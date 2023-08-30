package com.micro.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.micro.constant.RedisConstant;
import com.micro.dto.IdCardDTO;
import com.micro.dto.UserLoginDTO;
import com.micro.entity.UUser;
import com.micro.exception.CreateFinanceAccountException;
import com.micro.exception.UpdateUserLastLoginTimeException;
import com.micro.mapper.UFinanceAccountMapper;
import com.micro.mapper.UUserMapper;
import com.micro.result.Result;
import com.micro.utils.IdCardUtils;
import com.micro.utils.SmsCodeUtil;
import com.micro.utils.ThreadUserUtils;
import com.micro.vo.UserCenterVO;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.http.HttpRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@DubboService(interfaceClass = UserService.class, version = "1.0")
public class UserServiceImpl extends ServiceImpl<UUserMapper, UUser> implements UserService{

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private UFinanceAccountMapper uFinanceAccountMapper;

    /**
     * note 判断手机号是否注册
     * @date: 2023-08-18 17:54
     * @param:
     * @return:
     **/
    @Override
    public Result<String> phoneExist(String phone) {

        Integer count = query().eq("phone", phone).count();
        if (count != null && count > 0){
            return Result.fail("手机号已存在！");
        }

        return Result.success();
    }


    /**
     * note 发送验证码
     * @param
     * @return
     */
    @Override
    public Result codeRegister(String phone, String accessKeyId, String accessKeySecret, String endpoint) {
        String code = RandomUtil.randomNumbers(6);
        SendSmsResponse sendSmsResponse = SmsCodeUtil
                .sendSms(accessKeyId, accessKeySecret, endpoint, code);
        if (sendSmsResponse != null && sendSmsResponse.getStatusCode() == 200) {
            String key_prefix = RedisConstant.CODE_VERIFICATION + phone;
            stringRedisTemplate.opsForValue().set(key_prefix, code);
            return Result.success(sendSmsResponse.getStatusCode());
        }

        return Result.fail(sendSmsResponse.body.message);
    }

    /*
    *
     * note 用户注册
     * @date: 2023-08-18 17:56
     * @param:
     * @return:
     **/
    @Override
    @Transactional
    public synchronized Result<String> userRegister(String phone, String code) {
        // note 确认手机号是否注册过
        Result<String> stringResult = this.phoneExist(phone);
        if (stringResult.getCode().equals(Result.FAIL)){
            return stringResult;
        }

        // note 匹配验证码
        String key = RedisConstant.CODE_VERIFICATION + phone;
        String cacheCode = stringRedisTemplate.opsForValue().get(key);
        if (cacheCode == null || !cacheCode.equals(code)) {
            return Result.fail("验证码错误！");
        }
        // note 创建用户
        boolean success = save(UUser.builder()
                .phone(phone)
                .loginPassword(DigestUtils.md5DigestAsHex("123456".getBytes()))
                .addTime(LocalDateTime.now())
                .build());

        if (!success) {
            return Result.fail("注册失败");
        }

        UUser uUser = query().eq("phone", phone).one();
        Boolean financeAccount = createFinanceAccount(uUser.getId());
        if (!financeAccount) {
            Result.fail("创建用户余额账户失败");
        }

        return Result.success("注册成功!");
    }
    /*
    *
     * note 用户登录接口
     * @date: 2023-08-18 19:39
     * @param:
     * @return: 
     **/
    
    @Override
    @Transactional
    public Result userLogin(String phone, String password, String code){
        // note 判断验证码
        String key = RedisConstant.CODE_VERIFICATION + phone;
        String cacheCode = stringRedisTemplate.opsForValue().get(key);
        if (cacheCode == null || !cacheCode.equals(code)) {
            return Result.fail("验证码错误！");
        }

        // note 判断密码是否正确
        UUser uUser = query().eq("phone", phone).one();
        if (!uUser.getLoginPassword().equals(password)){
            return Result.fail("密码错误！");
        }

        LocalDateTime now = LocalDateTime.now();
        // note 更新用户最近登录时间
        boolean success = update().set("last_login_time", now).eq("phone", phone).update();

        // note 更新失败
        if (!success) {
            throw new UpdateUserLastLoginTimeException("更新用户最近登录时间失败!");
        }

        // note 创建token
        String token = UUID.randomUUID().toString();
        uUser.setLastLoginTime(now);
        UserLoginDTO userLoginDTO = UserLoginDTO.builder().uUser(uUser).token(token).build();
        // note 使用map结构将用户信息存储到redis，节约空间
        Map<String, Object> userInfoMap = BeanUtil.beanToMap(uUser, new HashMap<>(), CopyOptions.create()
                .setFieldValueEditor((fileName, fileValue) -> {
                    if (fileValue == null)
                        return "";
                    return fileValue.toString();
                }));
        String userKey = RedisConstant.LOGIN_USER_INFO + token;
        stringRedisTemplate.opsForHash().putAll(userKey, userInfoMap);
        stringRedisTemplate.expire(userKey, RedisConstant.USER_TTL, TimeUnit.HOURS);


        return Result.success(userLoginDTO);
    }

    /*
    *
     * note 查询用户是否创建余额表
     * @date: 2023-08-28 15:35
     * @param:
     * @return:
     **/
    public Boolean createFinanceAccount(Integer uid){
        Integer count = uFinanceAccountMapper.queryExistUid(uid);
        if (count > 0){
            return true;
        }
        Boolean success  = uFinanceAccountMapper.createFinanceAccount(uid);
        return success;
    }

    /*
    *
     * note 用户中心
     * @date: 2023-08-28 10:31
     * @param:
     * @return:
     **/
    @Override
    public Result<UserCenterVO> userCenter(Integer id) {
        UUser uUser = query().eq("id", id).one();
        if (uUser == null){
            return Result.fail("查询不到用户！");
        }
        UserCenterVO userCenterVO = UserCenterVO.builder().name(uUser.getName()).loginTime(uUser.getLastLoginTime())
                .headerUrl(uUser.getHeaderImage()).phone(uUser.getPhone()).build();
        BigDecimal money = uFinanceAccountMapper.queryMoney(id);
        if (money == null){
            return Result.fail("查询不到用户余额！");
        }
        userCenterVO.setMoney(money);
        return Result.success(userCenterVO);
    }
    /*
    *
     * note 实名认证
     * @date: 2023-08-30 16:33
     * @param:
     * @return:
     **/

    @Override
    @Transactional
    public Result userIDApprove(IdCardDTO idCardDTO,String appcode) {
        String cardNo = idCardDTO.getCardNo();
        String realName = idCardDTO.getRealName();
        String phone = idCardDTO.getPhone();

        Integer count = query().eq("id_card", cardNo).count();
        if (count > 0){
            return Result.fail("使用的身份证号已存在！");
        }

        JSONObject idCardApprove = IdCardUtils.IdCardApprove(cardNo, realName, appcode);
        if (idCardApprove == null) {
            return Result.fail("实名认证失败！");
        }
        int errorCode = Integer.parseInt(idCardApprove.getString("error_code"));
        if (errorCode > 0 ){
            return Result.fail(idCardApprove.getString("reason"));
        }

        boolean success = update().eq("phone", phone).set("name", realName).set("id_card", cardNo).update();
        if (!success) {
            return Result.fail("更新数据失败");
        }

        return Result.success("实名认证成功！");
    }
}

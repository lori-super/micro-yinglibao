package com.micro.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.micro.constant.RedisConstant;
import com.micro.constant.UserConstant;
import com.micro.dto.UserLoginDTO;
import com.micro.entity.UUser;
import com.micro.exception.UpdateUserLastLoginTimeException;
import com.micro.exception.UserExistException;
import com.micro.mapper.UUserMapper;
import com.micro.properties.AliSmsProperties;
import com.micro.result.Result;
import com.micro.utils.SmsCodeUtil;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@DubboService(interfaceClass = UserService.class, version = "1.0")
public class UserServiceImpl extends ServiceImpl<UUserMapper, UUser> implements UserService{

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    @Resource
    private UUserMapper uUserMapper;

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

        return Result.sucess();
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
            return Result.sucess(sendSmsResponse.body.message);
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
    public Result<String> userRegister(String phone, String code) {
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

        return Result.sucess("注册成功!");
    }
    /*
    *
     * note
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

        return Result.sucess(userLoginDTO);
    }
}

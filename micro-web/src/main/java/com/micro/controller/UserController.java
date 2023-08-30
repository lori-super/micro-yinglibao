package com.micro.controller;

import com.micro.dto.IdCardDTO;
import com.micro.dto.UserLoginParamsDTO;
import com.micro.result.Result;
import com.micro.vo.UserCenterVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/v1/user")
@Api(tags = "用户类接口")
@Slf4j
//@CrossOrigin
public class UserController extends BaseController{

    @GetMapping("/phone")
    @ApiOperation("判断手机号是否注册过")
    public Result<String> phoneExist(@RequestParam String phone){
        log.info("判断手机号是否存在，{}", phone);
        return userService.phoneExist(phone);
    }

    @GetMapping("/code")
    @ApiOperation("获取验证码")
    public Result codeRegister(@RequestParam String phone){
        log.info("获取验证码，{}", phone);
        return userService.codeRegister(phone, aliSmsProperties.getAccessKeyId(), aliSmsProperties.getAccessKeySecret(),
                aliSmsProperties.getEndpoint());
    }

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public Result<String> userRegister(@RequestParam String phone, @RequestParam String code){
        log.info("用户注册，手机号：{}， 验证码：{}", phone, code);
        return userService.userRegister(phone, code);
    }

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public Result userLogin(UserLoginParamsDTO userLoginDTO){
        log.info("用户注册，用户信息：{}", userLoginDTO);
        return userService.userLogin(userLoginDTO.getPhone(), userLoginDTO.getPassword(), userLoginDTO.getCode());
    }

    @GetMapping("/usercenter")
    @ApiOperation("用户中心接口")
    public Result<UserCenterVO> userCenter(HttpServletRequest request){
        log.info("用户中心接口");
        Integer uid = Integer.valueOf(request.getHeader("uid"));
        return userService.userCenter(uid);
    }

    @Value("${p2p.aliidcard.appcode}")
    private String appcode;
    @PutMapping("/realname")
    @ApiOperation("实名认证")
    public Result userIDApprove(@RequestBody IdCardDTO idCardDTO){
        log.info("实名认证接口:{}", idCardDTO);
        return userService.userIDApprove(idCardDTO, appcode);
    }



}

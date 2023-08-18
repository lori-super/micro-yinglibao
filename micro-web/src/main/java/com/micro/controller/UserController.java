package com.micro.controller;

import com.micro.dto.UserLoginDTO;
import com.micro.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user")
@Api(tags = "用户类接口")
@Slf4j
@CrossOrigin
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

    @GetMapping("/login")
    @ApiOperation("用户登录")
    public Result userLogin(@RequestParam String phone, @RequestParam String password, @RequestParam String code){
        log.info("用户注册，手机号：{}， 密码：{}, 验证码：{}", phone, password,code);
        return userService.userLogin(phone, password, code);
    }

}

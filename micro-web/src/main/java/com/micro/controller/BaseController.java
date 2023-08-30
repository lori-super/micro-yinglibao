package com.micro.controller;

import com.micro.properties.AliSmsProperties;
import com.micro.service.*;
import org.apache.dubbo.config.annotation.DubboReference;

import javax.annotation.Resource;

public class BaseController {
    @Resource
    protected AliSmsProperties aliSmsProperties;
    @DubboReference(interfaceClass = PlatBaseInfoService.class, version = "1.0")
    protected PlatBaseInfoService platBaseInfoService;

    @DubboReference(interfaceClass = ProductInfoService.class, version = "1.0")
    protected ProductInfoService productInfoService;

    @DubboReference(interfaceClass = InvestRankService.class, version = "1.0")
    protected InvestRankService investRankService;

    @DubboReference(interfaceClass = ProductDetailService.class, version = "1.0")
    protected ProductDetailService productDetailService;

    @DubboReference(interfaceClass = UserService.class, version = "1.0")
    protected UserService userService;

    @DubboReference(interfaceClass = UserInvestService.class, version = "1.0")
    protected UserInvestService userInvestService ;
}

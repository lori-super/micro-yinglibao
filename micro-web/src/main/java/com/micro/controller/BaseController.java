package com.micro.controller;

import com.micro.service.InvestRankService;
import com.micro.service.PlatBaseInfoService;
import com.micro.service.ProductDetailService;
import com.micro.service.ProductInfoService;
import org.apache.dubbo.config.annotation.DubboReference;

public class BaseController {

    @DubboReference(interfaceClass = PlatBaseInfoService.class, version = "1.0")
    protected PlatBaseInfoService platBaseInfoService;

    @DubboReference(interfaceClass = ProductInfoService.class, version = "1.0")
    protected ProductInfoService productInfoService;

    @DubboReference(interfaceClass = InvestRankService.class, version = "1.0")
    protected InvestRankService investRankService;

    @DubboReference(interfaceClass = ProductDetailService.class, version = "1.0")
    protected ProductDetailService productDetailService;
}

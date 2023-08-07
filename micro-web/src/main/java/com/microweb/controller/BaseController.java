package com.microweb.controller;

import com.microapi.service.PlatBaseInfoService;
import org.apache.dubbo.config.annotation.DubboReference;

public class BaseController {

    @DubboReference(interfaceClass = PlatBaseInfoService.class, version = "1.0")
    protected PlatBaseInfoService platBaseInfoService;
}

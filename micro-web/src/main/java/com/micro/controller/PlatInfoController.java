package com.micro.controller;

import com.micro.vo.PlatBaseInfoVO;
import com.micro.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/plat")
@Api(tags = "首页信息接口")
@Slf4j
public class PlatInfoController extends BaseController {
    @GetMapping("/info")
    @ApiOperation("页面基本信息接口")
    public Result<PlatBaseInfoVO> queryPlatInfo(){
        log.info("页面基本信息，利率，注册用户，总金额");
        PlatBaseInfoVO platBaseInfoVO = platBaseInfoService.queryPlatBaseInfo();
        return Result.success(platBaseInfoVO);
    }


}

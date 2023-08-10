package com.micro.controller;

import com.micro.vo.PlatBaseInfoVO;
import com.mircro.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/plat")
@Api(tags = "首页信息接口")
public class PlatInfoController extends BaseController {
    @GetMapping("/info")
    @ApiOperation("页面基本信息接口")
    public Result<PlatBaseInfoVO> queryPlatInfo(){
        PlatBaseInfoVO platBaseInfoVO = platBaseInfoService.queryPlatBaseInfo();
        return Result.sucess(platBaseInfoVO);
    }


}

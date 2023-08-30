package com.micro.controller;

import com.micro.result.Result;
import com.micro.vo.UserInvestInfoListVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/v1/recharge")
@Api(tags = "用户类接口")
@Slf4j
public class UserInvestController extends BaseController{

    @GetMapping("/records")
    @ApiOperation("获取用户的投资列表")
    public Result<UserInvestInfoListVO> userInvestList(@RequestParam Integer pageNo, @RequestParam Integer pageSize, HttpServletRequest request){
        log.info("获取用户的资产列表，页码：{}，页面大小：{}", pageNo, pageSize);

        return userInvestService.queryInvestList(pageNo, pageSize, Integer.valueOf(request.getHeader("uid")));
    }

}

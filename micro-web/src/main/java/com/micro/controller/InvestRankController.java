package com.micro.controller;

import com.micro.vo.InvestRankVO;
import com.mircro.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/invest")
@Api(tags = "投资排行榜")
@Slf4j
public class InvestRankController extends BaseController{

    @GetMapping("/rank")
    @ApiOperation("投资排行榜")
    public Result<List<InvestRankVO>> investRank(){
        log.info("投资排行榜");
        return Result.sucess(investRankService.investRankList());
    }

}

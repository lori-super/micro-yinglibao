package com.microweb.controller;

import com.microapi.dto.PlatBaseInfoDTO;
import com.mircrocommon.result.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/plat")
public class PlatInfoController extends BaseController {
    @GetMapping("/info")
    public Result<PlatBaseInfoDTO> queryPlatInfo(){
        PlatBaseInfoDTO platBaseInfoDTO = platBaseInfoService.queryPlatBaseInfo();
        return Result.sucess(platBaseInfoDTO);
    }
}

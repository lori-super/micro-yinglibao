package com.micro.controller;

import com.micro.page.PageResult;
import com.micro.vo.IndexProductInfoVO;
import com.micro.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/product")
@Api(tags = "首页产品信息接口")
@Slf4j
public class IndexProductController extends BaseController {

    @GetMapping("/index")
    @ApiOperation("返回首页产品")
    public Result<IndexProductInfoVO> indexPageProduct(){
        log.info("首页产品展示");
        return Result.success(productInfoService.queryProductInfoPage());
    }

    @GetMapping("/list")
    @ApiOperation("查看更多产品，返回分页产品列表")
    public Result<PageResult> productList(@RequestParam(defaultValue = "1", required = false, value = "page") Integer page,
                                          @RequestParam(defaultValue = "9", required = false, value = "pageSize") Integer pageSize,
                                          @RequestParam(value = "productType") Integer productType){
        log.info("分页产品结果展示, 页码={}, 页面大小={} , 产品类型{}", page, pageSize, productType);
        return Result.success(productInfoService.queryProductList(page, pageSize, productType));
    }

}

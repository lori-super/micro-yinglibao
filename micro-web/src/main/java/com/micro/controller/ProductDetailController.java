package com.micro.controller;

import com.micro.vo.ProductDetailVO;
import com.mircro.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/product")
@Api(tags = "产品详情接口类")
@Slf4j
@CrossOrigin
public class ProductDetailController extends BaseController{

    @GetMapping("/detail/{id}")
    @ApiOperation("产品详情")
    public Result<ProductDetailVO> queryDetail(@PathVariable Integer id){
        log.info("查看产品详情，产品ID={}", id);
        return productDetailService.queryProductDetail(id);
    }
}

package com.micro.service;

import com.micro.vo.ProductDetailVO;
import com.micro.result.Result;

public interface ProductDetailService {

    Result<ProductDetailVO> queryProductDetail(Integer productId);
}

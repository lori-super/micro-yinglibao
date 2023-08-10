package com.micro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.micro.entity.BLoanInfo;
import com.micro.page.PageResult;
import com.micro.vo.IndexProductInfoVO;

public interface ProductInfoService extends IService<BLoanInfo> {

      IndexProductInfoVO queryProductInfoPage();

      PageResult queryProductList(Integer page, Integer pageSize, Integer productType);
}

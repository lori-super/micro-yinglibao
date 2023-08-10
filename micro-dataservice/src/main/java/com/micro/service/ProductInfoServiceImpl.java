package com.micro.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.micro.entity.BLoanInfo;
import com.micro.mapper.BLoanInfoMapper;
import com.micro.page.PageResult;
import com.micro.vo.IndexProductInfoVO;
import com.mircro.constant.ProductConstant;
import org.apache.dubbo.config.annotation.DubboService;


import javax.annotation.Resource;

@DubboService(interfaceClass = ProductInfoService.class, version = "1.0")
public class ProductInfoServiceImpl extends ServiceImpl<BLoanInfoMapper, BLoanInfo> implements ProductInfoService{

    @Resource
    private BLoanInfoMapper bLoanInfoMapper;

    /**
     * 返回首页产品
     * @return
     */
    @Override
    public IndexProductInfoVO queryProductInfoPage() {
        PageHelper.startPage(1, 1);
        Page<BLoanInfo> xinshoubaoPage = bLoanInfoMapper.queryPageProduct(ProductConstant.xinshoubao);

        PageHelper.startPage(1, 3);
        Page<BLoanInfo> youxuanPage = bLoanInfoMapper.queryPageProduct(ProductConstant.youxuanProduct);

        PageHelper.startPage(1, 3);
        Page<BLoanInfo> sanbiaoPage = bLoanInfoMapper.queryPageProduct(ProductConstant.sanbiaoProduct);

        return IndexProductInfoVO.builder().xinshoubao(xinshoubaoPage.getResult())
                .youxuanProduct(youxuanPage.getResult())
                .sanbiaoProduct(sanbiaoPage.getResult()).build();
    }

    /**
     * 产品列表分页
     * @param page
     * @param pageSize
     * @param productType
     * @return
     */
    @Override
    public PageResult queryProductList(Integer page, Integer pageSize, Integer productType) {



        PageHelper.startPage(page, pageSize);
        Page<BLoanInfo> bLoanInfos = bLoanInfoMapper.queryPageProduct(productType);
        return new PageResult(bLoanInfos.getTotal(), bLoanInfos.getResult());
    }
}

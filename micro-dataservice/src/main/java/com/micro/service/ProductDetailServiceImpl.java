package com.micro.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.micro.dto.BidInfoWithUserDTO;
import com.micro.entity.BBidInfo;
import com.micro.entity.BLoanInfo;
import com.micro.mapper.BBidInfoMapper;
import com.micro.mapper.BLoanInfoMapper;
import com.micro.vo.ProductDetailVO;
import com.micro.utils.CommonUtils;
import com.micro.result.Result;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@DubboService(interfaceClass = ProductDetailService.class, version = "1.0")
public class ProductDetailServiceImpl extends ServiceImpl<BBidInfoMapper, BBidInfo> implements ProductDetailService{

    @Resource
    private BLoanInfoMapper bLoanInfoMapper;

    @Resource
    private BBidInfoMapper bBidInfoMapper;

    @Override
    public Result<ProductDetailVO> queryProductDetail(Integer productId) {
        //查询投资信息列表 select * from b_income_record where loan_id = ?
        List<BidInfoWithUserDTO> list = bBidInfoMapper.queryBidInfoWithPhone(productId);

        List<BidInfoWithUserDTO> collect = list.stream().map(item -> {
            item.setPhone(CommonUtils.hidePhoneNumber(item.getPhone()));
            return item;
        }).collect(Collectors.toList());

        BLoanInfo bLoanInfo = bLoanInfoMapper.queryById(productId);
        return Result.sucess(ProductDetailVO.builder().bLoanInfo(bLoanInfo).investRecords(collect).build());
    }
}

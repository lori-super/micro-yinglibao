package com.micro.service;



import com.micro.mapper.BBidInfoMapper;
import com.micro.mapper.BLoanInfoMapper;
import com.micro.mapper.UUserMapper;
import com.microapi.dto.PlatBaseInfoDTO;
import com.microapi.service.PlatBaseInfoService;
import org.apache.dubbo.config.annotation.DubboService;


import javax.annotation.Resource;
import java.math.BigDecimal;

@DubboService(interfaceClass = PlatBaseInfoService.class, version = "1.0")
public class PlatBaseInfoServiceImpl implements PlatBaseInfoService {
    @Resource
    private UUserMapper uUserMapper;
    @Resource
    private BLoanInfoMapper bLoanInfoMapper;

    @Resource
    private BBidInfoMapper bBidInfoMapper;

    @Override
    public PlatBaseInfoDTO queryPlatBaseInfo() {
        Integer countUser = uUserMapper.countUser();
        BigDecimal avgRate = bLoanInfoMapper.avgRate();
        BigDecimal sumSales = bBidInfoMapper.sumSales();
        return PlatBaseInfoDTO.builder().registerUsers(countUser).sumMoney(sumSales).avgRate(avgRate).build();
    }
}

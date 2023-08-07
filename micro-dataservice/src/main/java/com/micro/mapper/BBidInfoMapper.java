package com.micro.mapper;

import com.micro.entity.BBidInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;

/**
* @author rechao
* @description 针对表【b_bid_info(投资记录表)】的数据库操作Mapper
* @createDate 2023-08-07 16:44:08
* @Entity com.micro.entity.BBidInfo
*/
@Mapper
public interface BBidInfoMapper extends BaseMapper<BBidInfo> {

    BigDecimal sumSales();
}





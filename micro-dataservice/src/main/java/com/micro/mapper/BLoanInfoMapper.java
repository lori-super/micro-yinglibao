package com.micro.mapper;

import com.micro.entity.BLoanInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author rechao
* @description 针对表【b_loan_info(产品信息表)】的数据库操作Mapper
* @createDate 2023-08-07 16:44:08
* @Entity com.micro.entity.BLoanInfo
*/
@Mapper
public interface BLoanInfoMapper extends BaseMapper<BLoanInfo> {

}





package com.micro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.micro.entity.BLoanInfo;
import com.micro.service.BLoanInfoService;
import com.micro.mapper.BLoanInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author rechao
* @description 针对表【b_loan_info(产品信息表)】的数据库操作Service实现
* @createDate 2023-08-07 16:44:08
*/
@Service
public class BLoanInfoServiceImpl extends ServiceImpl<BLoanInfoMapper, BLoanInfo>
    implements BLoanInfoService{

}





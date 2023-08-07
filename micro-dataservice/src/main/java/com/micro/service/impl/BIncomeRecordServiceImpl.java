package com.micro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.micro.entity.BIncomeRecord;
import com.micro.service.BIncomeRecordService;
import com.micro.mapper.BIncomeRecordMapper;
import org.springframework.stereotype.Service;

/**
* @author rechao
* @description 针对表【b_income_record(收益记录表)】的数据库操作Service实现
* @createDate 2023-08-07 16:44:08
*/
@Service
public class BIncomeRecordServiceImpl extends ServiceImpl<BIncomeRecordMapper, BIncomeRecord>
    implements BIncomeRecordService{

}





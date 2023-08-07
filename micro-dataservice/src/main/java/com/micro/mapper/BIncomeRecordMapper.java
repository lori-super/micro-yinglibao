package com.micro.mapper;

import com.micro.entity.BIncomeRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author rechao
* @description 针对表【b_income_record(收益记录表)】的数据库操作Mapper
* @createDate 2023-08-07 16:44:08
* @Entity com.micro.entity.BIncomeRecord
*/
@Mapper
public interface BIncomeRecordMapper extends BaseMapper<BIncomeRecord> {

}





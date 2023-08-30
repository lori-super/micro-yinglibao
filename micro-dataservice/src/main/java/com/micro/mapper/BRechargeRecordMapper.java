package com.micro.mapper;

import com.github.pagehelper.Page;
import com.micro.entity.BRechargeRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author rechao
* @description 针对表【b_recharge_record(充值记录表)】的数据库操作Mapper
* @createDate 2023-08-07 16:44:08
* @Entity com.micro.entity.BRechargeRecord
*/
@Mapper
public interface BRechargeRecordMapper extends BaseMapper<BRechargeRecord> {

    Page<BRechargeRecord> queryUserRechargeRecord(Integer uid);
}





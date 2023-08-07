package com.micro.mapper;

import com.micro.entity.UFinanceAccount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author rechao
* @description 针对表【u_finance_account(用户财务资金账户表)】的数据库操作Mapper
* @createDate 2023-08-07 16:44:08
* @Entity com.micro.entity.UFinanceAccount
*/
@Mapper
public interface UFinanceAccountMapper extends BaseMapper<UFinanceAccount> {

}





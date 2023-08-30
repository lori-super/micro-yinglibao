package com.micro.mapper;

import com.micro.entity.UFinanceAccount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;

/**
* @author rechao
* @description 针对表【u_finance_account(用户财务资金账户表)】的数据库操作Mapper
* @createDate 2023-08-07 16:44:08
* @Entity com.micro.entity.UFinanceAccount
*/
@Mapper
public interface UFinanceAccountMapper extends BaseMapper<UFinanceAccount> {

    @Select("select count(uid) from u_finance_account where uid = #{uid}  ")
    Integer queryExistUid(Integer uid);

    @Insert("insert into u_finance_account (uid, available_money) values (#{uid}, 0.0)")
    Boolean createFinanceAccount(Integer uid);

    @Select("select available_money from u_finance_account where uid = #{id}")
    BigDecimal queryMoney(Integer id);
}





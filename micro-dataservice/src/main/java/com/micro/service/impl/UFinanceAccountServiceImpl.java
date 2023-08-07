package com.micro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.micro.entity.UFinanceAccount;
import com.micro.service.UFinanceAccountService;
import com.micro.mapper.UFinanceAccountMapper;
import org.springframework.stereotype.Service;

/**
* @author rechao
* @description 针对表【u_finance_account(用户财务资金账户表)】的数据库操作Service实现
* @createDate 2023-08-07 16:44:08
*/
@Service
public class UFinanceAccountServiceImpl extends ServiceImpl<UFinanceAccountMapper, UFinanceAccount>
    implements UFinanceAccountService{

}





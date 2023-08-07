package com.micro.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

/**
 * 用户财务资金账户表
 * @TableName u_finance_account
 */
@Data
@Builder
public class UFinanceAccount implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 用户ID
     */
    private Integer uid;

    /**
     * 用户可用资金
     */
    private BigDecimal availableMoney;

    private static final long serialVersionUID = 1L;
}
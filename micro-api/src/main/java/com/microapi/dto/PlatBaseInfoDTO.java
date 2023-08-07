package com.microapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlatBaseInfoDTO implements Serializable {
    /**
     * 注册用户数
     */
    private Integer registerUsers;
    /**
     * 成交金额总数
     */
    private BigDecimal sumMoney;
    /**
     *  收益率平均值
     */
    private BigDecimal avgRate;
}

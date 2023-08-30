package com.micro.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncomeRecordVO implements Serializable {
    private static final long serialVersionUID = 1L;
    //产品名称
    private String productName;
    //收益时间
    private LocalDateTime incomeDate;
    //收益金额
    private BigDecimal incomeMoney;
    private Integer id;

}

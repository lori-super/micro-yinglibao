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
public class RechargeRecordVO implements Serializable {
    private static final long serialVersionUID = 1L;
    //充值订单状态
    private String rechargeStatus;
    private BigDecimal rechargeMoney;
    private LocalDateTime rechargeTime;
    private Integer id;
}

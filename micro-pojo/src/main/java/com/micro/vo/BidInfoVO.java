package com.micro.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BidInfoVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String productName;
    private BigDecimal bidMoney;
    private LocalDateTime bidTime;
    private Integer id;
}

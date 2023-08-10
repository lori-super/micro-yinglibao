package com.micro.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvestRankVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Double money;
    private String phone;
}

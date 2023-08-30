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
public class UserCenterVO implements Serializable {

    private static final long serialVersionUID = 1L;
    private LocalDateTime loginTime;
    private BigDecimal money;
    private String phone;
    private String name;
    // note 用户头像url
    private String headerUrl;

}

package com.micro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdCardDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String phone;
    private String cardNo;
    private String realName;
    private String code;
}

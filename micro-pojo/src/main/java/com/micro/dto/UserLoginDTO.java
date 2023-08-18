package com.micro.dto;

import com.micro.entity.UUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String token;
    private UUser uUser;

}

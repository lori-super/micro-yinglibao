package com.micro.service;

import com.micro.dto.UserLoginDTO;
import com.micro.result.Result;

public interface UserService {

    Result<String> phoneExist(String phone);

    Result codeRegister(String phone, String accessKeyId, String accessKeySecret, String endpoint);

    Result<String> userRegister(String phone, String code);

    Result userLogin(String phone, String password, String code);
}

package com.micro.service;

import com.micro.dto.IdCardDTO;
import com.micro.result.Result;
import com.micro.vo.UserCenterVO;
import org.apache.http.HttpRequest;

public interface UserService {

    Result<String> phoneExist(String phone);

    Result codeRegister(String phone, String accessKeyId, String accessKeySecret, String endpoint);

    Result<String> userRegister(String phone, String code);

    Result userLogin(String phone, String password, String code);

    Result<UserCenterVO> userCenter(Integer id);

    Result userIDApprove(IdCardDTO idCardDTO,String appcode);
}

package com.micro.service;

import com.micro.result.Result;
import com.micro.vo.UserInvestInfoListVO;

public interface UserInvestService {
    Result<UserInvestInfoListVO> queryInvestList(Integer pageNo, Integer pageSize, Integer uid);
}

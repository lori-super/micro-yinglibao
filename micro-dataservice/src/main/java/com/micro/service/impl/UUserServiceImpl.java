package com.micro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.micro.entity.UUser;
import com.micro.service.UUserService;
import com.micro.mapper.UUserMapper;
import org.springframework.stereotype.Service;

/**
* @author rechao
* @description 针对表【u_user(用户表)】的数据库操作Service实现
* @createDate 2023-08-07 16:44:08
*/
@Service
public class UUserServiceImpl extends ServiceImpl<UUserMapper, UUser>
    implements UUserService{

}





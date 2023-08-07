package com.micro.mapper;

import com.micro.entity.UUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author rechao
* @description 针对表【u_user(用户表)】的数据库操作Mapper
* @createDate 2023-08-07 16:44:08
* @Entity com.micro.entity.UUser
*/
@Mapper
public interface UUserMapper extends BaseMapper<UUser> {

}





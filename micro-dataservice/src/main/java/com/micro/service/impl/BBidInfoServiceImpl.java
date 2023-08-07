package com.micro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.micro.entity.BBidInfo;
import com.micro.service.BBidInfoService;
import com.micro.mapper.BBidInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author rechao
* @description 针对表【b_bid_info(投资记录表)】的数据库操作Service实现
* @createDate 2023-08-07 16:44:08
*/
@Service
public class BBidInfoServiceImpl extends ServiceImpl<BBidInfoMapper, BBidInfo>
    implements BBidInfoService{

}





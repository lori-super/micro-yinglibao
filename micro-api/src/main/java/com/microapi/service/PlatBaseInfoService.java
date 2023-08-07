package com.microapi.service;


import com.microapi.dto.PlatBaseInfoDTO;

public interface PlatBaseInfoService{

    // 实现统计用户、平均利率、成交金额总量
    PlatBaseInfoDTO queryPlatBaseInfo();
}

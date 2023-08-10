package com.micro.service;


import com.micro.vo.PlatBaseInfoVO;

public interface PlatBaseInfoService{

    // 实现统计用户、平均利率、成交金额总量
    PlatBaseInfoVO queryPlatBaseInfo();
}

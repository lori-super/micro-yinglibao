package com.micro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.micro.entity.BBidInfo;
import com.micro.vo.InvestRankVO;

import java.util.List;

public interface InvestRankService extends IService<BBidInfo> {
    List<InvestRankVO> investRankList();
}

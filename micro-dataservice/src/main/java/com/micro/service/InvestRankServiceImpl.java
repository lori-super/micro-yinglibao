package com.micro.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.micro.entity.BBidInfo;
import com.micro.mapper.BBidInfoMapper;
import com.micro.vo.InvestRankVO;
import com.mircro.constant.RedisConstant;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@DubboService(interfaceClass = InvestRankService.class, version = "1.0")
public class InvestRankServiceImpl extends ServiceImpl<BBidInfoMapper, BBidInfo> implements InvestRankService {
    @Resource
    private BBidInfoMapper bBidInfoMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public List<InvestRankVO> investRankList() {
        Set<String> investRange = stringRedisTemplate.opsForZSet().range(RedisConstant.INVEST_RANK, 0, 2);
        List<InvestRankVO> list = new ArrayList<>();
        if (investRange != null && investRange.size() != 0) {
            investRange.forEach(item ->{
                Double score = stringRedisTemplate.opsForZSet().score(RedisConstant.INVEST_RANK, item);
                list.add(new InvestRankVO(score, item));
            });
            Collections.reverse(list);
            return list;
        }

        List<InvestRankVO> investRankBOS = bBidInfoMapper.queryInvestSum();
        investRankBOS.forEach(item -> {
            stringRedisTemplate.opsForZSet().add(RedisConstant.INVEST_RANK, item.getPhone()
                    , item.getMoney());
        });
        return investRankBOS;
    }
}

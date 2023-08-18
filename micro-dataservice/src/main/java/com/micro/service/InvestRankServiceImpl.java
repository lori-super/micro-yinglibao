package com.micro.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.micro.entity.BBidInfo;
import com.micro.mapper.BBidInfoMapper;
import com.micro.vo.InvestRankVO;
import com.micro.constant.RedisConstant;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@DubboService(interfaceClass = InvestRankService.class, version = "1.0")
public class InvestRankServiceImpl extends ServiceImpl<BBidInfoMapper, BBidInfo> implements InvestRankService {
    @Resource
    private BBidInfoMapper bBidInfoMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public List<InvestRankVO> investRankList() {
        Set<ZSetOperations.TypedTuple<String>> typedTuples = stringRedisTemplate.opsForZSet().reverseRangeWithScores(RedisConstant.INVEST_RANK, 0, 2);
        List<InvestRankVO> list = new ArrayList<>();
        if (typedTuples != null && typedTuples.size() != 0) {
            typedTuples.forEach(item ->{
                list.add(InvestRankVO.builder().phone(item.getValue()).money(item.getScore()).build());
            });
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

package com.micro.service;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.micro.entity.BIncomeRecord;
import com.micro.entity.BRechargeRecord;
import com.micro.enumeration.RechargeStatus;
import com.micro.mapper.BBidInfoMapper;
import com.micro.mapper.BIncomeRecordMapper;
import com.micro.mapper.BRechargeRecordMapper;
import com.micro.page.PageResult;
import com.micro.result.Result;
import com.micro.vo.BidInfoVO;
import com.micro.vo.IncomeRecordVO;
import com.micro.vo.RechargeRecordVO;
import com.micro.vo.UserInvestInfoListVO;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@DubboService(interfaceClass = UserInvestService.class, version = "1.0")
public class UserInvestServiceImpl implements UserInvestService{
    @Resource
    private BRechargeRecordMapper bRechargeRecordMapper;
    @Resource
    private BIncomeRecordMapper bIncomeRecordMapper;
    @Resource
    private BBidInfoMapper bBidInfoMapper;


    @Override
    public Result<UserInvestInfoListVO> queryInvestList(Integer pageNo, Integer pageSize, Integer uid) {
        PageHelper.startPage(pageNo, pageSize);
        Page<BRechargeRecord> rechargeRecordList = bRechargeRecordMapper.queryUserRechargeRecord(uid);
        // note 获取RechargeRecord列表
        List<RechargeRecordVO> rechargeRecordVOList = new ArrayList<>();
        rechargeRecordList.getResult().forEach(rechargeRecord ->{
            // note 将DateTIme转为LocalDateTime
            LocalDateTime rechargeTime = rechargeRecord.getRechargeTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            RechargeRecordVO rechargeRecordVO = RechargeRecordVO.builder()
                    // note 使用枚举，实现键值对的映射
                    .rechargeStatus(RechargeStatus.values()[rechargeRecord.getRechargeStatus()].getDescription())
                    .rechargeMoney(rechargeRecord.getRechargeMoney()).rechargeTime(rechargeTime).id(rechargeRecord.getId()).build();
            rechargeRecordVOList.add(rechargeRecordVO);
        });
        PageResult rechargePage = PageResult.builder().pageNumber(rechargeRecordList.getPageNum()).pageSize(rechargeRecordList.getPageSize())
                .totalPage(rechargeRecordList.getPages()).total(rechargeRecordList.getTotal()).records(rechargeRecordVOList).build();

        // note 获取IncomeRecord列表
        PageHelper.startPage(pageNo, pageSize);
        Page<IncomeRecordVO> incomeRecords = bIncomeRecordMapper.queryUserIncomeRecord(uid);
        PageResult incomeRecordPage = PageResult.builder().pageNumber(incomeRecords.getPageNum()).pageSize(incomeRecords.getPageSize())
                .totalPage(incomeRecords.getPages()).total(incomeRecords.getTotal()).records(incomeRecords.getResult()).build();

        // note 获取BidInfo列表
        PageHelper.startPage(pageNo, pageSize);
        Page<BidInfoVO> bidInfoVOPage = bBidInfoMapper.queryBidInfoRecord(uid);
        PageResult bidInfoPages = PageResult.builder().pageNumber(bidInfoVOPage.getPageNum()).pageSize(bidInfoVOPage.getPageSize())
                .totalPage(bidInfoVOPage.getPages()).total(bidInfoVOPage.getTotal()).records(bidInfoVOPage.getResult()).build();

        // note 放入UserInvestInfoListVO中
        UserInvestInfoListVO userInvestInfoListVO = UserInvestInfoListVO.builder()
                .bBidInfos(bidInfoPages).bIncomeRecords(incomeRecordPage).rechargeRecords(rechargePage).build();
        return Result.success(userInvestInfoListVO);
    }
}

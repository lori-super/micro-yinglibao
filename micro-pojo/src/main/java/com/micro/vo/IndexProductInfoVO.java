package com.micro.vo;

import com.micro.entity.BLoanInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IndexProductInfoVO implements Serializable{

    private static final long serialVersionUID = 1L;

    // 首页新手包展示
    private List<BLoanInfo> xinshoubao;
    // 首页优选产品展示
    private List<BLoanInfo> youxuanProduct;
    // 首页散标产品展示
    private List<BLoanInfo> sanbiaoProduct;
}

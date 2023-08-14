package com.micro.vo;

import com.micro.dto.BidInfoWithUserDTO;
import com.micro.entity.BBidInfo;
import com.micro.entity.BIncomeRecord;
import com.micro.entity.BLoanInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDetailVO implements Serializable{
    private static final long serialVersionUID = 1L;
    //产品信息
    private BLoanInfo bLoanInfo;
    // 投资记录
    private List<BidInfoWithUserDTO> investRecords;
}

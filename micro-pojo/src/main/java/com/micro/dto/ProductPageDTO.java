package com.micro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 首页产品分页
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductPageDTO {
    // 页码
    private Integer page;
    // 页面大小
    private Integer pageSize;
    // 产品类型
    private Integer productType;


}

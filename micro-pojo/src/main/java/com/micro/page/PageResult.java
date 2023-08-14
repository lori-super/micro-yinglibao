package com.micro.page;

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
public class PageResult implements Serializable {
    private static final long serialVersionUID = 1L;
    // 总数
    private Long total;
    // 分页返回的记录
    private List records;
    // 页码
    private Integer pageNumber;
    //页面大小
    private Integer pageSize;
    // 总共几页
    private Integer totalPage;
}

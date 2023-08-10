package com.micro.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Builder;
import lombok.Data;

/**
 * 用户表
 * @TableName u_user
 */
@Data
@Builder
public class UUser implements Serializable {
    /**
     * 用户ID，主键
     */
    private Integer id;

    /**
     * 注册手机号码
     */
    private String phone;

    /**
     * 登录密码，密码长度最大16位
     */
    private String loginPassword;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 用户身份证号码
     */
    private String idCard;

    /**
     * 注册时间
     */
    private Date addTime;

    /**
     * 最近登录时间
     */
    private Date lastLoginTime;

    /**
     * 用户头像文件路径
     */
    private String headerImage;

    private static final long serialVersionUID = 1L;
}
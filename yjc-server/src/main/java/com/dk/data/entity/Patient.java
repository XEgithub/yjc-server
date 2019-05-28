package com.dk.data.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 患者 Entity
*
* @author ban
* @date 2018/12/05
*/
@Getter
@Setter
@ToString
@TableName("patient")
public class Patient {

    /**
     * 主键
     */
    private Long id;

    /**
     * uuid
     */
    private String uuid;

    /**
     * 用户
     */
    private String user;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 体重
     */
    private String weight;

    /**
     * 身高
     */
    private String height;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 地址
     */
    private String address;

    /**
     * 证件类型
     */
    private String certificatesType;

    /**
     * 证件号码
     */
    private String certificatesNo;

    /**
     * 是否默认(0表示不默认, 1表示默认)
     */
    private String first;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date updateTime;

    /**
     * 描述
     */
    private String description;

    /**
     * 状态
     */
    private Byte status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 预留
     */
    private String reserve;

    /**
     * 0:删除 1:未删除
     */
    @TableLogic
    private Boolean deleted;

}
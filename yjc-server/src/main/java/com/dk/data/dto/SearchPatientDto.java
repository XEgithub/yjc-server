package com.dk.data.dto;

import cn.sourcespro.commons.data.dto.PageDto;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 患者 Search Condition Entity
*
* @author ban
* @date 2018/12/05
*/
@Getter
@Setter
@ToString
public class SearchPatientDto extends PageDto {

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
    private Date createTime;

    /**
     * 修改时间
     */
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

}
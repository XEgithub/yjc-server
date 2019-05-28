package com.dk.data.dto;

import cn.sourcespro.commons.data.dto.PageDto;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 轮播图 Search Condition Entity
*
* @author ban
* @date 2018/12/04
*/
@Getter
@Setter
@ToString
public class SearchHospitalDto extends PageDto {

    /**
     * logo
     */
    private String logo;

    /**
     * 名称
     */
    private String name;

    /**
     * 地址
     */
    private String address;

    /**
     * 手机
     */
    private String phone;

    /**
     * 座机
     */
    private String tel;

    /**
     * 访问次数
     */
    private Long visitCount;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 备注
     */
    private String remark;

    /**
     * 备注1
     */
    private String remark1;

    /**
     * 状态
     */
    private Byte status;

    /**
     * 预留
     */
    private String reserve;

}
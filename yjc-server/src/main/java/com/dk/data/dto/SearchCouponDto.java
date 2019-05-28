package com.dk.data.dto;

import cn.sourcespro.commons.data.dto.PageDto;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 微信用户 Search Condition Entity
*
* @author ban
* @date 2018/12/10
*/
@Getter
@Setter
@ToString
public class SearchCouponDto extends PageDto {

    /**
     * 项目
     */
    private ProjectDto[] projects;

    /**
     * 优惠券名
     */
    private String name;

    /**
     * 用户uuid
     */
    private String user;

    /**
     * 最小订单金额
     */
    private BigDecimal minPrice;

    /**
     * 优惠金额
     */
    private BigDecimal couponPrice;

    /**
     * 兑换所需积分
     */
    private Long needIntegral;

    /**
     * 优惠券类型1:满减优惠券 2:服务抵用券
     */
    private Boolean type;

    /**
     * 适用范围(0表示不通用仅指定医院使用 1表示通用)
     */
    private String application;

    /**
     * 医院uuid
     */
    private Long hospital;

    /**
     * 开始时间
     */
    private Date beginTime;

    /**
     * 过期时间
     */
    private Date expireTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 操作人t_user
     */
    private String operator;

    /**
     * 描述
     */
    private String description;

    /**
     * 备注
     */
    private String remark;

    /**
     * 0: 未使用 1:已使用
     */
    private Byte status;

    /**
     * 预留
     */
    private String reserve;

}
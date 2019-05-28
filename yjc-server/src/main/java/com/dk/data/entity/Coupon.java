package com.dk.data.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 微信用户 Entity
*
* @author ban
* @date 2018/12/10
*/
@Getter
@Setter
@ToString
@TableName("coupon")
public class Coupon {

    /**
     * id
     */
    private Long id;

    /**
     * uuid
     */
    private String uuid;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date beginTime;

    /**
     * 过期时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date expireTime;

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

    /**
     * 0:下线 1:启用
     */
    @TableLogic
    private Boolean deleted;

}
package com.dk.data.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 微信用户 Add Entity
*
* @author ban
* @date 2018/12/10
*/
@Getter
@Setter
@ToString
public class AddCouponDto {

    /**
     * 优惠券名
     */
    @NotBlank(message = "优惠券名不能为空")
    private String name;

    /**
     * 用户uuid
     */
    @NotBlank(message = "用户uuid不能为空")
    private String user;

    /**
     * 最小订单金额
     */
    @NotNull(message = "最小订单金额不能为空")
    private BigDecimal minPrice;

    /**
     * 优惠金额
     */
    @NotNull(message = "优惠金额不能为空")
    private BigDecimal couponPrice;

    /**
     * 兑换所需积分
     */
    @NotBlank(message = "兑换所需积分不能为空")
    private Long needIntegral;

    /**
     * 优惠券类型1:满减优惠券 2:服务抵用券
     */
    @NotNull(message = "优惠券类型1:满减优惠券 2:服务抵用券不能为空")
    private Boolean type;

    /**
     * 适用范围(0表示不通用仅指定医院使用 1表示通用)
     */
    @NotBlank(message = "适用范围(0表示不通用仅指定医院使用 1表示通用)不能为空")
    private String application;

    /**
     * 医院uuid
     */
    @NotNull(message = "医院uuid不能为空")
    private Long hospital;

    /**
     * 开始时间
     */
    @NotNull(message = "开始时间不能为空")
    private Date beginTime;

    /**
     * 过期时间
     */
    @NotNull(message = "过期时间不能为空")
    private Date expireTime;

    /**
     * 操作人t_user
     */
    @NotBlank(message = "操作人t_user不能为空")
    private String operator;

    /**
     * 描述
     */
    @NotBlank(message = "描述不能为空")
    private String description;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空")
    private String remark;

    /**
     * 0: 未使用 1:已使用
     */
    @NotNull(message = "0: 未使用 1:已使用不能为空")
    private Byte status;

    /**
     * 预留
     */
    @NotBlank(message = "预留不能为空")
    private String reserve;

}
package com.dk.data.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 订单 Edit Entity
*
* @author ban
* @date 2018/12/05
*/
@Getter
@Setter
@ToString
public class EditOrderDto {

    /**
     * uuid
     */
    @NotNull(message = "uuid不能为空")
    private String uuid;

    /**
     * 主键
     */
//    @NotNull(message = "主键不能为空")
    private Long id;

    /**
     * 订单编号
     */
//    @NotBlank(message = "订单编号不能为空")
    private String orderNo;

    /**
     * 用户uuid
     */
//    @NotBlank(message = "用户uuid不能为空")
    private String user;

    /**
     * 项目uuid
     */
//    @NotBlank(message = "项目uuid不能为空")
    private String project;

    /**
     * 医院uuid
     */
    private String hospital;

    /**
     * 项目数量
     */
//    @NotNull(message = "项目数量不能为空")
    private Integer projectCount;

    /**
     * 订单状态（1:待付款 2:待预约 3:进行中 4:已完成 5:待退款 6:已退款）
     */
//    @NotBlank(message = "订单状态（1:待付款 2:待预约 3:进行中 4:已完成 5:待退款 6:已退款）不能为空")
    private String orderStatus;

    /**
     * 服务状态(0:已接单 1:已采样 2:已送检 3:已上传报告)
     */
//    @NotBlank(message = "服务状态(0:已接单 1:已采样 2:已送检 3:已上传报告)不能为空")
    private String serviceStatus;

    /**
     * 采样凭证
     */
    private String samplingProof;

    /**
     * 接单时间
     */
//    @NotNull(message = "接单时间不能为空")
    private Date receiveTime;

    /**
     * 采样时间
     */
//    @NotNull(message = "采样时间不能为空")
    private Date samplingTime;

    /**
     * 送检时间
     */
//    @NotNull(message = "送检时间不能为空")
    private Date testTime;

    /**
     * 上传报告时间
     */
//    @NotNull(message = "上传报告时间不能为空")
    private Date uploadReportTime;

    /**
     * 订单地址
     */
//    @NotBlank(message = "订单地址不能为空")
    private String address;

    /**
     * 就医人uuid
     */
//    @NotBlank(message = "就医人uuid不能为空")
    private String patient;

    /**
     * 订单地址状态(0 表示三环内, 1表示三环外)
     */
//    @NotBlank(message = "订单地址状态(0 表示三环内, 1表示三环外)不能为空")
    private String scope;

    /**
     * 纸质报告(0 表示不需要, 1表示需要)
     */
//    @NotBlank(message = "纸质报告(0 表示不需要, 1表示需要)不能为空")
    private String paperReport;

    /**
     * 优惠券uuid
     */
//    @NotBlank(message = "优惠券uuid不能为空")
    private String coupon;

    /**
     * 项目金额
     */
//    @NotNull(message = "项目金额不能为空")
    private BigDecimal projectPrice;

    /**
     * 服务金额
     */
//    @NotNull(message = "服务金额不能为空")
    private BigDecimal servicePrice;

    /**
     * 优惠金额
     */
//    @NotNull(message = "优惠金额不能为空")
    private BigDecimal couponPrice;

    /**
     * 订单金额
     */
//    @NotNull(message = "订单金额不能为空")
    private BigDecimal price;

    /**
     * 改约金额
     */
    private Double reschedulePrice;

    /**
     * 改约时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date rescheduleTime;

    /**
     * 改约次数
     */
    private Integer rescheduleCount;

    /**
     * 重新预约金额
     */
    private BigDecimal rebookPrice;

    /**
     * 可退款金额
     */
    private BigDecimal refundPrice;

    /**
     * 订单退款申请时间
     */
//    @NotNull(message = "订单退款申请时间不能为空")
    private Date applicationRefundTime;

    /**
     * 订单退款时间
     */
//    @NotNull(message = "订单退款时间不能为空")
    private Date refundTime;

    /**
     * 退款原因
     */
    private String refundCause;

    /**
     * 注意事项
     */
    private String notes;

    /**
     * 用户备注
     */
//    @NotBlank(message = "用户备注不能为空")
    private String userRemark;

    /**
     * 操作人
     */
//    @NotBlank(message = "操作人不能为空")
    private String operator;

    /**
     * 描述
     */
//    @NotBlank(message = "描述不能为空")
    private String description;

    /**
     * 备注
     */
//    @NotBlank(message = "备注不能为空")
    private String remark;

    /**
     * 状态
     */
//    @NotNull(message = "状态不能为空")
    private Byte status;

    /**
     * 预留
     */
//    @NotBlank(message = "预留不能为空")
    private String reserve;

}
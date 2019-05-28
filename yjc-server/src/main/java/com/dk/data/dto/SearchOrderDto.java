package com.dk.data.dto;

import cn.sourcespro.commons.data.dto.PageDto;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 订单 Search Condition Entity
*
* @author ban
* @date 2018/12/05
*/
@Getter
@Setter
@ToString
public class SearchOrderDto extends PageDto {

    /**
     * 订单uuid
     */
    private String uuid;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 用户uuid
     */
    private String user;

    /**
     * 项目uuid
     */
    private String project;

    /**
     * 医院uuid
     */
    private String hospital;

    /**
     * 项目数量
     */
    private Integer projectCount;

    /**
     * 订单状态（1:待付款 2:待预约 3:进行中 4:已完成 5:待退款 6:已退款）
     */
    private String orderStatus;

    /**
     * 服务状态(0:已接单 1:已采样 2:已送检 3:已上传报告)
     */
    private String serviceStatus;

    /**
     * 采样凭证
     */
    private String samplingProof;

    /**
     * 接单时间
     */
    private Date receiveTime;

    /**
     * 采样时间
     */
    private Date samplingTime;

    /**
     * 送检时间
     */
    private Date testTime;

    /**
     * 上传报告时间
     */
    private Date uploadReportTime;

    /**
     * 订单地址
     */
    private String address;

    /**
     * 就医人uuid
     */
    private String patient;

    /**
     * 订单地址状态(0 表示三环内, 1表示三环外)
     */
    private String scope;

    /**
     * 纸质报告(0 表示不需要, 1表示需要)
     */
    private String paperReport;

    /**
     * 优惠券uuid
     */
    private String coupon;

    /**
     * 项目金额
     */
    private BigDecimal projectPrice;

    /**
     * 服务金额
     */
    private BigDecimal servicePrice;

    /**
     * 优惠金额
     */
    private BigDecimal couponPrice;

    /**
     * 订单金额
     */
    private BigDecimal price;

    /**
     * 改约金额
     */
    private BigDecimal reschedulePrice;

    /**
     * 改约时间
     */
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
    private Date applicationRefundTime;

    /**
     * 订单退款时间
     */
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
    private String userRemark;

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
     * 描述
     */
    private String description;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态
     */
    private Byte status;

    /**
     * 预留
     */
    private String reserve;

}
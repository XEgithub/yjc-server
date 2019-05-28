package com.dk.data.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 订单 Entity
*
* @author ban
* @date 2018/12/05
*/
@Getter
@Setter
@ToString
@TableName("order_list")
public class Order {

    /**
     * 主键
     */
    private Long id;

    /**
     * uuid
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
     * 服务状态(0:未接单 1:已接单 2:已采样 3:已送检 4:已上传报告)
     */
    private String serviceStatus;

    /**
     * 采样凭证
     */
    private String samplingProof;

    /**
     * 接单时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date receiveTime;

    /**
     * 采样时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date samplingTime;

    /**
     * 送检时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date testTime;

    /**
     * 上传报告时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
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
//    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal reschedulePrice;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date applicationRefundTime;

    /**
     * 订单退款时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
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

    /**
     * 0:删除 1:未删除
     */
    @TableLogic
    private Boolean deleted;

    /**
     * 预约uuid
     */
    @TableField(exist = false)
    private String appointment;

}
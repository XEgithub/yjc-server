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
* 预约 Entity
*
* @author ban
* @date 2018/12/12
*/
@Getter
@Setter
@ToString
@TableName("appointment")
public class Appointment {

    @TableField(exist = false)
    private String nurse;

    @TableField(exist = false)
    private String nursePhone;

    /**
     * 主键
     */
    private Long id;

    /**
     * uuid
     */
    private String uuid;

    /**
     * 订单uuid
     */
    private String orderList;

    /**
     * 服务类型 0:上门采样 1:陪诊
     */
    private String type;

    /**
     * 医院
     */
    private String hospital;

    /**
     * 地址
     */
    private String address;

    /**
     * 用户备注
     */
    private String userRemark;

    /**
     * 注意事项
     */
    private String notes;

    /**
     * 采样凭证
     */
    private String evidence;

    /**
     * 预约次数
     */
    private Integer total;

    /**
     * 改约金额
     */
    private BigDecimal reschedulePrice;

    /**
     * 预约时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date appointmentTime;

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
     * 备注
     */
    private String remark;

    /**
     * 状态（0=预约中，1=已预约）
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
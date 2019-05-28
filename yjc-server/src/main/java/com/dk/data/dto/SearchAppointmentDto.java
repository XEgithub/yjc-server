package com.dk.data.dto;

import cn.sourcespro.commons.data.dto.PageDto;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 预约 Search Condition Entity
*
* @author ban
* @date 2018/12/12
*/
@Getter
@Setter
@ToString
public class SearchAppointmentDto extends PageDto {

    /**
     * 用户uuid
     */
    private String user;

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
    private Date appointmentTime;

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
     * 状态（0=预约中，1=已预约 2=已取消）
     */
    private Byte status;

    /**
     * 预留
     */
    private String reserve;

}
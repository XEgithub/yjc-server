package com.dk.data.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

/**
* 预约 Add Entity
*
* @author ban
* @date 2018/12/12
*/
@Getter
@Setter
@ToString
public class AddAppointmentDto {

    /**
     * 订单uuid
     */
    @NotBlank(message = "订单uuid不能为空")
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
//    @NotBlank(message = "注意事项不能为空")
    private String notes;

    /**
     * 采样凭证
     */
    private String evidence;

    /**
     * 预约次数
     */
//    @NotBlank(message = "预约次数不能为空")
    private Integer total;

    /**
     * 改约金额
     */
    private BigDecimal reschedulePrice;

    /**
     * 预约时间
     */
    @NotNull(message = "预约时间不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date appointmentTime;

    /**
     * 操作人
     */
//    @NotBlank(message = "操作人不能为空")
    private String operator;

    /**
     * 备注
     */
//    @NotBlank(message = "备注不能为空")
    private String remark;

    /**
     * 状态（0=预约中，1=已预约）
     */
//    @NotNull(message = "状态（0=预约中，1=已预约 2=已取消）")
    private Byte status;

    /**
     * 预留
     */
//    @NotBlank(message = "预留不能为空")
    private String reserve;

}
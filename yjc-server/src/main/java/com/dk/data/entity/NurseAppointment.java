package com.dk.data.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 护士预约关系表 Entity
*
* @author ban
* @date 2019/01/10
*/
@Getter
@Setter
@ToString
@TableName("nurse_appointment")
public class NurseAppointment {

    /**
     * id
     */
    private Long id;

    /**
     * uuid
     */
    private String uuid;

    /**
     * 护士
     */
    private String nurse;

    /**
     * 预约
     */
    private String appointment;

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
     * 状态 0:派单中 1:已拒绝 2:已超时 3:用户取消 4:护士取消 5:已完成
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

}
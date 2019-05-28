package com.dk.data.dto;

import cn.sourcespro.commons.data.dto.PageDto;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 护士预约关系表 Search Condition Entity
*
* @author ban
* @date 2019/01/10
*/
@Getter
@Setter
@ToString
public class SearchNurseAppointmentDto extends PageDto {

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
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 状态 0:派单中 1:已拒绝 2:已超时 3:用户取消 4:护士取消 5:已完成
     */
    private Byte status;

    /**
     * 预留
     */
    private String reserve;

}
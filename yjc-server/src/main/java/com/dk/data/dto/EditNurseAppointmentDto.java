package com.dk.data.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 护士预约关系表 Edit Entity
*
* @author ban
* @date 2019/01/10
*/
@Getter
@Setter
@ToString
public class EditNurseAppointmentDto {

    /**
     * id
     */
    @NotNull(message = "id不能为空")
    private Long id;

    /**
     * 护士
     */
    @NotBlank(message = "护士不能为空")
    private String nurse;

    /**
     * 预约
     */
    @NotBlank(message = "预约不能为空")
    private String appointment;

    /**
     * 状态 0:派单中 1:已拒绝 2:已超时 3:用户取消 4:护士取消 5:已完成
     */
    @NotNull(message = "状态 0:派单中 1:已拒绝 2:已超时 3:用户取消 4:护士取消 5:已完成不能为空")
    private Byte status;

    /**
     * 预留
     */
    @NotBlank(message = "预留不能为空")
    private String reserve;

}
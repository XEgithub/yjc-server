package com.dk.data.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 条码 Add Entity
*
* @author ban
* @date 2019/01/10
*/
@Getter
@Setter
@ToString
public class AddBarcodeDto {

    /**
     * 预约
     */
    @NotBlank(message = "预约不能为空")
    private String appointment;

    /**
     * 采血管
     */
    @NotBlank(message = "采血管不能为空")
    private String tube;

    /**
     * 条形码
     */
    @NotBlank(message = "条形码不能为空")
    private String barcoding;

    /**
     * 状态
     */
    @NotNull(message = "状态不能为空")
    private Byte status;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空")
    private String remark;

    /**
     * 预留
     */
    @NotBlank(message = "预留不能为空")
    private String reserve;

}
package com.dk.data.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 护士流水表 Edit Entity
*
* @author ban
* @date 2019/01/10
*/
@Getter
@Setter
@ToString
public class EditBillDto {

    /**
     * id
     */
    @NotNull(message = "id不能为空")
    private Long id;

    /**
     * 护士uuid
     */
    @NotBlank(message = "护士uuid不能为空")
    private String nurse;

    /**
     * 预约uuid
     */
    @NotBlank(message = "预约uuid不能为空")
    private String appointment;

    /**
     * 名称
     */
    @NotBlank(message = "名称不能为空")
    private String name;

    /**
     * 金额
     */
    @NotNull(message = "金额不能为空")
    private BigDecimal price;

    /**
     * 类型(0:收入 1:支出)
     */
    @NotBlank(message = "类型(0:收入 1:支出)不能为空")
    private String type;

    /**
     * 余额
     */
    @NotNull(message = "余额不能为空")
    private BigDecimal balance;

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
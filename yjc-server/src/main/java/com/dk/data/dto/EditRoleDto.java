package com.dk.data.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 角色 Edit Entity
*
* @author ban
* @date 2018/11/28
*/
@Getter
@Setter
@ToString
public class EditRoleDto {

    /**
     * id
     */
    @NotNull(message = "id不能为空")
    private Long id;

    /**
     * 名称
     */
    @NotBlank(message = "名称不能为空")
    private String name;

    /**
     * 角色标识
     */
    @NotBlank(message = "角色标识不能为空")
    private String role;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空")
    private String remark;

}
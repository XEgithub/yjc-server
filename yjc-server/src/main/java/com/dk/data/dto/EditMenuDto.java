package com.dk.data.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 菜单 Edit Entity
*
* @author ban
* @date 2018/11/28
*/
@Getter
@Setter
@ToString
public class EditMenuDto {

    /**
     * 名称
     */
    @NotBlank(message = "名称不能为空")
    private String name;

    /**
     * 路径
     */
    @NotBlank(message = "路径不能为空")
    private String url;

    /**
     * 图标
     */
    @NotBlank(message = "图标不能为空")
    private String icon;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空")
    private String remark;

    /**
     * 排序
     */
    @NotNull(message = "排序不能为空")
    private Integer sortt;

}
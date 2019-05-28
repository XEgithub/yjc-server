package com.dk.data.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 轮播图 Edit Entity
*
* @author ban
* @date 2018/12/05
*/
@Getter
@Setter
@ToString
public class EditShoppingCartDto {

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空")
    private Long id;

    /**
     * 用户
     */
    @NotBlank(message = "用户不能为空")
    private String user;

    /**
     * 项目
     */
    @NotBlank(message = "项目不能为空")
    private String project;

    /**
     * 项目类型(0表示单项目, 1表示组合项目)
     */
    @NotBlank(message = "项目类型(0表示单项目, 1表示组合项目)不能为空")
    private String projectType;

    /**
     * 状态
     */
    @NotNull(message = "状态不能为空")
    private Boolean status;

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
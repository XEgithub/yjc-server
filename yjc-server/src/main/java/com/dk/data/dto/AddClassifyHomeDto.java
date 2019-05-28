package com.dk.data.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 轮播图 Add Entity
*
* @author ban
* @date 2018/12/04
*/
@Getter
@Setter
@ToString
public class AddClassifyHomeDto {

    /**
     * id
     */
    @NotNull(message = "id不能为空")
    private Long id;

    /**
     * uuid
     */
    @NotNull(message = "uuid不能为空")
    private String uuid;

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
     * 父id
     */
    @NotNull(message = "父id不能为空")
    private Long pid;

    /**
     * 左值
     */
    @NotNull(message = "左值不能为空")
    private Integer lft;

    /**
     * 右值
     */
    @NotNull(message = "右值不能为空")
    private Integer rgt;

    /**
     * 路径ids
     */
    @NotBlank(message = "路径ids不能为空")
    private String ids;

    /**
     * 叶子节点
     */
    @NotNull(message = "叶子节点不能为空")
    private Byte leaf;

    /**
     * 排序
     */
    @NotNull(message = "排序不能为空")
    private Integer sortt;

}
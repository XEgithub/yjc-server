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
* @date 2018/12/04
*/
@Getter
@Setter
@ToString
public class EditCarouselDto {

    /**
     * id
     */
    @NotNull(message = "id不能为空")
    private Long id;

    /**
     * 图片
     */
    @NotBlank(message = "图片不能为空")
    private String image;

    /**
     * 跳转链接
     */
    @NotBlank(message = "跳转链接不能为空")
    private String url;

    /**
     * 0:轮播图 1:固定显示图
     */
    @NotNull(message = "0:轮播图 1:固定显示图不能为空")
    private Byte alone;

//    /**
//     * 操作人
//     */
//    @NotBlank(message = "操作人不能为空")
//    private String operator;

//    /**
//     * 0:禁用 1:启用
//     */
//    @NotNull(message = "0:禁用 1:启用不能为空")
//    private Byte status;

    /**
     * 备注
     */
//    @NotBlank(message = "备注不能为空")
    private String remark;

}
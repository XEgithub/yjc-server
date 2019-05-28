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
public class EditProjectsHomeDto {

    /**
     * id
     */
    @NotNull(message = "id不能为空")
    private Long id;

    /**
     * 项目uuid
     */
    @NotBlank(message = "项目uuid不能为空")
    private String project;

    /**
     * 单项目/组合项目 project/group
     */
    @NotBlank(message = "单项目/组合项目 project/group不能为空")
    private String type;

    /**
     * 分类uuid
     */
    @NotBlank(message = "分类uuid不能为空")
    private String classify;

}
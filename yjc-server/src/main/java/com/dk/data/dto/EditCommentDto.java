package com.dk.data.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 评论 Edit Entity
*
* @author ban
* @date 2018/12/12
*/
@Getter
@Setter
@ToString
public class EditCommentDto {

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空")
    private Long id;

    /**
     * 订单uuid
     */
    @NotBlank(message = "订单uuid不能为空")
    private String orderList;

    /**
     * 项目uuid
     */
    @NotBlank(message = "项目uuid不能为空")
    private String project;

    /**
     * 项目类型
     */
    private String type;

    /**
     * 用户uuid
     */
    @NotBlank(message = "用户uuid不能为空")
    private String user;

    /**
     * 内容
     */
    @NotBlank(message = "内容不能为空")
    private String content;

    /**
     * 项目指星(1-5星)
     */
    @NotBlank(message = "项目指星(1-5星)不能为空")
    private String grade;

    /**
     * 就医人
     */
    @NotBlank(message = "就医人不能为空")
    private String patient;

    /**
     * 护士
     */
    @NotBlank(message = "护士不能为空")
    private String nurse;

    /**
     * 护士手机号
     */
    private String nursePhone;

    /**
     * 图片
     */
    @NotBlank(message = "图片不能为空")
    private String image;

    /**
     * 描述
     */
    @NotBlank(message = "描述不能为空")
    private String description;

    /**
     * 状态
     */
    @NotNull(message = "状态不能为空")
    private Byte status;

    /**
     * 预留
     */
    @NotBlank(message = "预留不能为空")
    private String reserve;

}
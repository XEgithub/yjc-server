package com.dk.data.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 关注 Add Entity
*
* @author ban
* @date 2018/12/12
*/
@Getter
@Setter
@ToString
public class AddConcernDto {

    /**
     * 用户uuid
     */
//    @NotBlank(message = "用户uuid不能为空")
    private String user;

    /**@PathVariable
     * 项目uuid
     */
    @NotBlank(message = "项目uuid不能为空")
    private String project;

    /**
     * 项目类型(project表示单项目 groupproject表示组合项目)
     */
    @NotBlank(message = "项目类型(0表示单项目 1表示组合项目)不能为空")
    private String type;

    /**
     * 医院uuid
     */
    @NotBlank(message = "医院uuid不能为空")
    private String hospital;

    /**
     * 项目图片
     */
//    @NotBlank(message = "项目图片不能为空")
    private String image;

    /**
     * 项目名称
     */
//    @NotBlank(message = "项目名称不能为空")
    private String name;

    /**
     * 项目原价
     */
//    @NotNull(message = "项目原价不能为空")
    private BigDecimal oldPrice;

    /**
     * 项目现价
     */
//    @NotNull(message = "项目现价不能为空")
    private BigDecimal nowPrice;

    /**
     * 项目描述
     */
//    @NotBlank(message = "项目描述不能为空")
    private String description;

    /**
     * 关注时间
     */
//    @NotNull(message = "关注时间不能为空")
    private Date concernTime;

    /**
     * 取消关注时间
     */
//    @NotNull(message = "取消关注时间不能为空")
    private Date cancelTime;

    /**
     * 状态
     */
    @NotNull(message = "状态不能为空")
    private Byte status;

    /**
     * 预留
     */
//    @NotBlank(message = "预留不能为空")
    private String reserve;

}
package com.dk.data.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
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
public class AddProjectDto {

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空")
    private Long id;

    /**
     * 主键
     */
    @NotNull(message = "uuid不能为空")
    private String uuid;

    /**
     * logo
     */
    @NotBlank(message = "logo不能为空")
    private String logo;

    /**
     * 项目类型(0表示单项目 1表示组合项目)
     */
    @NotBlank(message = "项目类型(0表示单项目 1表示组合项目)不能为空")
    private String type;

    /**
     * 背景
     */
    @NotBlank(message = "背景不能为空")
    private String banner;

    /**
     * 列表图片
     */
    @NotBlank(message = "列表图片不能为空")
    private String image;

    /**
     * 图片1
     */
    @NotBlank(message = "图片1不能为空")
    private String image1;

    /**
     * 图片2
     */
    @NotBlank(message = "图片2不能为空")
    private String image2;

    /**
     * 图片3
     */
    @NotBlank(message = "图片3不能为空")
    private String image3;

    /**
     * 名称
     */
    @NotBlank(message = "名称不能为空")
    private String name;

    /**
     * 医院uuid
     */
    @NotBlank(message = "医院uuid不能为空")
    private String hospital;

    /**
     * 原价
     */
    @NotNull(message = "原价不能为空")
    private BigDecimal oldPrice;

    /**
     * 现价
     */
    @NotNull(message = "现价不能为空")
    private BigDecimal nowPrice;

    /**
     * 周期
     */
    @NotBlank(message = "周期不能为空")
    private String period;

    /**
     * 采样管
     */
    private String samplingPipe;

    /**
     * 注意事项
     */
    @NotBlank(message = "注意事项不能为空")
    private String notes;

    /**
     * 首页分类uuid
     */
    @NotBlank(message = "首页分类uuid不能为空")
    private String classify1;

    /**
     * 导航分类uuid
     */
    @NotBlank(message = "导航分类uuid不能为空")
    private String classify2;

    /**
     * 关键词
     */
    @NotBlank(message = "关键词不能为空")
    private String keyword;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空")
    private String remark;

    /**
     * 备注1
     */
    @NotBlank(message = "备注1不能为空")
    private String remark1;

    /**
     * 操作人
     */
    @NotBlank(message = "操作人不能为空")
    private String operator;

    /**
     * 描述
     */
    @NotBlank(message = "描述不能为空")
    private String description;

    /**
     * 详情
     */
    @NotBlank(message = "详情不能为空")
    private String detail;

    /**
     * 状态（下线=0，审核=1，审核失败=2，上线=3）
     */
    @NotNull(message = "状态（下线=0，审核=1，审核失败=2，上线=3）不能为空")
    private Byte status;

    /**
     * 预留
     */
    @NotBlank(message = "预留不能为空")
    private String reserve;

}
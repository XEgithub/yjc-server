package com.dk.data.dto;

import cn.sourcespro.commons.data.dto.PageDto;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 轮播图 Search Condition Entity
*
* @author ban
* @date 2018/12/04
*/
@Getter
@Setter
@ToString
public class SearchProjectDto extends PageDto {

    /**
     * 用户
     */
    private String user;

    /**
     * 主键
     */
    private Long id;

    /**
     * uuid
     */
    private String uuid;

    /**
     * logo
     */
    private String logo;

    /**
     * 项目类型(0表示单项目 1表示组合项目)
     */
    private String type;

    /**
     * 背景
     */
    private String banner;

    /**
     * 列表图片
     */
    private String image;

    /**
     * 图片1
     */
    private String image1;

    /**
     * 图片2
     */
    private String image2;

    /**
     * 图片3
     */
    private String image3;

    /**
     * 名称
     */
    private String name;

    /**
     * 医院uuid
     */
    private String hospital;

    /**
     * 原价
     */
    private BigDecimal oldPrice;

    /**
     * 现价
     */
    private BigDecimal nowPrice;

    /**
     * 周期
     */
    private String period;

    /**
     * 采样管
     */
    private String samplingPipe;

    /**
     * 注意事项
     */
    private String notes;

    /**
     * 首页分类uuid
     */
    private String classify1;

    /**
     * 导航分类uuid
     */
    private String classify2;

    /**
     * 关键词
     */
    private String keyword;

    /**
     * 备注
     */
    private String remark;

    /**
     * 备注1
     */
    private String remark1;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 描述
     */
    private String description;

    /**
     * 详情
     */
    private String detail;

    /**
     * 状态（下线=0，审核=1，审核失败=2，上线=3）
     */
    private Byte status;

    /**
     * 预留
     */
    private String reserve;

}
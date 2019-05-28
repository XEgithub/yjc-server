package com.dk.data.dto;

import cn.sourcespro.commons.data.dto.PageDto;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 关注 Search Condition Entity
*
* @author ban
* @date 2018/12/12
*/
@Getter
@Setter
@ToString
public class SearchConcernDto extends PageDto {

    /**
     * 用户uuid
     */
    private String user;

    /**
     * 项目uuid
     */
    private String project;

    /**
     * 项目类型(project表示单项目 groupproject表示组合项目)
     */
    private String type;

    /**
     * 医院uuid
     */
    private String hospital;

    /**
     * 项目图片
     */
    private String image;

    /**
     * 项目名称
     */
    private String name;

    /**
     * 项目原价
     */
    private BigDecimal oldPrice;

    /**
     * 项目现价
     */
    private BigDecimal nowPrice;

    /**
     * 项目描述
     */
    private String description;

    /**
     * 关注时间
     */
    private Date concernTime;

    /**
     * 取消关注时间
     */
    private Date cancelTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 状态
     */
    private Byte status;

    /**
     * 预留
     */
    private String reserve;

}
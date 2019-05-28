package com.dk.data.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 关注 Entity
*
* @author ban
* @date 2018/12/12
*/
@Getter
@Setter
@ToString
@TableName("concern")
public class Concern {

    /**
     * 主键
     */
    private Long id;

    /**
     * uuid
     */
    private String uuid;

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
     * 医院名
     */
    @TableField(exist = false)
    private String hospitalName;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date concernTime;

    /**
     * 取消关注时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date cancelTime;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date updateTime;

    /**
     * 状态
     */
    private Byte status;

    /**
     * 0:下线 1:启用
     */
    @TableLogic
    private Boolean deleted;

    /**
     * 预留
     */
    private String reserve;

}
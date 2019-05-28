package com.dk.data.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 购物车 Entity
*
* @author ban
* @date 2018/12/05
*/
@Getter
@Setter
@ToString
@TableName("shopping_cart")
public class ShoppingCart {

    /**
     * 主键
     */
    private Long id;

    /**
     * uuid
     */
    private String uuid;

    /**
     * 用户
     */
    private String user;

    /**
     * 项目
     */
    private String project;

    /**
     * 项目类型(0表示单项目, 1表示组合项目)
     */
    private String projectType;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date updateTime;

    /**
     * 状态
     */
    private Boolean status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 预留
     */
    private String reserve;

    /**
     * 0:上线 1:下线
     */
    @TableLogic
    private Boolean deleted;

    @TableField(exist=false)
    private Integer exist;

}
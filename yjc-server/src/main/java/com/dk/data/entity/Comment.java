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
* 评论 Entity
*
* @author ban
* @date 2018/12/12
*/
@Getter
@Setter
@ToString
@TableName("comment")
public class Comment {

    /**
     * 头像
     */
    @TableField(exist = false)
    private String headimgurl;

    /**
     * 主键
     */
    private Long id;

    /**
     * uuid
     */
    private String uuid;

    /**
     * 订单uuid
     */
    private String orderList;

    /**
     * 项目uuid
     */
    private String project;

    /**
     * 项目类型
     */
    private String type;

    /**
     * 用户uuid
     */
    private String user;

    /**
     * 内容
     */
    private String content;

    @TableField(exist = false)
    private String[] contents;

    /**
     * 项目指星(1-5星)
     */
    private String grade;

    /**
     * 就医人
     */
    private String patient;

    /**
     * 护士
     */
    private String nurse;

    /**
     * 护士手机号
     */
    private String nursePhone;

    /**
     * 图片
     */
    private String image;

    @TableField(exist = false)
    private String[] images;

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
     * 描述
     */
    private String description;

    /**
     * 状态
     */
    private Byte status;

    /**
     * 预留
     */
    private String reserve;

    /**
     * 0:下线 1:启用
     */
    @TableLogic
    private Boolean deleted;

}
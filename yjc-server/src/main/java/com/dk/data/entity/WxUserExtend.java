package com.dk.data.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 微信用户扩展 Entity
*
* @author ban
* @date 2018/12/27
*/
@Getter
@Setter
@ToString
@TableName("wx_user_extend")
public class WxUserExtend {

    /**
     * 主键
     */
    private Long id;

    /**
     * uuid
     */
    private String uuid;

    /**
     * wx_userUuid
     */
    private String wxUser;

    /**
     * 头像
     */
    private String image;

    /**
     * 姓名
     */
    private String name;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 性别
     */
    private String sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 体重
     */
    private String weight;

    /**
     * 身高
     */
    private String height;

    /**
     * 手机
     */
    private String phone;

    /**
     * 生日
     */
    private String birthday;

    /**
     * 地址
     */
    private String address;

    /**
     * 身份证
     */
    private String idCard;

    /**
     * 身份
     */
    private String identity;

    /**
     * 来源
     */
    private String source;

    /**
     * 积分
     */
    private Long integral;

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
     * 操作人
     */
    private String operator;

    /**
     * 描述
     */
    private String description;

    /**
     * 状态
     */
    private Byte status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 预留
     */
    private String reserve;

    /**
     * 
     */
    @TableLogic
    private Boolean deleted;

}
package com.dk.data.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 用户 Entity
*
* @author ban
* @date 2018/11/26
*/
@Getter
@Setter
@ToString
public class User {

    /**
     * id
     */
    private Long id;

    /**
     * uuid
     */
    private String uuid;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 用户名
     */
    private String username;

    /**
     * 性别：0:男 1:女 2:未知
     */
    private Byte sex;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 手机
     */
    private String phone;

    /**
     * 电话
     */
    private String tel;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 身份证
     */
    private String idcard;

    /**
     * 生日
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date birthday;

    /**
     * 地区
     */
    private Long area;

    /**
     * 签名
     */
    private String autograph;

    /**
     * 微信openid
     */
    private String openid;

    /**
     * 状态：0:未激活 1:激活
     */
    private Byte active;

    /**
     * 推荐人
     */
    private Long referee;

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
     * 最后登录时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date lastLoginTime;

    /**
     * 状态
     */
    @TableLogic
    private Boolean deleted;

}
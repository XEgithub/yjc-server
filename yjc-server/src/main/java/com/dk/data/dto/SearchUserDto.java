package com.dk.data.dto;

import cn.sourcespro.commons.data.dto.PageDto;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 用户 Search Condition Entity
*
* @author ban
* @date 2018/11/26
*/
@Getter
@Setter
@ToString
public class SearchUserDto extends PageDto {

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
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 最后登录时间
     */
    private Date lastLoginTime;

}
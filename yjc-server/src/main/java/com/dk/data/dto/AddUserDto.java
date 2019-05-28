package com.dk.data.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 用户 Add Entity
*
* @author ban
* @date 2018/11/26
*/
@Getter
@Setter
@ToString
public class AddUserDto {

    /**
     * 昵称
     */
    @NotBlank(message = "昵称不能为空")
    private String nickname;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 性别：0:男 1:女 2:未知
     */
    @NotNull(message = "性别：0:男 1:女 2:未知不能为空")
    private Byte sex;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 头像
     */
    @NotBlank(message = "头像不能为空")
    private String avatar;

    /**
     * 手机
     */
    @NotBlank(message = "手机不能为空")
    private String phone;

    /**
     * 电话
     */
    @NotBlank(message = "电话不能为空")
    private String tel;

    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱不能为空")
    private String email;

    /**
     * 身份证
     */
    @NotBlank(message = "身份证不能为空")
    private String idcard;

    /**
     * 生日
     */
    @NotNull(message = "生日不能为空")
    private Date birthday;

    /**
     * 地区
     */
    @NotNull(message = "地区不能为空")
    private Long area;

    /**
     * 签名
     */
    @NotBlank(message = "签名不能为空")
    private String autograph;

    /**
     * 微信openid
     */
    @NotBlank(message = "微信openid不能为空")
    private String openid;

    /**
     * 状态：0:未激活 1:激活
     */
    @NotNull(message = "状态：0:未激活 1:激活不能为空")
    private Byte active;

    /**
     * 推荐人
     */
    @NotNull(message = "推荐人不能为空")
    private Long referee;

    /**
     * 最后登录时间
     */
    @NotNull(message = "最后登录时间不能为空")
    private Date lastLoginTime;

}
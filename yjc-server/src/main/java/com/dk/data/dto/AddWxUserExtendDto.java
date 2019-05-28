package com.dk.data.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

/**
* 微信用户扩展 Add Entity
*
* @author ban
* @date 2018/12/27
*/
@Getter
@Setter
@ToString
public class AddWxUserExtendDto {

    /**
     * wx_userUuid
     */
    @NotBlank(message = "wx_userUuid不能为空")
    private String wxUser;

    /**
     * 头像
     */
    @NotBlank(message = "头像不能为空")
    private String image;

    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空")
    private String name;

    /**
     * 昵称
     */
    @NotBlank(message = "昵称不能为空")
    private String nickname;

    /**
     * 性别
     */
    @NotBlank(message = "性别不能为空")
    private String sex;

    /**
     * 年龄
     */
    @NotNull(message = "年龄不能为空")
    private Integer age;

    /**
     * 体重
     */
    @NotBlank(message = "体重不能为空")
    private String weight;

    /**
     * 身高
     */
    @NotBlank(message = "身高不能为空")
    private String height;

    /**
     * 手机
     */
    @NotBlank(message = "手机不能为空")
    private String phone;

    /**
     * 生日
     */
    @NotBlank(message = "生日不能为空")
    private String birthday;

    /**
     * 地址
     */
    @NotBlank(message = "地址不能为空")
    private String address;

    /**
     * 身份证
     */
    @NotBlank(message = "身份证不能为空")
    private String idCard;

    /**
     * 身份
     */
    @NotBlank(message = "身份不能为空")
    private String identity;

    /**
     * 来源
     */
    @NotBlank(message = "来源不能为空")
    private String source;

    /**
     * 积分
     */
    @NotNull(message = "积分不能为空")
    private Long integral;

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
     * 状态
     */
    @NotNull(message = "状态不能为空")
    private Byte status;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空")
    private String remark;

    /**
     * 预留
     */
    @NotBlank(message = "预留不能为空")
    private String reserve;

}
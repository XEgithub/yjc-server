package com.dk.data.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 患者 Edit Entity
*
* @author ban
* @date 2018/12/05
*/
@Getter
@Setter
@ToString
public class EditPatientDto {

    /**
     * 主键
     */
//    @NotNull(message = "主键不能为空")
    private Long id;

    /**
     * 用户
     */
//    @NotBlank(message = "用户不能为空")
    private String user;

    /**
     * 就医人uuid
     */
    @NotBlank(message = "就医人uuid不能为空")
    private String uuid;

    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空")
    private String name;

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
//    @NotBlank(message = "体重不能为空")
    private String weight;

    /**
     * 身高
     */
//    @NotBlank(message = "身高不能为空")
    private String height;

    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空")
    private String phone;

    /**
     * 生日
     */
//    @NotNull(message = "生日不能为空")
    private Date birthday;

    /**
     * 地址
     */
    @NotBlank(message = "地址不能为空")
    private String address;

    /**
     * 证件类型
     */
    @NotBlank(message = "证件类型不能为空")
    private String certificatesType;

    /**
     * 证件号码
     */
    @NotBlank(message = "证件号码不能为空")
    private String certificatesNo;

    /**
     * 是否默认(0表示不默认, 1表示默认)
     */
//    @NotBlank(message = "是否默认(0表示不默认, 1表示默认)不能为空")
    private String first;

    /**
     * 描述
     */
//    @NotBlank(message = "描述不能为空")
    private String description;

    /**
     * 状态
     */
//    @NotNull(message = "状态不能为空")
    private Byte status;

    /**
     * 备注
     */
//    @NotBlank(message = "备注不能为空")
    private String remark;

    /**
     * 预留
     */
//    @NotBlank(message = "预留不能为空")
    private String reserve;

}
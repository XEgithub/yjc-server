package com.dk.data.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 护士 Edit Entity
*
* @author ban
* @date 2019/01/10
*/
@Getter
@Setter
@ToString
public class EditNurseDto {

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空")
    private Long id;

    /**
     * wxUser
     */
    private String wxUser;

    /**
     * 昵称
     */
    @NotBlank(message = "昵称不能为空")
    private String nickName;

    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空")
    private String name;

    /**
     * 年龄
     */
    @NotNull(message = "年龄不能为空")
    private Integer age;

    /**
     * 性别
     */
    @NotBlank(message = "性别不能为空")
    private String sex;

    /**
     * 头像
     */
    @NotBlank(message = "头像不能为空")
    private String logo;

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
     * 手机号码
     */
    @NotBlank(message = "手机号码不能为空")
    private String phone;

    /**
     * 身份证照片（正）
     */
    @NotBlank(message = "身份证照片（正）不能为空")
    private String idCardJust;

    /**
     * 身份证照片（反）
     */
    @NotBlank(message = "身份证照片（反）不能为空")
    private String idCardAgainst;

    /**
     * 专业技术资格证书
     */
    @NotBlank(message = "专业技术资格证书不能为空")
    private String professionImage;

    /**
     * 护士执业证书
     */
    @NotBlank(message = "护士执业证书不能为空")
    private String practiceImage;

    /**
     * 胸牌照片
     */
    @NotBlank(message = "胸牌照片不能为空")
    private String chestCardImage;

    /**
     * 住址
     */
    @NotBlank(message = "住址不能为空")
    private String address;

    /**
     * 医院
     */
    @NotBlank(message = "医院不能为空")
    private String hospital;

    /**
     * 个人评价
     */
    @NotBlank(message = "个人评价不能为空")
    private String evaluate;

    /**
     * 累计订单数量
     */
    @NotNull(message = "累计订单数量不能为空")
    private Long totalNumber;

    /**
     * 余额
     */
    @NotNull(message = "余额不能为空")
    private BigDecimal balance;

    /**
     * 积分
     */
    @NotNull(message = "积分不能为空")
    private Long score;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空")
    private String remark;

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
     * 服务状态(false:不接单 true:接单中)
     */
    private Boolean serviceStatus;

    /**
     * 状态（0,1,2,3；默认创建，审核中，审核失败，上线）
     */
    @NotNull(message = "状态（0,1,2,3；默认创建，审核中，审核失败，上线）不能为空")
    private Byte status;

    /**
     * 预留
     */
    @NotBlank(message = "预留不能为空")
    private String reserve;

}
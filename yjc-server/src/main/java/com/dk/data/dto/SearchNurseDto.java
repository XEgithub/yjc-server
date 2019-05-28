package com.dk.data.dto;

import cn.sourcespro.commons.data.dto.PageDto;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 护士 Search Condition Entity
*
* @author ban
* @date 2019/01/10
*/
@Getter
@Setter
@ToString
public class SearchNurseDto extends PageDto {

    /**
     * wxUser
     */
    private String wxUser;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别
     */
    private String sex;

    /**
     * 头像
     */
    private String logo;

    /**
     * 证件类型
     */
    private String certificatesType;

    /**
     * 证件号码
     */
    private String certificatesNo;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 身份证照片（正）
     */
    private String idCardJust;

    /**
     * 身份证照片（反）
     */
    private String idCardAgainst;

    /**
     * 专业技术资格证书
     */
    private String professionImage;

    /**
     * 护士执业证书
     */
    private String practiceImage;

    /**
     * 胸牌照片
     */
    private String chestCardImage;

    /**
     * 住址
     */
    private String address;

    /**
     * 医院
     */
    private String hospital;

    /**
     * 个人评价
     */
    private String evaluate;

    /**
     * 累计订单数量
     */
    private Long totalNumber;

    /**
     * 余额
     */
    private BigDecimal balance;

    /**
     * 积分
     */
    private Long score;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
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
     * 服务状态(false:不接单 true:接单中)
     */
    private Boolean serviceStatus;

    /**
     * 状态（0,1,2,3；默认创建，审核中，审核失败，上线）
     */
    private Byte status;

    /**
     * 预留
     */
    private String reserve;

}
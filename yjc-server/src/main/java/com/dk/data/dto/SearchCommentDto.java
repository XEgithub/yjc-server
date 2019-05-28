package com.dk.data.dto;

import cn.sourcespro.commons.data.dto.PageDto;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 评论 Search Condition Entity
*
* @author ban
* @date 2018/12/12
*/
@Getter
@Setter
@ToString
public class SearchCommentDto extends PageDto {

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

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
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

}
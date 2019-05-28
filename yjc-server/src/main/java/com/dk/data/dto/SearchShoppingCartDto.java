package com.dk.data.dto;

import cn.sourcespro.commons.data.dto.PageDto;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 轮播图 Search Condition Entity
*
* @author ban
* @date 2018/12/05
*/
@Getter
@Setter
@ToString
public class SearchShoppingCartDto extends PageDto {

    /**
     * 用户
     */
    private String user;

    /**
     * 项目
     */
    private String project;

    /**
     * 项目类型(0表示单项目, 1表示组合项目)
     */
    private String projectType;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 状态
     */
    private Boolean status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 预留
     */
    private String reserve;

}
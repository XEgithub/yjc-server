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
* @date 2018/12/04
*/
@Getter
@Setter
@ToString
public class SearchProjectsHomeDto extends PageDto {

    /**
     * 项目uuid
     */
    private String project;

    /**
     * 价格排序
     */
    private String sort;

    private String hospital;

    /**
     * 单项目/组合项目 project/group
     */
    private String type;

    /**
     * 分类uuid
     */
    private String classify;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

}
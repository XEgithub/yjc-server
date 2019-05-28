package com.dk.data.dto;

import cn.sourcespro.commons.data.dto.PageDto;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 菜单 Search Condition Entity
*
* @author ban
* @date 2018/11/28
*/
@Getter
@Setter
@ToString
public class SearchMenuDto extends PageDto {

    /**
     * 名称
     */
    private String name;

    /**
     * 路径
     */
    private String url;

    /**
     * 图标
     */
    private String icon;

    /**
     * 备注
     */
    private String remark;

    /**
     * 父id
     */
    private Long pid = 1L;

    /**
     * 级别
     */
    private Integer level;

    /**
     * 叶子节点
     */
    private Boolean leaf;

    /**
     * 排序
     */
    private Integer sortt;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
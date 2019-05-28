package com.dk.data.dto;

import cn.sourcespro.commons.data.dto.PageDto;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 热门搜索 Search Condition Entity
*
* @author ban
* @date 2018/12/11
*/
@Getter
@Setter
@ToString
public class SearchSearchHotDto extends PageDto {

    /**
     * 搜索词条
     */
    private String searchName;

    /**
     * 权重
     */
    private Byte weight;

    /**
     * 搜索次数
     */
    private Long time;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 状态
     */
    private Byte status;

    /**
     * 预留
     */
    private String reserve;

}
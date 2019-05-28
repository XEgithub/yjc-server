package com.dk.data.dto;

import cn.sourcespro.commons.data.dto.PageDto;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 历史搜索 Search Condition Entity
*
* @author ban
* @date 2018/12/11
*/
@Getter
@Setter
@ToString
public class SearchSearchHisDto extends PageDto {

    /**
     * 搜索关键字
     */
    private String searchName;

    /**
     * 搜索次数
     */
    private Long time;

    /**
     * 用户
     */
    private String user;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 0:不作为历史记录显示 1:作为历史记录显示
     */
    private Boolean status;

    /**
     * 预留
     */
    private String reserve;

}
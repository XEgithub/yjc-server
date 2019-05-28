package com.dk.data.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 历史搜索 Entity
*
* @author ban
* @date 2018/12/11
*/
@Getter
@Setter
@ToString
@TableName("search_his")
public class SearchHis {

    /**
     * 主键
     */
    private Long id;

    /**
     * uuid
     */
    private String uuid;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date updateTime;

    /**
     * 0:不作为历史记录显示 1:作为历史记录显示
     */
    private Boolean status;

    /**
     * 预留
     */
    private String reserve;

    /**
     * 
     */
    @TableLogic
    private Boolean deleted;

}
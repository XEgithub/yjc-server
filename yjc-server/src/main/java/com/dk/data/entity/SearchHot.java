package com.dk.data.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 热门搜索 Entity
*
* @author ban
* @date 2018/12/11
*/
@Getter
@Setter
@ToString
@TableName("search_hot")
public class SearchHot {

    /**
     * 主键
     */
    private Long id;

    /**
     * uuid
     */
    private String uuid;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;

    /**
     * 
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date updateTime;

    /**
     * 状态
     */
    private Byte status;

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
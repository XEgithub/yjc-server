package com.dk.data.dto;

import cn.sourcespro.commons.data.dto.PageDto;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 微信用户 Search Condition Entity
*
* @author ban
* @date 2018/12/11
*/
@Getter
@Setter
@ToString
public class SearchInformDto extends PageDto {

    /**
     * 用户uuid
     */
    private String user;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 消息类型
     */
    private Byte type;

    /**
     * 0:未读 1:已读
     */
    private String markRead;

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
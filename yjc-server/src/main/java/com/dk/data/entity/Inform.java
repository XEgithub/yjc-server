package com.dk.data.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 微信用户 Entity
*
* @author ban
* @date 2018/12/11
*/
@Getter
@Setter
@ToString
public class Inform {

    /**
     * 主键
     */
    private Long id;

    /**
     * uuid
     */
    private String uuid;

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
     * 0:删除 1:未删除
     */
    @TableLogic
    private Boolean deleted;

}
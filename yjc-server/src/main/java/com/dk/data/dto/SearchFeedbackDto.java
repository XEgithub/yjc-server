package com.dk.data.dto;

import cn.sourcespro.commons.data.dto.PageDto;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 反馈 Search Condition Entity
*
* @author ban
* @date 2019/01/10
*/
@Getter
@Setter
@ToString
public class SearchFeedbackDto extends PageDto {

    /**
     * 用户
     */
    private String user;

    /**
     * 内容
     */
    private String content;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态
     */
    private Byte status;

    /**
     * 预留
     */
    private String reserve;

}
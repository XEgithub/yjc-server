package com.dk.data.dto;

import cn.sourcespro.commons.data.dto.PageDto;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 评论模板 Search Condition Entity
*
* @author ban
* @date 2019/01/03
*/
@Getter
@Setter
@ToString
public class SearchCommentTemplateDto extends PageDto {

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
     * 描述
     */
    private String description;

    /**
     * 状态
     */
    private Byte status;

    /**
     * 预留
     */
    private String reserve;

    /**
     * 删除
     */
    private Boolean deleted;

}
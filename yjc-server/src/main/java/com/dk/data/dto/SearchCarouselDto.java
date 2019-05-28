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
public class SearchCarouselDto extends PageDto {

    /**
     * 图片
     */
    private String image;

    /**
     * 跳转链接
     */
    private String url;

    /**
     * 0:轮播图 1:固定显示图
     */
    private Byte alone;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 0:禁用 1:启用
     */
    private Byte status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 预留
     */
    private String reserve;

}
package com.dk.data.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 轮播图 Entity
*
* @author ban
* @date 2018/12/04
*/
@Getter
@Setter
@ToString
@TableName("carousel")
public class Carousel {

    /**
     * id
     */
    private Long id;

    /**
     * uuid
     */
    private String uuid;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
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
     * 逻辑删除
     */
    @TableLogic
    private Boolean deleted;

    /**
     * 备注
     */
    private String remark;

    /**
     * 预留
     */
    private String reserve;

}
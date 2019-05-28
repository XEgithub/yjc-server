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
@TableName("classify_home")
public class ClassifyHome {

    /**
     * id
     */
    private Long id;

    /**
     * uuid
     */
    private String uuid;

    /**
     * 名称
     */
    private String name;

    /**
     * 路径
     */
    private String url;

    /**
     * 图标
     */
    private String icon;

    /**
     * 备注
     */
    private String remark;

    /**
     * 父id
     */
    private Long pid;

    /**
     * 左值
     */
    private Integer lft;

    /**
     * 右值
     */
    private Integer rgt;

    /**
     * 级别
     */
    private Integer level;

    /**
     * 路径ids
     */
    private String ids;

    /**
     * 叶子节点
     */
    private Byte leaf;

    /**
     * 排序
     */
    private Integer sortt;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date updateTime;

    /**
     * 状态
     */
    @TableLogic
    private Boolean deleted;

}
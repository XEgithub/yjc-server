package com.dk.data.entity;

import cn.sourcespro.commons.entity.BaseTree;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 微信用户 Entity
*
* @author ban
* @date 2018/12/10
*/
@Getter
@Setter
@ToString
@TableName("classify")
public class Classify extends BaseTree<Long, Classify> {

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
package com.dk.data.entity;

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
@TableName("projects_classify")
public class ProjectsClassify {

    /**
     * id
     */
    private Long id;

    /**
     * uuid
     */
    private String uuid;

    /**
     * 项目uuid
     */
    private String project;

    /**
     * 单项目/组合项目 project/group
     */
    private String type;

    /**
     * 分类id 对应表classify
     */
    private String classify;

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
     * 逻辑删除
     */
    @TableLogic
    private Boolean deleted;

}
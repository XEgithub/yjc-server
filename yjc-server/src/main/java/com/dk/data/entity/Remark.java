package com.dk.data.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 订单 Entity
*
* @author ban
* @date 2018/12/06
*/
@Getter
@Setter
@ToString
public class Remark {

    /**
     * id
     */
    private Long id;

    /**
     * uuid
     */
    private String uuid;

    /**
     * 内容
     */
    private String content;

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
     * 描述
     */
    private String description;

    /**
     * 状态
     */
    private Byte status;

    /**
     * 删除
     */
    @TableLogic
    private Boolean deleted;

    /**
     * 预留
     */
    private String reserve;

}
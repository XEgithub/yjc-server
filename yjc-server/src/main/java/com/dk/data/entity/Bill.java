package com.dk.data.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 护士流水表 Entity
*
* @author ban
* @date 2019/01/10
*/
@Getter
@Setter
@ToString
public class Bill {

    /**
     * id
     */
    private Long id;

    /**
     * uuid
     */
    private String uuid;

    /**
     * 护士uuid
     */
    private String nurse;

    /**
     * 预约uuid
     */
    private String appointment;

    /**
     * 名称
     */
    private String name;

    /**
     * 金额
     */
    private BigDecimal price;

    /**
     * 类型(0:收入 1:支出)
     */
    private String type;

    /**
     * 余额
     */
    private BigDecimal balance;

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
     * 状态
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

    /**
     * 0未删除 1删除
     */
    @TableLogic
    private Boolean deleted;

}
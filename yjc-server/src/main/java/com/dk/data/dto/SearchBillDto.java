package com.dk.data.dto;

import cn.sourcespro.commons.data.dto.PageDto;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 护士流水表 Search Condition Entity
*
* @author ban
* @date 2019/01/10
*/
@Getter
@Setter
@ToString
public class SearchBillDto extends PageDto {

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
    private Date createTime;

    /**
     * 修改时间
     */
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

}
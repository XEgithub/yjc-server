package com.dk.data.dto;

import cn.sourcespro.commons.data.dto.PageDto;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 条码 Search Condition Entity
*
* @author ban
* @date 2019/01/10
*/
@Getter
@Setter
@ToString
public class SearchBarcodeDto extends PageDto {

    /**
     * 预约
     */
    private String appointment;

    /**
     * 采血管
     */
    private String tube;

    /**
     * 条形码
     */
    private String barcoding;

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
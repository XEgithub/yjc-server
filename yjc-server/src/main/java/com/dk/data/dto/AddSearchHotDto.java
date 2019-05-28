package com.dk.data.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 热门搜索 Add Entity
*
* @author ban
* @date 2018/12/11
*/
@Getter
@Setter
@ToString
public class AddSearchHotDto {

    /**
     * 搜索词条
     */
    @NotBlank(message = "搜索词条不能为空")
    private String searchName;

    /**
     * 权重
     */
    @NotNull(message = "权重不能为空")
    private Byte weight;

    /**
     * 搜索次数
     */
    @NotNull(message = "搜索次数不能为空")
    private Long time;

    /**
     * 状态
     */
    @NotNull(message = "状态不能为空")
    private Byte status;

    /**
     * 预留
     */
    @NotBlank(message = "预留不能为空")
    private String reserve;

}
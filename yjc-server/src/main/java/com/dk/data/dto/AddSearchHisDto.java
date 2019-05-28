package com.dk.data.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 历史搜索 Add Entity
*
* @author ban
* @date 2018/12/11
*/
@Getter
@Setter
@ToString
public class AddSearchHisDto {

    /**
     * 搜索关键字
     */
    @NotBlank(message = "搜索关键字不能为空")
    private String searchName;

    /**
     * 搜索次数
     */
    @NotNull(message = "搜索次数不能为空")
    private Long time;

    /**
     * 用户
     */
    @NotBlank(message = "用户不能为空")
    private String user;

    /**
     * 0:不作为历史记录显示 1:作为历史记录显示
     */
    @NotNull(message = "0:不作为历史记录显示 1:作为历史记录显示不能为空")
    private Boolean status;

    /**
     * 预留
     */
    @NotBlank(message = "预留不能为空")
    private String reserve;

}
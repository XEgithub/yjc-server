package com.dk.data.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 反馈 Add Entity
*
* @author ban
* @date 2019/01/10
*/
@Getter
@Setter
@ToString
public class AddFeedbackDto {

    /**
     * 用户
     */
    @NotBlank(message = "用户不能为空")
    private String user;

    /**
     * 内容
     */
    @NotBlank(message = "内容不能为空")
    private String content;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空")
    private String remark;

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
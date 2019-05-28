package com.dk.data.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 微信用户 Add Entity
*
* @author ban
* @date 2018/12/11
*/
@Getter
@Setter
@ToString
public class AddInformDto {

    /**
     * 用户uuid
     */
    @NotBlank(message = "用户uuid不能为空")
    private String user;

    /**
     * 标题
     */
    @NotBlank(message = "标题不能为空")
    private String title;

    /**
     * 内容
     */
    @NotBlank(message = "内容不能为空")
    private String content;

    /**
     * 消息类型
     */
    private Byte type;

    /**
     * 0:未读 1:已读
     */
    @NotBlank(message = "0:未读 1:已读不能为空")
    private String markRead;

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
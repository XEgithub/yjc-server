package com.dk.data.dto;

import cn.sourcespro.commons.data.dto.PageDto;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 角色 Search Condition Entity
*
* @author ban
* @date 2018/11/28
*/
@Getter
@Setter
@ToString
public class SearchRoleDto extends PageDto {

    /**
     * 名称
     */
    private String name;

    /**
     * 角色标识
     */
    private String role;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
package com.dk.data.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 轮播图 Add Entity
*
* @author ban
* @date 2018/12/04
*/
@Getter
@Setter
@ToString
public class AddHospitalDto {

    /**
     * logo
     */
    @NotBlank(message = "logo不能为空")
    private String logo;

    /**
     * 名称
     */
    @NotBlank(message = "名称不能为空")
    private String name;

    /**
     * 地址
     */
    @NotBlank(message = "地址不能为空")
    private String address;

    /**
     * 手机
     */
//    @NotBlank(message = "手机不能为空")
    private String phone;

    /**
     * 座机
     */
    @NotBlank(message = "座机不能为空")
    private String tel;

    /**
     * 描述
     */
    @NotBlank(message = "描述不能为空")
    private String description;

    /**
     * 备注
     */
//    @NotBlank(message = "备注不能为空")
    private String remark;

    /**
     * 备注1
     */
//    @NotBlank(message = "备注1不能为空")
    private String remark1;

//    /**
//     * 预留
//     */
//    @NotBlank(message = "预留不能为空")
//    private String reserve;

}
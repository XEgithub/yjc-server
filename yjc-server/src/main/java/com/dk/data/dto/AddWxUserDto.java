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
* @date 2018/12/07
*/
@Getter
@Setter
@ToString
public class AddWxUserDto {

    /**
     * 
     */
    @NotNull(message = "不能为空")
    private Integer subscribe;

    /**
     * 
     */
    @NotBlank(message = "不能为空")
    private String openid;

    /**
     * 昵称
     */
    @NotBlank(message = "昵称不能为空")
    private String nickname;

    /**
     * 
     */
    @NotNull(message = "不能为空")
    private Integer sex;

    /**
     * 省
     */
    @NotBlank(message = "省不能为空")
    private String province;

    /**
     * 所在城市
     */
    @NotBlank(message = "所在城市不能为空")
    private String city;

    /**
     * 
     */
    @NotBlank(message = "不能为空")
    private String country;

    /**
     * 
     */
    @NotBlank(message = "不能为空")
    private String language;

    /**
     * 头像
     */
    @NotBlank(message = "头像不能为空")
    private String headimgurl;

    /**
     * 关注时间
     */
    @NotBlank(message = "关注时间不能为空")
    private String subscribeTime;

    /**
     * 
     */
    @NotBlank(message = "不能为空")
    private String unionid;

    /**
     * 公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
     */
    @NotBlank(message = "公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注不能为空")
    private String remark;

    /**
     * 用户所在的分组ID（兼容旧的用户分组接口）
     */
    @NotNull(message = "用户所在的分组ID（兼容旧的用户分组接口）不能为空")
    private Integer groupid;

    /**
     * 用户被打上的标签ID列表
     */
    @NotBlank(message = "用户被打上的标签ID列表不能为空")
    private String tagidList;

    /**
     * 返回用户关注的渠道来源，ADD_SCENE_SEARCH 公众号搜索，ADD_SCENE_ACCOUNT_MIGRATION 公众号迁移，ADD_SCENE_PROFILE_CARD 名片分享，ADD_SCENE_QR_CODE 扫描二维码，ADD_SCENEPROFILE LINK 图文页内名称点击，ADD_SCENE_PROFILE_ITEM 图文页右上角菜单，ADD_SCENE_PAID 支付后关注，ADD_SCENE_OTHERS 其他
     */
    @NotBlank(message = "返回用户关注的渠道来源，ADD_SCENE_SEARCH 公众号搜索，ADD_SCENE_ACCOUNT_MIGRATION 公众号迁移，ADD_SCENE_PROFILE_CARD 名片分享，ADD_SCENE_QR_CODE 扫描二维码，ADD_SCENEPROFILE LINK 图文页内名称点击，ADD_SCENE_PROFILE_ITEM 图文页右上角菜单，ADD_SCENE_PAID 支付后关注，ADD_SCENE_OTHERS 其他不能为空")
    private String subscribeScene;

    /**
     * 来源
     */
    @NotBlank(message = "来源不能为空")
    private String source;

    /**
     * 定位地址
     */
    @NotBlank(message = "定位地址不能为空")
    private String address;

    /**
     * 二维码扫码场景
     */
    @NotBlank(message = "二维码扫码场景不能为空")
    private String qrScene;

    /**
     * 二维码扫码场景描述
     */
    @NotBlank(message = "二维码扫码场景描述不能为空")
    private String qrSceneStr;

    /**
     * 
     */
    @NotBlank(message = "不能为空")
    private String reserve;

    /**
     * 
     */
    @NotBlank(message = "不能为空")
    private String remark1;

}
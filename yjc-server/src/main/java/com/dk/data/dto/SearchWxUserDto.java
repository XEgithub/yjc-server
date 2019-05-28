package com.dk.data.dto;

import cn.sourcespro.commons.data.dto.PageDto;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* 微信用户 Search Condition Entity
*
* @author ban
* @date 2018/12/07
*/
@Getter
@Setter
@ToString
public class SearchWxUserDto extends PageDto {

    /**
     * 
     */
    private Integer subscribe;

    /**
     * 
     */
    private String openid;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 
     */
    private Integer sex;

    /**
     * 省
     */
    private String province;

    /**
     * 所在城市
     */
    private String city;

    /**
     * 
     */
    private String country;

    /**
     * 
     */
    private String language;

    /**
     * 头像
     */
    private String headimgurl;

    /**
     * 关注时间
     */
    private String subscribeTime;

    /**
     * 
     */
    private String unionid;

    /**
     * 公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
     */
    private String remark;

    /**
     * 用户所在的分组ID（兼容旧的用户分组接口）
     */
    private Integer groupid;

    /**
     * 用户被打上的标签ID列表
     */
    private String tagidList;

    /**
     * 返回用户关注的渠道来源，ADD_SCENE_SEARCH 公众号搜索，ADD_SCENE_ACCOUNT_MIGRATION 公众号迁移，ADD_SCENE_PROFILE_CARD 名片分享，ADD_SCENE_QR_CODE 扫描二维码，ADD_SCENEPROFILE LINK 图文页内名称点击，ADD_SCENE_PROFILE_ITEM 图文页右上角菜单，ADD_SCENE_PAID 支付后关注，ADD_SCENE_OTHERS 其他
     */
    private String subscribeScene;

    /**
     * 来源
     */
    private String source;

    /**
     * 定位地址
     */
    private String address;

    /**
     * 二维码扫码场景
     */
    private String qrScene;

    /**
     * 二维码扫码场景描述
     */
    private String qrSceneStr;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 
     */
    private String reserve;

    /**
     * 
     */
    private String remark1;

}
<#include "../include/public.ftl"/>

<@head_add_edit title="微信用户-详情"/>

    <@body_empty>
        <@form_padding title="详情微信用户">
            <input type="hidden" name="id" value="${item.id!''}"/>
            <@input label="" name="subscribe" value="${(item.subscribe)!''}" readonly="readonly"/>
            <@input label="" name="openid" value="${(item.openid)!''}" readonly="readonly"/>
            <@input label="昵称" name="nickname" value="${(item.nickname)!''}" readonly="readonly"/>
            <@input label="" name="sex" value="${(item.sex)!''}" readonly="readonly"/>
            <@input label="省" name="province" value="${(item.province)!''}" readonly="readonly"/>
            <@input label="所在城市" name="city" value="${(item.city)!''}" readonly="readonly"/>
            <@input label="" name="country" value="${(item.country)!''}" readonly="readonly"/>
            <@input label="" name="language" value="${(item.language)!''}" readonly="readonly"/>
            <@input label="头像" name="headimgurl" value="${(item.headimgurl)!''}" readonly="readonly"/>
            <@input label="关注时间" name="subscribeTime" value="${(item.subscribeTime)!''}" readonly="readonly"/>
            <@input label="" name="unionid" value="${(item.unionid)!''}" readonly="readonly"/>
            <@input label="公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注" name="remark" value="${(item.remark)!''}" readonly="readonly"/>
            <@input label="用户所在的分组ID（兼容旧的用户分组接口）" name="groupid" value="${(item.groupid)!''}" readonly="readonly"/>
            <@input label="用户被打上的标签ID列表" name="tagidList" value="${(item.tagidList)!''}" readonly="readonly"/>
            <@input label="返回用户关注的渠道来源，ADD_SCENE_SEARCH 公众号搜索，ADD_SCENE_ACCOUNT_MIGRATION 公众号迁移，ADD_SCENE_PROFILE_CARD 名片分享，ADD_SCENE_QR_CODE 扫描二维码，ADD_SCENEPROFILE LINK 图文页内名称点击，ADD_SCENE_PROFILE_ITEM 图文页右上角菜单，ADD_SCENE_PAID 支付后关注，ADD_SCENE_OTHERS 其他" name="subscribeScene" value="${(item.subscribeScene)!''}" readonly="readonly"/>
            <@input label="来源" name="source" value="${(item.source)!''}" readonly="readonly"/>
            <@input label="定位地址" name="address" value="${(item.address)!''}" readonly="readonly"/>
            <@input label="二维码扫码场景" name="qrScene" value="${(item.qrScene)!''}" readonly="readonly"/>
            <@input label="二维码扫码场景描述" name="qrSceneStr" value="${(item.qrSceneStr)!''}" readonly="readonly"/>
            <@input label="" name="reserve" value="${(item.reserve)!''}" readonly="readonly"/>
            <@input label="" name="remark1" value="${(item.remark1)!''}" readonly="readonly"/>
        </@form_padding>
    </@body_empty>
<@footer>
<script>
    layui.use(['element', 'form'], function(){
        var form = layui.form;
        var $ = layui.jquery;

    });
</script>
</@footer>
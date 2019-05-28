<#include "../include/public.ftl"/>

<@head_add_edit title="微信用户-编辑"/>

    <@body_empty>
        <@form_padding title="编辑微信用户">
            <input type="hidden" name="id" value="${item.id!''}"/>
            <@input label="" name="subscribe" value="${(item.subscribe)!''}" placeholder="请输入" require="false"/>
            <@input label="" name="openid" value="${(item.openid)!''}" placeholder="请输入" require="false"/>
            <@input label="昵称" name="nickname" value="${(item.nickname)!''}" placeholder="请输入昵称" require="false"/>
            <@input label="" name="sex" value="${(item.sex)!''}" placeholder="请输入" require="false"/>
            <@input label="省" name="province" value="${(item.province)!''}" placeholder="请输入省" require="false"/>
            <@input label="所在城市" name="city" value="${(item.city)!''}" placeholder="请输入所在城市" require="false"/>
            <@input label="" name="country" value="${(item.country)!''}" placeholder="请输入" require="false"/>
            <@input label="" name="language" value="${(item.language)!''}" placeholder="请输入" require="false"/>
            <@input label="头像" name="headimgurl" value="${(item.headimgurl)!''}" placeholder="请输入头像" require="false"/>
            <@input label="关注时间" name="subscribeTime" value="${(item.subscribeTime)!''}" placeholder="请输入关注时间" require="false"/>
            <@input label="" name="unionid" value="${(item.unionid)!''}" placeholder="请输入" require="false"/>
            <@input label="公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注" name="remark" value="${(item.remark)!''}" placeholder="请输入公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注" require="false"/>
            <@input label="用户所在的分组ID（兼容旧的用户分组接口）" name="groupid" value="${(item.groupid)!''}" placeholder="请输入用户所在的分组ID（兼容旧的用户分组接口）" require="false"/>
            <@input label="用户被打上的标签ID列表" name="tagidList" value="${(item.tagidList)!''}" placeholder="请输入用户被打上的标签ID列表" require="false"/>
            <@input label="返回用户关注的渠道来源，ADD_SCENE_SEARCH 公众号搜索，ADD_SCENE_ACCOUNT_MIGRATION 公众号迁移，ADD_SCENE_PROFILE_CARD 名片分享，ADD_SCENE_QR_CODE 扫描二维码，ADD_SCENEPROFILE LINK 图文页内名称点击，ADD_SCENE_PROFILE_ITEM 图文页右上角菜单，ADD_SCENE_PAID 支付后关注，ADD_SCENE_OTHERS 其他" name="subscribeScene" value="${(item.subscribeScene)!''}" placeholder="请输入返回用户关注的渠道来源，ADD_SCENE_SEARCH 公众号搜索，ADD_SCENE_ACCOUNT_MIGRATION 公众号迁移，ADD_SCENE_PROFILE_CARD 名片分享，ADD_SCENE_QR_CODE 扫描二维码，ADD_SCENEPROFILE LINK 图文页内名称点击，ADD_SCENE_PROFILE_ITEM 图文页右上角菜单，ADD_SCENE_PAID 支付后关注，ADD_SCENE_OTHERS 其他" require="false"/>
            <@input label="来源" name="source" value="${(item.source)!''}" placeholder="请输入来源" require="false"/>
            <@input label="定位地址" name="address" value="${(item.address)!''}" placeholder="请输入定位地址" require="false"/>
            <@input label="二维码扫码场景" name="qrScene" value="${(item.qrScene)!''}" placeholder="请输入二维码扫码场景" require="false"/>
            <@input label="二维码扫码场景描述" name="qrSceneStr" value="${(item.qrSceneStr)!''}" placeholder="请输入二维码扫码场景描述" require="false"/>
            <@input label="" name="reserve" value="${(item.reserve)!''}" placeholder="请输入" require="false"/>
            <@input label="" name="remark1" value="${(item.remark1)!''}" placeholder="请输入" require="false"/>
            <@select label="下拉框" name="name">
                <option value="xxxx">选择一</option>
                <option value="yyyy">选择二</option>
                <option value="zzzz">选择三</option>
            </@select>
            <button class="layui-btn" lay-submit lay-filter="*" style="display: none">隐藏提交，勿删！！！</button>
        </@form_padding>
    </@body_empty>
<@footer>
<script>
    layui.use(['element', 'form', 'laydate'], function(){
        var form = layui.form;
        var $ = layui.jquery;
        var laydate = layui.laydate;


        form.on('submit(*)', function(data){
            $.ajax({
                type: "POST",
                url: "/admin/wxuser/update",
                contentType: "application/json",
                data: JSON.stringify(data.field),
                success: function (res) {
                    if (res.code === 0) {
                        parent.layui.table.reload('myTable');
                        layer.msg("更新成功！");
                        parent.layer.closeAll();
                    } else {
                        layer.alert(res.msg);
                    }
                }
            });
            return false;
        });
    });
</script>
</@footer>
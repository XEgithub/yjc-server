<#include "../include/public.ftl"/>

<@head_add_edit title="用户-详情"/>

    <@body_empty>
        <@form_padding title="详情用户">
            <input type="hidden" name="id" value="${item.id!''}"/>
            <@input label="昵称" name="nickname" value="${(item.nickname)!''}" readonly="readonly"/>
            <@input label="用户名" name="username" value="${(item.username)!''}" readonly="readonly"/>
            <@input label="性别：0:男 1:女 2:未知" name="sex" value="${(item.sex)!''}" readonly="readonly"/>
            <@input label="密码" name="password" value="${(item.password)!''}" readonly="readonly"/>
            <@input label="头像" name="avatar" value="${(item.avatar)!''}" readonly="readonly"/>
            <@input label="手机" name="phone" value="${(item.phone)!''}" readonly="readonly"/>
            <@input label="电话" name="tel" value="${(item.tel)!''}" readonly="readonly"/>
            <@input label="邮箱" name="email" value="${(item.email)!''}" readonly="readonly"/>
            <@input label="身份证" name="idcard" value="${(item.idcard)!''}" readonly="readonly"/>
            <@input label="生日" name="birthday" value="${(item.birthday?string('yyyy-MM-dd HH:mm:ss'))!''}" readonly="readonly"/>
            <@input label="地区" name="area" value="${(item.area)!''}" readonly="readonly"/>
            <@input label="签名" name="autograph" value="${(item.autograph)!''}" readonly="readonly"/>
            <@input label="微信openid" name="openid" value="${(item.openid)!''}" readonly="readonly"/>
            <@input label="状态：0:未激活 1:激活" name="active" value="${(item.active)!''}" readonly="readonly"/>
            <@input label="推荐人" name="referee" value="${(item.referee)!''}" readonly="readonly"/>
            <@input label="最后登录时间" name="lastLoginTime" value="${(item.lastLoginTime?string('yyyy-MM-dd HH:mm:ss'))!''}" readonly="readonly"/>
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
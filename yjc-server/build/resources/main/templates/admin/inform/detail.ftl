<#include "../include/public.ftl"/>

<@head_add_edit title="微信用户-详情"/>

    <@body_empty>
        <@form_padding title="详情微信用户">
            <input type="hidden" name="id" value="${item.id!''}"/>
            <@input label="用户uuid" name="user" value="${(item.user)!''}" readonly="readonly"/>
            <@input label="标题" name="title" value="${(item.title)!''}" readonly="readonly"/>
            <@input label="内容" name="content" value="${(item.content)!''}" readonly="readonly"/>
            <@input label="0:未读 1:已读" name="read" value="${(item.read)!''}" readonly="readonly"/>
            <@input label="状态" name="status" value="${(item.status)!''}" readonly="readonly"/>
            <@input label="预留" name="reserve" value="${(item.reserve)!''}" readonly="readonly"/>
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
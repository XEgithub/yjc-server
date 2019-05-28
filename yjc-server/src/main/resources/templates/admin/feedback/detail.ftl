<#include "../include/public.ftl"/>

<@head_add_edit title="反馈-详情"/>

    <@body_empty>
        <@form_padding title="详情反馈">
            <input type="hidden" name="id" value="${item.id!''}"/>
            <@input label="用户" name="user" value="${(item.user)!''}" readonly="readonly"/>
            <@input label="内容" name="content" value="${(item.content)!''}" readonly="readonly"/>
            <@input label="备注" name="remark" value="${(item.remark)!''}" readonly="readonly"/>
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
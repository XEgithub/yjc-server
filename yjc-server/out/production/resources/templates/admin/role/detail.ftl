<#include "../include/public.ftl"/>

<@head_add_edit title="角色-详情"/>

    <@body_empty>
        <@form_padding title="详情角色">
            <input type="hidden" name="id" value="${item.id!''}"/>
            <@input label="名称" name="name" value="${(item.name)!''}" readonly="readonly"/>
            <@input label="角色标识" name="role" value="${(item.role)!''}" readonly="readonly"/>
            <@input label="备注" name="remark" value="${(item.remark)!''}" readonly="readonly"/>
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
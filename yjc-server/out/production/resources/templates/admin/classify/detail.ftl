<#include "../include/public.ftl"/>

<@head_add_edit title="微信用户-详情"/>

    <@body_empty>
        <@form_padding title="详情微信用户">
            <input type="hidden" name="id" value="${item.id!''}"/>
            <@input label="上级" name="pName" value="${(parent.name)!''}" readonly="readonly"/>
            <@input label="名称" name="name" value="${(item.name)!''}" readonly="readonly"/>
            <@input label="路径" name="url" value="${(item.url)!''}" readonly="readonly"/>
            <@input label="图标" name="icon" value="${(item.icon)!''}" readonly="readonly"/>
            <@input label="备注" name="remark" value="${(item.remark)!''}" readonly="readonly"/>
            <@input label="排序" name="sortt" value="${(item.sortt)!''}" readonly="readonly"/>
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
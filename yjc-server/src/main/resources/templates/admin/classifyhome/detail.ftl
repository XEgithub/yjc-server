<#include "../include/public.ftl"/>

<@head_add_edit title="轮播图-详情"/>

    <@body_empty>
        <@form_padding title="详情轮播图">
            <input type="hidden" name="id" value="${item.id!''}"/>
            <@input label="名称" name="name" value="${(item.name)!''}" readonly="readonly"/>
            <@input label="路径" name="url" value="${(item.url)!''}" readonly="readonly"/>
            <@input label="图标" name="icon" value="${(item.icon)!''}" readonly="readonly"/>
            <@input label="备注" name="remark" value="${(item.remark)!''}" readonly="readonly"/>
            <@input label="父id" name="pid" value="${(item.pid)!''}" readonly="readonly"/>
            <@input label="左值" name="lft" value="${(item.lft)!''}" readonly="readonly"/>
            <@input label="右值" name="rgt" value="${(item.rgt)!''}" readonly="readonly"/>
            <@input label="级别" name="level" value="${(item.level)!''}" readonly="readonly"/>
            <@input label="路径ids" name="ids" value="${(item.ids)!''}" readonly="readonly"/>
            <@input label="叶子节点" name="leaf" value="${(item.leaf)!''}" readonly="readonly"/>
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
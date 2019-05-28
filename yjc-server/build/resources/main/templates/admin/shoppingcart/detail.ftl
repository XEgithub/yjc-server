<#include "../include/public.ftl"/>

<@head_add_edit title="轮播图-详情"/>

    <@body_empty>
        <@form_padding title="详情轮播图">
            <input type="hidden" name="id" value="${item.id!''}"/>
            <@input label="用户" name="user" value="${(item.user)!''}" readonly="readonly"/>
            <@input label="项目" name="project" value="${(item.project)!''}" readonly="readonly"/>
            <@input label="项目类型(0表示单项目, 1表示组合项目)" name="projectType" value="${(item.projectType)!''}" readonly="readonly"/>
            <@input label="状态" name="status" value="${(item.status)!''}" readonly="readonly"/>
            <@input label="备注" name="remark" value="${(item.remark)!''}" readonly="readonly"/>
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
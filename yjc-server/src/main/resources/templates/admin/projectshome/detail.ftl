<#include "../include/public.ftl"/>

<@head_add_edit title="轮播图-详情"/>

    <@body_empty>
        <@form_padding title="详情轮播图">
            <input type="hidden" name="id" value="${item.id!''}"/>
            <@input label="项目uuid" name="project" value="${(item.project)!''}" readonly="readonly"/>
            <@input label="单项目/组合项目 project/group" name="type" value="${(item.type)!''}" readonly="readonly"/>
            <@input label="分类uuid" name="classify" value="${(item.classify)!''}" readonly="readonly"/>
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
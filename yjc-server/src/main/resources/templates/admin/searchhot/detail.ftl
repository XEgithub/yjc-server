<#include "../include/public.ftl"/>

<@head_add_edit title="热门搜索-详情"/>

    <@body_empty>
        <@form_padding title="详情热门搜索">
            <input type="hidden" name="id" value="${item.id!''}"/>
            <@input label="搜索词条" name="searchName" value="${(item.searchName)!''}" readonly="readonly"/>
            <@input label="权重" name="weight" value="${(item.weight)!''}" readonly="readonly"/>
            <@input label="搜索次数" name="count" value="${(item.count)!''}" readonly="readonly"/>
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
<#include "../include/public.ftl"/>

<@head_add_edit title="历史搜索-详情"/>

    <@body_empty>
        <@form_padding title="详情历史搜索">
            <input type="hidden" name="id" value="${item.id!''}"/>
            <@input label="搜索关键字" name="searchName" value="${(item.searchName)!''}" readonly="readonly"/>
            <@input label="搜索次数" name="count" value="${(item.count)!''}" readonly="readonly"/>
            <@input label="用户" name="userId" value="${(item.userId)!''}" readonly="readonly"/>
            <@input label="0:不作为历史记录显示 1:作为历史记录显示" name="status" value="${(item.status)!''}" readonly="readonly"/>
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
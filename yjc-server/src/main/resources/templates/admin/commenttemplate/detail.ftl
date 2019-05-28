<#include "../include/public.ftl"/>

<@head_add_edit title="评论模板-详情"/>

    <@body_empty>
        <@form_padding title="详情评论模板">
            <input type="hidden" name="id" value="${item.id!''}"/>
            <@input label="内容" name="content" value="${(item.content)!''}" readonly="readonly"/>
            <@input label="描述" name="description" value="${(item.description)!''}" readonly="readonly"/>
            <@input label="状态" name="status" value="${(item.status)!''}" readonly="readonly"/>
            <@input label="预留" name="reserve" value="${(item.reserve)!''}" readonly="readonly"/>
            <@input label="删除" name="delete" value="${(item.delete)!''}" readonly="readonly"/>
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
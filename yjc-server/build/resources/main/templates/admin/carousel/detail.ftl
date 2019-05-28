<#include "../include/public.ftl"/>

<@head_add_edit title="轮播图-详情"/>

    <@body_empty>
        <@form_padding title="详情轮播图">
            <input type="hidden" name="id" value="${item.id!''}"/>
            <@input label="图片" name="image" value="${(item.image)!''}" readonly="readonly"/>
            <@input label="跳转链接" name="url" value="${(item.url)!''}" readonly="readonly"/>
            <@input label="0:轮播图 1:固定显示图" name="alone" value="${(item.alone)!''}" readonly="readonly"/>
            <@input label="操作人" name="operator" value="${(item.operator)!''}" readonly="readonly"/>
            <@input label="0:禁用 1:启用" name="status" value="${(item.status)!''}" readonly="readonly"/>
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
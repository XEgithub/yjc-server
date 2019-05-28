<#include "../include/public.ftl"/>

<@head_add_edit title="轮播图-详情"/>

    <@body_empty>
        <@form_padding title="详情轮播图">
            <input type="hidden" name="id" value="${item.id!''}"/>
            <@input label="logo" name="logo" value="${(item.logo)!''}" readonly="readonly"/>
            <@input label="名称" name="name" value="${(item.name)!''}" readonly="readonly"/>
            <@input label="地址" name="address" value="${(item.address)!''}" readonly="readonly"/>
            <@input label="手机" name="phone" value="${(item.phone)!''}" readonly="readonly"/>
            <@input label="座机" name="tel" value="${(item.tel)!''}" readonly="readonly"/>
            <@input label="访问次数" name="visitCount" value="${(item.visitCount)!''}" readonly="readonly"/>
            <@input label="描述" name="description" value="${(item.description)!''}" readonly="readonly"/>
            <@input label="操作人" name="operator" value="${(item.operator)!''}" readonly="readonly"/>
            <@input label="备注" name="remark" value="${(item.remark)!''}" readonly="readonly"/>
            <@input label="备注1" name="remark1" value="${(item.remark1)!''}" readonly="readonly"/>
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
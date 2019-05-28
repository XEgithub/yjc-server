<#include "../include/public.ftl"/>

<@head_add_edit title="关注-详情"/>

    <@body_empty>
        <@form_padding title="详情关注">
            <input type="hidden" name="id" value="${item.id!''}"/>
            <@input label="用户uuid" name="user" value="${(item.user)!''}" readonly="readonly"/>
            <@input label="项目uuid" name="project" value="${(item.project)!''}" readonly="readonly"/>
            <@input label="项目类型(project表示单项目 groupproject表示组合项目)" name="type" value="${(item.type)!''}" readonly="readonly"/>
            <@input label="项目图片" name="image" value="${(item.image)!''}" readonly="readonly"/>
            <@input label="项目名称" name="name" value="${(item.name)!''}" readonly="readonly"/>
            <@input label="项目原价" name="oldPrice" value="${(item.oldPrice)!''}" readonly="readonly"/>
            <@input label="项目现价" name="nowPrice" value="${(item.nowPrice)!''}" readonly="readonly"/>
            <@input label="项目描述" name="description" value="${(item.description)!''}" readonly="readonly"/>
            <@input label="关注时间" name="concernTime" value="${(item.concernTime?string('yyyy-MM-dd HH:mm:ss'))!''}" readonly="readonly"/>
            <@input label="取消关注时间" name="cancelTime" value="${(item.cancelTime?string('yyyy-MM-dd HH:mm:ss'))!''}" readonly="readonly"/>
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
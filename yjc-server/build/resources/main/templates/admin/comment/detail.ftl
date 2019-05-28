<#include "../include/public.ftl"/>

<@head_add_edit title="评论-详情"/>

    <@body_empty>
        <@form_padding title="详情评论">
            <input type="hidden" name="id" value="${item.id!''}"/>
            <@input label="订单uuid" name="order" value="${(item.order)!''}" readonly="readonly"/>
            <@input label="项目uuid" name="project" value="${(item.project)!''}" readonly="readonly"/>
            <@input label="用户uuid" name="user" value="${(item.user)!''}" readonly="readonly"/>
            <@input label="内容" name="content" value="${(item.content)!''}" readonly="readonly"/>
            <@input label="项目指星(1-5星)" name="grade" value="${(item.grade)!''}" readonly="readonly"/>
            <@input label="就医人" name="patient" value="${(item.patient)!''}" readonly="readonly"/>
            <@input label="护士" name="nurse" value="${(item.nurse)!''}" readonly="readonly"/>
            <@input label="图片" name="image" value="${(item.image)!''}" readonly="readonly"/>
            <@input label="描述" name="description" value="${(item.description)!''}" readonly="readonly"/>
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
<#include "../include/public.ftl"/>

<@head_add_edit title="轮播图-详情"/>

    <@body_empty>
        <@form_padding title="详情轮播图">
            <input type="hidden" name="id" value="${item.id!''}"/>
            <@input label="logo" name="logo" value="${(item.logo)!''}" readonly="readonly"/>
            <@input label="项目类型(0表示单项目 1表示组合项目)" name="type" value="${(item.type)!''}" readonly="readonly"/>
            <@input label="背景" name="banner" value="${(item.banner)!''}" readonly="readonly"/>
            <@input label="列表图片" name="image" value="${(item.image)!''}" readonly="readonly"/>
            <@input label="图片1" name="image1" value="${(item.image1)!''}" readonly="readonly"/>
            <@input label="图片2" name="image2" value="${(item.image2)!''}" readonly="readonly"/>
            <@input label="图片3" name="image3" value="${(item.image3)!''}" readonly="readonly"/>
            <@input label="名称" name="name" value="${(item.name)!''}" readonly="readonly"/>
            <@input label="医院uuid" name="hospital" value="${(item.hospital)!''}" readonly="readonly"/>
            <@input label="原价" name="oldPrice" value="${(item.oldPrice)!''}" readonly="readonly"/>
            <@input label="现价" name="nowPrice" value="${(item.nowPrice)!''}" readonly="readonly"/>
            <@input label="周期" name="period" value="${(item.period)!''}" readonly="readonly"/>
            <@input label="注意事项" name="notes" value="${(item.notes)!''}" readonly="readonly"/>
            <@input label="首页分类uuid" name="classify1" value="${(item.classify1)!''}" readonly="readonly"/>
            <@input label="导航分类uuid" name="classify2" value="${(item.classify2)!''}" readonly="readonly"/>
            <@input label="关键词" name="keyword" value="${(item.keyword)!''}" readonly="readonly"/>
            <@input label="备注" name="remark" value="${(item.remark)!''}" readonly="readonly"/>
            <@input label="备注1" name="remark1" value="${(item.remark1)!''}" readonly="readonly"/>
            <@input label="操作人" name="operator" value="${(item.operator)!''}" readonly="readonly"/>
            <@input label="描述" name="description" value="${(item.description)!''}" readonly="readonly"/>
            <@input label="详情" name="detail" value="${(item.detail)!''}" readonly="readonly"/>
            <@input label="状态（下线=0，审核=1，审核失败=2，上线=3）" name="status" value="${(item.status)!''}" readonly="readonly"/>
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
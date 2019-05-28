<#include "../include/public.ftl"/>

<@head_add_edit title="微信用户-详情"/>

    <@body_empty>
        <@form_padding title="详情微信用户">
            <input type="hidden" name="id" value="${item.id!''}"/>
            <@input label="优惠券名" name="name" value="${(item.name)!''}" readonly="readonly"/>
            <@input label="用户uuid" name="user" value="${(item.user)!''}" readonly="readonly"/>
            <@input label="最小订单金额" name="minPrice" value="${(item.minPrice)!''}" readonly="readonly"/>
            <@input label="优惠金额" name="couponPrice" value="${(item.couponPrice)!''}" readonly="readonly"/>
            <@input label="兑换所需积分" name="needIntegral" value="${(item.needIntegral)!''}" readonly="readonly"/>
            <@input label="优惠券类型1:满减优惠券 2:服务抵用券" name="type" value="${(item.type)!''}" readonly="readonly"/>
            <@input label="适用范围(0表示不通用仅指定医院使用 1表示通用)" name="application" value="${(item.application)!''}" readonly="readonly"/>
            <@input label="医院uuid" name="hospital" value="${(item.hospital)!''}" readonly="readonly"/>
            <@input label="开始时间" name="beginTime" value="${(item.beginTime?string('yyyy-MM-dd HH:mm:ss'))!''}" readonly="readonly"/>
            <@input label="过期时间" name="expireTime" value="${(item.expireTime?string('yyyy-MM-dd HH:mm:ss'))!''}" readonly="readonly"/>
            <@input label="操作人t_user" name="operator" value="${(item.operator)!''}" readonly="readonly"/>
            <@input label="描述" name="description" value="${(item.description)!''}" readonly="readonly"/>
            <@input label="备注" name="remark" value="${(item.remark)!''}" readonly="readonly"/>
            <@input label="0: 未使用 1:已使用" name="status" value="${(item.status)!''}" readonly="readonly"/>
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
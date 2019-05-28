<#include "../include/public.ftl"/>

<@head_add_edit title="预约-详情"/>

    <@body_empty>
        <@form_padding title="详情预约">
            <input type="hidden" name="id" value="${item.id!''}"/>
            <@input label="订单uuid" name="order" value="${(item.order)!''}" readonly="readonly"/>
            <@input label="特殊情况" name="exceptionalCase" value="${(item.exceptionalCase)!''}" readonly="readonly"/>
            <@input label="注意事项" name="notes" value="${(item.notes)!''}" readonly="readonly"/>
            <@input label="预约时间" name="appointmentTime" value="${(item.appointmentTime?string('yyyy-MM-dd HH:mm:ss'))!''}" readonly="readonly"/>
            <@input label="操作人" name="operator" value="${(item.operator)!''}" readonly="readonly"/>
            <@input label="备注" name="remark" value="${(item.remark)!''}" readonly="readonly"/>
            <@input label="状态（0=预约中，1=已预约）" name="status" value="${(item.status)!''}" readonly="readonly"/>
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
<#include "../include/public.ftl"/>

<@head_add_edit title="护士流水表-详情"/>

    <@body_empty>
        <@form_padding title="详情护士流水表">
            <input type="hidden" name="id" value="${item.id!''}"/>
            <@input label="护士uuid" name="nurse" value="${(item.nurse)!''}" readonly="readonly"/>
            <@input label="预约uuid" name="appointment" value="${(item.appointment)!''}" readonly="readonly"/>
            <@input label="名称" name="name" value="${(item.name)!''}" readonly="readonly"/>
            <@input label="金额" name="price" value="${(item.price)!''}" readonly="readonly"/>
            <@input label="类型(0:收入 1:支出)" name="type" value="${(item.type)!''}" readonly="readonly"/>
            <@input label="余额" name="balance" value="${(item.balance)!''}" readonly="readonly"/>
            <@input label="状态" name="status" value="${(item.status)!''}" readonly="readonly"/>
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
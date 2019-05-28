<#include "../include/public.ftl"/>

<@head_add_edit title="条码-详情"/>

    <@body_empty>
        <@form_padding title="详情条码">
            <input type="hidden" name="id" value="${item.id!''}"/>
            <@input label="预约" name="appointment" value="${(item.appointment)!''}" readonly="readonly"/>
            <@input label="采血管" name="tube" value="${(item.tube)!''}" readonly="readonly"/>
            <@input label="条形码" name="barcoding" value="${(item.barcoding)!''}" readonly="readonly"/>
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
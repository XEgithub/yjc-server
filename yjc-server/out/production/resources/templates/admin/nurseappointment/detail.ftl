<#include "../include/public.ftl"/>

<@head_add_edit title="护士预约关系表-详情"/>

    <@body_empty>
        <@form_padding title="详情护士预约关系表">
            <input type="hidden" name="id" value="${item.id!''}"/>
            <@input label="护士" name="nurse" value="${(item.nurse)!''}" readonly="readonly"/>
            <@input label="预约" name="appointment" value="${(item.appointment)!''}" readonly="readonly"/>
            <@input label="状态 0:派单中 1:已拒绝 2:已超时 3:用户取消 4:护士取消 5:已完成" name="status" value="${(item.status)!''}" readonly="readonly"/>
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
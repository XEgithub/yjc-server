<#include "../include/public.ftl"/>

<@head_add_edit title="护士预约关系表-编辑"/>

    <@body_empty>
        <@form_padding title="编辑护士预约关系表">
            <input type="hidden" name="id" value="${item.id!''}"/>
            <@input label="护士" name="nurse" value="${(item.nurse)!''}" placeholder="请输入护士" require="false"/>
            <@input label="预约" name="appointment" value="${(item.appointment)!''}" placeholder="请输入预约" require="false"/>
            <@input label="状态 0:派单中 1:已拒绝 2:已超时 3:用户取消 4:护士取消 5:已完成" name="status" value="${(item.status)!''}" placeholder="请输入状态 0:派单中 1:已拒绝 2:已超时 3:用户取消 4:护士取消 5:已完成" require="false"/>
            <@input label="预留" name="reserve" value="${(item.reserve)!''}" placeholder="请输入预留" require="false"/>
            <@select label="下拉框" name="name">
                <option value="xxxx">选择一</option>
                <option value="yyyy">选择二</option>
                <option value="zzzz">选择三</option>
            </@select>
            <button class="layui-btn" lay-submit lay-filter="*" style="display: none">隐藏提交，勿删！！！</button>
        </@form_padding>
    </@body_empty>
<@footer>
<script>
    layui.use(['element', 'form', 'laydate'], function(){
        var form = layui.form;
        var $ = layui.jquery;
        var laydate = layui.laydate;


        form.on('submit(*)', function(data){
            $.ajax({
                type: "POST",
                url: "/admin/nurseappointment/update",
                contentType: "application/json",
                data: JSON.stringify(data.field),
                success: function (res) {
                    if (res.code === 0) {
                        parent.layui.table.reload('myTable');
                        layer.msg("更新成功！");
                        parent.layer.closeAll();
                    } else {
                        layer.alert(res.msg);
                    }
                }
            });
            return false;
        });
    });
</script>
</@footer>
<#include "../include/public.ftl"/>

<@head_add_edit title="角色-编辑"/>

    <@body_empty>
        <@form_padding title="编辑角色">
            <input type="hidden" name="id" value="${item.id!''}"/>
            <@input label="名称" name="name" value="${(item.name)!''}" placeholder="请输入名称" require="false"/>
            <@input label="角色标识" name="role" value="${(item.role)!''}" placeholder="请输入角色标识" require="false"/>
            <@input label="备注" name="remark" value="${(item.remark)!''}" placeholder="请输入备注" require="false"/>
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
                url: "/admin/role/update",
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
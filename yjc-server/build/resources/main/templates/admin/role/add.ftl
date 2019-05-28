<#include "../include/public.ftl"/>

<@head_add_edit title="角色-新增"/>

    <@body_empty>
        <@form_padding title="新增角色">
            <@input label="名称" name="name" placeholder="请输入名称" require="false"/>
            <@input label="角色标识" name="role" placeholder="请输入角色标识" require="false"/>
            <@input label="备注" name="remark" placeholder="请输入备注" require="false"/>
            <button class="layui-btn" lay-submit lay-filter="*" style="display: none">隐藏提交，勿删！！！</button>
        </@form_padding>
    </@body_empty>
<@footer>
<script>
    layui.use(['element', 'form', 'laydate'], function(){
        var form = layui.form;
        var $ = layui.jquery;
        var laydate = layui.laydate;
        var flag = true;
        form.on('submit(*)', function(data){
            if (flag){
                $.ajax({
                    type: "POST",
                    url: "/admin/role/add",
                    contentType: "application/json",
                    data: JSON.stringify(data.field),
                    success: function (res) {
                        if (res.code === 0) {
                            parent.layui.table.reload('myTable');
                            layer.msg("添加成功！");
                            parent.layer.closeAll();
                            flag = false;
                        } else {
                            layer.alert(res.msg);
                        }
                    }
                });
                return false;
            }

        });
    });
</script>
</@footer>
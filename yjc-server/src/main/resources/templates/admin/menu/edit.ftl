<#include "../include/public.ftl"/>

<@head_add_edit title="菜单-编辑"/>

    <@body_empty>
        <@form_padding title="编辑菜单">
            <input type="hidden" name="id" value="${item.id!''}"/>
            <@input label="上级" name="pName" value="${(parent.name)!''}" readonly="readonly"/>
            <@input label="名称" name="name" value="${(item.name)!''}" placeholder="请输入名称" require="false"/>
            <@input label="路径" name="url" value="${(item.url)!''}" placeholder="请输入路径" require="false"/>
            <@input label="图标" name="icon" value="${(item.icon)!''}" placeholder="请输入图标" require="false"/>
            <@input label="备注" name="remark" value="${(item.remark)!''}" placeholder="请输入备注" require="false"/>
            <@input label="排序" name="sortt" value="${(item.sortt)!''}" placeholder="请输入排序" require="false"/>
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
                url: "/admin/menu/update",
                contentType: "application/json",
                data: JSON.stringify(data.field),
                success: function (res) {
                    if (res.code === 0) {
                        parent.layui.table.reload('myTable');
                        parent.layui.dtree.reload('myTree');
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
<#include "../include/public.ftl"/>

<@head_add_edit title="微信用户-新增"/>

    <@body_empty>
        <@form_padding title="新增微信用户">
            <input type="hidden" name="pid" value="1">
            <@input label="上级" name="pName" value="根节点" readonly="readonly"/>
            <@input label="名称" name="name" placeholder="请输入名称" require="false"/>
            <@input label="路径" name="url" placeholder="请输入路径" require="false"/>
            <@input label="图标" name="icon" placeholder="请输入图标" require="false"/>
            <@input label="备注" name="remark" placeholder="请输入备注" require="false"/>
            <@input label="排序" name="sortt" placeholder="请输入排序" require="false"/>
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
                url: "/admin/classify/add",
                contentType: "application/json",
                data: JSON.stringify(data.field),
                success: function (res) {
                    if (res.code === 0) {
                        parent.layui.table.reload('myTable');
                        parent.layui.dtree.reload('myTree');
                        layer.msg("添加成功！");
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
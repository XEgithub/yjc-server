<#include "../include/public.ftl"/>

<@head_add_edit title="轮播图-新增"/>

    <@body_empty>
        <@form_padding title="新增轮播图">
            <@input label="logo" name="logo" placeholder="请输入logo" require="false"/>
            <@input label="名称" name="name" placeholder="请输入名称" require="false"/>
            <@input label="地址" name="address" placeholder="请输入地址" require="false"/>
            <@input label="手机" name="phone" placeholder="请输入手机" require="false"/>
            <@input label="座机" name="tel" placeholder="请输入座机" require="false"/>
            <@input label="描述" name="description" placeholder="请输入描述" require="false"/>
            <@input label="备注" name="remark" placeholder="请输入备注" require="false"/>
            <@input label="备注1" name="remark1" placeholder="请输入备注1" require="false"/>
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
                url: "/admin/hospital/add",
                contentType: "application/json",
                data: JSON.stringify(data.field),
                success: function (res) {
                    if (res.code === 0) {
                        parent.layui.table.reload('myTable');
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
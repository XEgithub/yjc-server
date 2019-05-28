<#include "../include/public.ftl"/>

<@head_add_edit title="轮播图-新增"/>

    <@body_empty>
        <@form_padding title="新增轮播图">
            <@input label="项目uuid" name="project" placeholder="请输入项目uuid" require="false"/>
            <@input label="单项目/组合项目 project/group" name="type" placeholder="请输入单项目/组合项目 project/group" require="false"/>
            <@input label="分类uuid" name="classify" placeholder="请输入分类uuid" require="false"/>
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
                url: "/admin/projectshome/add",
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
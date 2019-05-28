<#include "../include/public.ftl"/>

<@head_add_edit title="轮播图-编辑"/>

    <@body_empty>
        <@form_padding title="编辑轮播图">
            <input type="hidden" name="id" value="${item.id!''}"/>
            <@input label="项目uuid" name="project" value="${(item.project)!''}" placeholder="请输入项目uuid" require="false"/>
            <@input label="单项目/组合项目 project/group" name="type" value="${(item.type)!''}" placeholder="请输入单项目/组合项目 project/group" require="false"/>
            <@input label="分类uuid" name="classify" value="${(item.classify)!''}" placeholder="请输入分类uuid" require="false"/>
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
                url: "/admin/projectshome/update",
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
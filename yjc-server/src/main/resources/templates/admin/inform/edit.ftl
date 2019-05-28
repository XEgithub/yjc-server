<#include "../include/public.ftl"/>

<@head_add_edit title="微信用户-编辑"/>

    <@body_empty>
        <@form_padding title="编辑微信用户">
            <input type="hidden" name="id" value="${item.id!''}"/>
            <@input label="用户uuid" name="user" value="${(item.user)!''}" placeholder="请输入用户uuid" require="false"/>
            <@input label="标题" name="title" value="${(item.title)!''}" placeholder="请输入标题" require="false"/>
            <@input label="内容" name="content" value="${(item.content)!''}" placeholder="请输入内容" require="false"/>
            <@input label="0:未读 1:已读" name="read" value="${(item.read)!''}" placeholder="请输入0:未读 1:已读" require="false"/>
            <@input label="状态" name="status" value="${(item.status)!''}" placeholder="请输入状态" require="false"/>
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
                url: "/admin/inform/update",
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
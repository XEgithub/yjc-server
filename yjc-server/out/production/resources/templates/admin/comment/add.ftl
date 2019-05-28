<#include "../include/public.ftl"/>

<@head_add_edit title="评论-新增"/>

    <@body_empty>
        <@form_padding title="新增评论">
            <@input label="订单uuid" name="order" placeholder="请输入订单uuid" require="false"/>
            <@input label="项目uuid" name="project" placeholder="请输入项目uuid" require="false"/>
            <@input label="用户uuid" name="user" placeholder="请输入用户uuid" require="false"/>
            <@input label="内容" name="content" placeholder="请输入内容" require="false"/>
            <@input label="项目指星(1-5星)" name="grade" placeholder="请输入项目指星(1-5星)" require="false"/>
            <@input label="就医人" name="patient" placeholder="请输入就医人" require="false"/>
            <@input label="护士" name="nurse" placeholder="请输入护士" require="false"/>
            <@input label="图片" name="image" placeholder="请输入图片" require="false"/>
            <@input label="描述" name="description" placeholder="请输入描述" require="false"/>
            <@input label="状态" name="status" placeholder="请输入状态" require="false"/>
            <@input label="预留" name="reserve" placeholder="请输入预留" require="false"/>
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
                url: "/admin/comment/add",
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
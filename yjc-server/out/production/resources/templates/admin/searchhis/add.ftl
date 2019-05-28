<#include "../include/public.ftl"/>

<@head_add_edit title="历史搜索-新增"/>

    <@body_empty>
        <@form_padding title="新增历史搜索">
            <@input label="搜索关键字" name="searchName" placeholder="请输入搜索关键字" require="false"/>
            <@input label="搜索次数" name="count" placeholder="请输入搜索次数" require="false"/>
            <@input label="用户" name="userId" placeholder="请输入用户" require="false"/>
            <@input label="0:不作为历史记录显示 1:作为历史记录显示" name="status" placeholder="请输入0:不作为历史记录显示 1:作为历史记录显示" require="false"/>
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
                url: "/admin/searchhis/add",
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
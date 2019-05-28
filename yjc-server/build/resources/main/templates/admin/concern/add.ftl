<#include "../include/public.ftl"/>

<@head_add_edit title="关注-新增"/>

    <@body_empty>
        <@form_padding title="新增关注">
            <@input label="用户uuid" name="user" placeholder="请输入用户uuid" require="false"/>
            <@input label="项目uuid" name="project" placeholder="请输入项目uuid" require="false"/>
            <@input label="项目类型(project表示单项目 groupproject表示组合项目)" name="type" placeholder="请输入项目类型(project表示单项目 groupproject表示组合项目)" require="false"/>
            <@input label="项目图片" name="image" placeholder="请输入项目图片" require="false"/>
            <@input label="项目名称" name="name" placeholder="请输入项目名称" require="false"/>
            <@input label="项目原价" name="oldPrice" placeholder="请输入项目原价" require="false"/>
            <@input label="项目现价" name="nowPrice" placeholder="请输入项目现价" require="false"/>
            <@input label="项目描述" name="description" placeholder="请输入项目描述" require="false"/>
            <@input label="关注时间" name="concernTime" placeholder="请输入关注时间" require="false" id="concernTimeDatePick"/>
            <@input label="取消关注时间" name="cancelTime" placeholder="请输入取消关注时间" require="false" id="cancelTimeDatePick"/>
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
        laydate.render({
            elem: '#concernTimeDatePick'
        });
        laydate.render({
            elem: '#cancelTimeDatePick'
        });
        form.on('submit(*)', function(data){
            $.ajax({
                type: "POST",
                url: "/admin/concern/add",
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
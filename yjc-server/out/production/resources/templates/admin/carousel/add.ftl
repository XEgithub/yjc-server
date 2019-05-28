<#include "../include/public.ftl"/>

<@head_add_edit title="轮播图-新增"/>

    <@body_empty>
        <@form_padding title="新增轮播图">
            <@input label="图片" name="image" placeholder="请输入图片" require="false"/>
            <@input label="跳转链接" name="url" placeholder="请输入跳转链接" require="false"/>
            <@input label="0:轮播图 1:固定显示图" name="alone" placeholder="请输入0:轮播图 1:固定显示图" require="false"/>
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
        form.on('submit(*)', function(data){
            $.ajax({
                type: "POST",
                url: "/admin/carousel/add",
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
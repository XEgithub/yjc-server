<#include "../include/public.ftl"/>

<@head_add_edit title="预约-新增"/>

    <@body_empty>
        <@form_padding title="新增预约">
            <@input label="订单uuid" name="order" placeholder="请输入订单uuid" require="false"/>
            <@input label="特殊情况" name="exceptionalCase" placeholder="请输入特殊情况" require="false"/>
            <@input label="注意事项" name="notes" placeholder="请输入注意事项" require="false"/>
            <@input label="预约时间" name="appointmentTime" placeholder="请输入预约时间" require="false" id="appointmentTimeDatePick"/>
            <@input label="操作人" name="operator" placeholder="请输入操作人" require="false"/>
            <@input label="备注" name="remark" placeholder="请输入备注" require="false"/>
            <@input label="状态（0=预约中，1=已预约）" name="status" placeholder="请输入状态（0=预约中，1=已预约）" require="false"/>
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
            elem: '#appointmentTimeDatePick'
        });
        form.on('submit(*)', function(data){
            $.ajax({
                type: "POST",
                url: "/admin/appointment/add",
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
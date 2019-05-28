<#include "../include/public.ftl"/>

<@head_add_edit title="护士流水表-编辑"/>

    <@body_empty>
        <@form_padding title="编辑护士流水表">
            <input type="hidden" name="id" value="${item.id!''}"/>
            <@input label="护士uuid" name="nurse" value="${(item.nurse)!''}" placeholder="请输入护士uuid" require="false"/>
            <@input label="预约uuid" name="appointment" value="${(item.appointment)!''}" placeholder="请输入预约uuid" require="false"/>
            <@input label="名称" name="name" value="${(item.name)!''}" placeholder="请输入名称" require="false"/>
            <@input label="金额" name="price" value="${(item.price)!''}" placeholder="请输入金额" require="false"/>
            <@input label="类型(0:收入 1:支出)" name="type" value="${(item.type)!''}" placeholder="请输入类型(0:收入 1:支出)" require="false"/>
            <@input label="余额" name="balance" value="${(item.balance)!''}" placeholder="请输入余额" require="false"/>
            <@input label="状态" name="status" value="${(item.status)!''}" placeholder="请输入状态" require="false"/>
            <@input label="备注" name="remark" value="${(item.remark)!''}" placeholder="请输入备注" require="false"/>
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
                url: "/admin/bill/update",
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
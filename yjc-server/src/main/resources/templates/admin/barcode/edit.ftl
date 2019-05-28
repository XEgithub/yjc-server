<#include "../include/public.ftl"/>

<@head_add_edit title="条码-编辑"/>

    <@body_empty>
        <@form_padding title="编辑条码">
            <input type="hidden" name="id" value="${item.id!''}"/>
            <@input label="预约" name="appointment" value="${(item.appointment)!''}" placeholder="请输入预约" require="false"/>
            <@input label="采血管" name="tube" value="${(item.tube)!''}" placeholder="请输入采血管" require="false"/>
            <@input label="条形码" name="barcoding" value="${(item.barcoding)!''}" placeholder="请输入条形码" require="false"/>
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
                url: "/admin/barcode/update",
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
<#include "../include/public.ftl"/>

<@head_add_edit title="患者-新增"/>

    <@body_empty>
        <@form_padding title="新增患者">
            <@input label="用户" name="user" placeholder="请输入用户" require="false"/>
            <@input label="姓名" name="name" placeholder="请输入姓名" require="false"/>
            <@input label="性别" name="sex" placeholder="请输入性别" require="false"/>
            <@input label="年龄" name="age" placeholder="请输入年龄" require="false"/>
            <@input label="体重" name="weight" placeholder="请输入体重" require="false"/>
            <@input label="身高" name="height" placeholder="请输入身高" require="false"/>
            <@input label="手机号" name="phone" placeholder="请输入手机号" require="false"/>
            <@input label="生日" name="birthday" placeholder="请输入生日" require="false"/>
            <@input label="地址" name="address" placeholder="请输入地址" require="false"/>
            <@input label="证件类型" name="certificatesType" placeholder="请输入证件类型" require="false"/>
            <@input label="证件号码" name="certificatesNo" placeholder="请输入证件号码" require="false"/>
            <@input label="是否默认(0表示不默认, 1表示默认)" name="default" placeholder="请输入是否默认(0表示不默认, 1表示默认)" require="false"/>
            <@input label="描述" name="description" placeholder="请输入描述" require="false"/>
            <@input label="状态" name="status" placeholder="请输入状态" require="false"/>
            <@input label="备注" name="remark" placeholder="请输入备注" require="false"/>
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
                url: "/admin/patient/add",
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
<#include "../include/public.ftl"/>

<@head_add_edit title="微信用户扩展-新增"/>

    <@body_empty>
        <@form_padding title="新增微信用户扩展">
            <@input label="wx_userUuid" name="wxUser" placeholder="请输入wx_userUuid" require="false"/>
            <@input label="头像" name="image" placeholder="请输入头像" require="false"/>
            <@input label="姓名" name="name" placeholder="请输入姓名" require="false"/>
            <@input label="昵称" name="nickname" placeholder="请输入昵称" require="false"/>
            <@input label="性别" name="sex" placeholder="请输入性别" require="false"/>
            <@input label="年龄" name="age" placeholder="请输入年龄" require="false"/>
            <@input label="体重" name="weight" placeholder="请输入体重" require="false"/>
            <@input label="身高" name="height" placeholder="请输入身高" require="false"/>
            <@input label="手机" name="phone" placeholder="请输入手机" require="false"/>
            <@input label="生日" name="birthday" placeholder="请输入生日" require="false"/>
            <@input label="地址" name="address" placeholder="请输入地址" require="false"/>
            <@input label="身份证" name="idCard" placeholder="请输入身份证" require="false"/>
            <@input label="身份" name="identity" placeholder="请输入身份" require="false"/>
            <@input label="来源" name="source" placeholder="请输入来源" require="false"/>
            <@input label="积分" name="integral" placeholder="请输入积分" require="false"/>
            <@input label="操作人" name="operator" placeholder="请输入操作人" require="false"/>
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
                url: "/admin/wxuserextend/add",
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
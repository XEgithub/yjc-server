<#include "../include/public.ftl"/>

<@head_add_edit title="用户-新增"/>

    <@body_empty>
        <@form_padding title="新增用户">
            <@input label="昵称" name="nickname" placeholder="请输入昵称" require="false"/>
            <@input label="用户名" name="username" placeholder="请输入用户名" require="false"/>
            <@input label="性别：0:男 1:女 2:未知" name="sex" placeholder="请输入性别：0:男 1:女 2:未知" require="false"/>
            <@input label="密码" name="password" placeholder="请输入密码" require="false"/>
            <@input label="头像" name="avatar" placeholder="请输入头像" require="false"/>
            <@input label="手机" name="phone" placeholder="请输入手机" require="false"/>
            <@input label="电话" name="tel" placeholder="请输入电话" require="false"/>
            <@input label="邮箱" name="email" placeholder="请输入邮箱" require="false"/>
            <@input label="身份证" name="idcard" placeholder="请输入身份证" require="false"/>
            <@input label="生日" name="birthday" placeholder="请输入生日" require="false" id="birthdayDatePick"/>
            <@input label="地区" name="area" placeholder="请输入地区" require="false"/>
            <@input label="签名" name="autograph" placeholder="请输入签名" require="false"/>
            <@input label="微信openid" name="openid" placeholder="请输入微信openid" require="false"/>
            <@input label="状态：0:未激活 1:激活" name="active" placeholder="请输入状态：0:未激活 1:激活" require="false"/>
            <@input label="推荐人" name="referee" placeholder="请输入推荐人" require="false"/>
            <@input label="最后登录时间" name="lastLoginTime" placeholder="请输入最后登录时间" require="false" id="lastLoginTimeDatePick"/>
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
            elem: '#birthdayDatePick'
        });
        laydate.render({
            elem: '#lastLoginTimeDatePick'
        });
        form.on('submit(*)', function(data){
            $.ajax({
                type: "POST",
                url: "/admin/user/add",
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
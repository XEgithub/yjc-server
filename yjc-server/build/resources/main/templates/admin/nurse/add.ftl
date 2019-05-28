<#include "../include/public.ftl"/>

<@head_add_edit title="护士-新增"/>

    <@body_empty>
        <@form_padding title="新增护士">
            <@input label="昵称" name="nickName" placeholder="请输入昵称" require="false"/>
            <@input label="姓名" name="name" placeholder="请输入姓名" require="false"/>
            <@input label="年龄" name="age" placeholder="请输入年龄" require="false"/>
            <@input label="性别" name="sex" placeholder="请输入性别" require="false"/>
            <@input label="头像" name="logo" placeholder="请输入头像" require="false"/>
            <@input label="证件类型" name="certificatesType" placeholder="请输入证件类型" require="false"/>
            <@input label="证件号码" name="certificatesNo" placeholder="请输入证件号码" require="false"/>
            <@input label="手机号码" name="phone" placeholder="请输入手机号码" require="false"/>
            <@input label="身份证照片（正）" name="idCardJust" placeholder="请输入身份证照片（正）" require="false"/>
            <@input label="身份证照片（反）" name="idCardAgainst" placeholder="请输入身份证照片（反）" require="false"/>
            <@input label="专业技术资格证书" name="professionImage" placeholder="请输入专业技术资格证书" require="false"/>
            <@input label="护士执业证书" name="practiceImage" placeholder="请输入护士执业证书" require="false"/>
            <@input label="胸牌照片" name="chestCardImage" placeholder="请输入胸牌照片" require="false"/>
            <@input label="住址" name="address" placeholder="请输入住址" require="false"/>
            <@input label="就职医院" name="working" placeholder="请输入就职医院" require="false"/>
            <@input label="用户" name="user" placeholder="请输入用户" require="false"/>
            <@input label="医院" name="hospital" placeholder="请输入医院" require="false"/>
            <@input label="个人评价" name="evaluate" placeholder="请输入个人评价" require="false"/>
            <@input label="累计订单数量" name="totalNumber" placeholder="请输入累计订单数量" require="false"/>
            <@input label="余额" name="balance" placeholder="请输入余额" require="false"/>
            <@input label="积分" name="score" placeholder="请输入积分" require="false"/>
            <@input label="备注" name="remark" placeholder="请输入备注" require="false"/>
            <@input label="操作人" name="operator" placeholder="请输入操作人" require="false"/>
            <@input label="描述" name="description" placeholder="请输入描述" require="false"/>
            <@input label="状态（0,1,2,3；默认创建，审核中，审核失败，上线）" name="status" placeholder="请输入状态（0,1,2,3；默认创建，审核中，审核失败，上线）" require="false"/>
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
                url: "/admin/nurse/add",
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
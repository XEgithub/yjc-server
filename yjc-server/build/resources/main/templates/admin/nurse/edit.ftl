<#include "../include/public.ftl"/>

<@head_add_edit title="护士-编辑"/>

    <@body_empty>
        <@form_padding title="编辑护士">
            <input type="hidden" name="id" value="${item.id!''}"/>
            <@input label="昵称" name="nickName" value="${(item.nickName)!''}" placeholder="请输入昵称" require="false"/>
            <@input label="姓名" name="name" value="${(item.name)!''}" placeholder="请输入姓名" require="false"/>
            <@input label="年龄" name="age" value="${(item.age)!''}" placeholder="请输入年龄" require="false"/>
            <@input label="性别" name="sex" value="${(item.sex)!''}" placeholder="请输入性别" require="false"/>
            <@input label="头像" name="logo" value="${(item.logo)!''}" placeholder="请输入头像" require="false"/>
            <@input label="证件类型" name="certificatesType" value="${(item.certificatesType)!''}" placeholder="请输入证件类型" require="false"/>
            <@input label="证件号码" name="certificatesNo" value="${(item.certificatesNo)!''}" placeholder="请输入证件号码" require="false"/>
            <@input label="手机号码" name="phone" value="${(item.phone)!''}" placeholder="请输入手机号码" require="false"/>
            <@input label="身份证照片（正）" name="idCardJust" value="${(item.idCardJust)!''}" placeholder="请输入身份证照片（正）" require="false"/>
            <@input label="身份证照片（反）" name="idCardAgainst" value="${(item.idCardAgainst)!''}" placeholder="请输入身份证照片（反）" require="false"/>
            <@input label="专业技术资格证书" name="professionImage" value="${(item.professionImage)!''}" placeholder="请输入专业技术资格证书" require="false"/>
            <@input label="护士执业证书" name="practiceImage" value="${(item.practiceImage)!''}" placeholder="请输入护士执业证书" require="false"/>
            <@input label="胸牌照片" name="chestCardImage" value="${(item.chestCardImage)!''}" placeholder="请输入胸牌照片" require="false"/>
            <@input label="住址" name="address" value="${(item.address)!''}" placeholder="请输入住址" require="false"/>
            <@input label="就职医院" name="working" value="${(item.working)!''}" placeholder="请输入就职医院" require="false"/>
            <@input label="用户" name="user" value="${(item.user)!''}" placeholder="请输入用户" require="false"/>
            <@input label="医院" name="hospital" value="${(item.hospital)!''}" placeholder="请输入医院" require="false"/>
            <@input label="个人评价" name="evaluate" value="${(item.evaluate)!''}" placeholder="请输入个人评价" require="false"/>
            <@input label="累计订单数量" name="totalNumber" value="${(item.totalNumber)!''}" placeholder="请输入累计订单数量" require="false"/>
            <@input label="余额" name="balance" value="${(item.balance)!''}" placeholder="请输入余额" require="false"/>
            <@input label="积分" name="score" value="${(item.score)!''}" placeholder="请输入积分" require="false"/>
            <@input label="备注" name="remark" value="${(item.remark)!''}" placeholder="请输入备注" require="false"/>
            <@input label="操作人" name="operator" value="${(item.operator)!''}" placeholder="请输入操作人" require="false"/>
            <@input label="描述" name="description" value="${(item.description)!''}" placeholder="请输入描述" require="false"/>
            <@input label="状态（0,1,2,3；默认创建，审核中，审核失败，上线）" name="status" value="${(item.status)!''}" placeholder="请输入状态（0,1,2,3；默认创建，审核中，审核失败，上线）" require="false"/>
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
                url: "/admin/nurse/update",
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
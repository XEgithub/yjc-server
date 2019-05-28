<#include "../include/public.ftl"/>

<@head_add_edit title="护士-详情"/>

    <@body_empty>
        <@form_padding title="详情护士">
            <input type="hidden" name="id" value="${item.id!''}"/>
            <@input label="昵称" name="nickName" value="${(item.nickName)!''}" readonly="readonly"/>
            <@input label="姓名" name="name" value="${(item.name)!''}" readonly="readonly"/>
            <@input label="年龄" name="age" value="${(item.age)!''}" readonly="readonly"/>
            <@input label="性别" name="sex" value="${(item.sex)!''}" readonly="readonly"/>
            <@input label="头像" name="logo" value="${(item.logo)!''}" readonly="readonly"/>
            <@input label="证件类型" name="certificatesType" value="${(item.certificatesType)!''}" readonly="readonly"/>
            <@input label="证件号码" name="certificatesNo" value="${(item.certificatesNo)!''}" readonly="readonly"/>
            <@input label="手机号码" name="phone" value="${(item.phone)!''}" readonly="readonly"/>
            <@input label="身份证照片（正）" name="idCardJust" value="${(item.idCardJust)!''}" readonly="readonly"/>
            <@input label="身份证照片（反）" name="idCardAgainst" value="${(item.idCardAgainst)!''}" readonly="readonly"/>
            <@input label="专业技术资格证书" name="professionImage" value="${(item.professionImage)!''}" readonly="readonly"/>
            <@input label="护士执业证书" name="practiceImage" value="${(item.practiceImage)!''}" readonly="readonly"/>
            <@input label="胸牌照片" name="chestCardImage" value="${(item.chestCardImage)!''}" readonly="readonly"/>
            <@input label="住址" name="address" value="${(item.address)!''}" readonly="readonly"/>
            <@input label="就职医院" name="working" value="${(item.working)!''}" readonly="readonly"/>
            <@input label="用户" name="user" value="${(item.user)!''}" readonly="readonly"/>
            <@input label="医院" name="hospital" value="${(item.hospital)!''}" readonly="readonly"/>
            <@input label="个人评价" name="evaluate" value="${(item.evaluate)!''}" readonly="readonly"/>
            <@input label="累计订单数量" name="totalNumber" value="${(item.totalNumber)!''}" readonly="readonly"/>
            <@input label="余额" name="balance" value="${(item.balance)!''}" readonly="readonly"/>
            <@input label="积分" name="score" value="${(item.score)!''}" readonly="readonly"/>
            <@input label="备注" name="remark" value="${(item.remark)!''}" readonly="readonly"/>
            <@input label="操作人" name="operator" value="${(item.operator)!''}" readonly="readonly"/>
            <@input label="描述" name="description" value="${(item.description)!''}" readonly="readonly"/>
            <@input label="状态（0,1,2,3；默认创建，审核中，审核失败，上线）" name="status" value="${(item.status)!''}" readonly="readonly"/>
            <@input label="预留" name="reserve" value="${(item.reserve)!''}" readonly="readonly"/>
        </@form_padding>
    </@body_empty>
<@footer>
<script>
    layui.use(['element', 'form'], function(){
        var form = layui.form;
        var $ = layui.jquery;

    });
</script>
</@footer>
<#include "../include/public.ftl"/>

<@head_add_edit title="患者-详情"/>

    <@body_empty>
        <@form_padding title="详情患者">
            <input type="hidden" name="id" value="${item.id!''}"/>
            <@input label="用户" name="user" value="${(item.user)!''}" readonly="readonly"/>
            <@input label="姓名" name="name" value="${(item.name)!''}" readonly="readonly"/>
            <@input label="性别" name="sex" value="${(item.sex)!''}" readonly="readonly"/>
            <@input label="年龄" name="age" value="${(item.age)!''}" readonly="readonly"/>
            <@input label="体重" name="weight" value="${(item.weight)!''}" readonly="readonly"/>
            <@input label="身高" name="height" value="${(item.height)!''}" readonly="readonly"/>
            <@input label="手机号" name="phone" value="${(item.phone)!''}" readonly="readonly"/>
            <@input label="生日" name="birthday" value="${(item.birthday)!''}" readonly="readonly"/>
            <@input label="地址" name="address" value="${(item.address)!''}" readonly="readonly"/>
            <@input label="证件类型" name="certificatesType" value="${(item.certificatesType)!''}" readonly="readonly"/>
            <@input label="证件号码" name="certificatesNo" value="${(item.certificatesNo)!''}" readonly="readonly"/>
            <@input label="是否默认(0表示不默认, 1表示默认)" name="default" value="${(item.default)!''}" readonly="readonly"/>
            <@input label="描述" name="description" value="${(item.description)!''}" readonly="readonly"/>
            <@input label="状态" name="status" value="${(item.status)!''}" readonly="readonly"/>
            <@input label="备注" name="remark" value="${(item.remark)!''}" readonly="readonly"/>
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
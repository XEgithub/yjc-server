<#include "../include/public.ftl"/>

<@head_add_edit title="微信用户扩展-详情"/>

    <@body_empty>
        <@form_padding title="详情微信用户扩展">
            <input type="hidden" name="id" value="${item.id!''}"/>
            <@input label="wx_userUuid" name="wxUser" value="${(item.wxUser)!''}" readonly="readonly"/>
            <@input label="头像" name="image" value="${(item.image)!''}" readonly="readonly"/>
            <@input label="姓名" name="name" value="${(item.name)!''}" readonly="readonly"/>
            <@input label="昵称" name="nickname" value="${(item.nickname)!''}" readonly="readonly"/>
            <@input label="性别" name="sex" value="${(item.sex)!''}" readonly="readonly"/>
            <@input label="年龄" name="age" value="${(item.age)!''}" readonly="readonly"/>
            <@input label="体重" name="weight" value="${(item.weight)!''}" readonly="readonly"/>
            <@input label="身高" name="height" value="${(item.height)!''}" readonly="readonly"/>
            <@input label="手机" name="phone" value="${(item.phone)!''}" readonly="readonly"/>
            <@input label="生日" name="birthday" value="${(item.birthday)!''}" readonly="readonly"/>
            <@input label="地址" name="address" value="${(item.address)!''}" readonly="readonly"/>
            <@input label="身份证" name="idCard" value="${(item.idCard)!''}" readonly="readonly"/>
            <@input label="身份" name="identity" value="${(item.identity)!''}" readonly="readonly"/>
            <@input label="来源" name="source" value="${(item.source)!''}" readonly="readonly"/>
            <@input label="积分" name="integral" value="${(item.integral)!''}" readonly="readonly"/>
            <@input label="操作人" name="operator" value="${(item.operator)!''}" readonly="readonly"/>
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
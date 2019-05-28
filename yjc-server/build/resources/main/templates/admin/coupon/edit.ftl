<#include "../include/public.ftl"/>

<@head_add_edit title="微信用户-编辑"/>

    <@body_empty>
        <@form_padding title="编辑微信用户">
            <input type="hidden" name="id" value="${item.id!''}"/>
            <@input label="优惠券名" name="name" value="${(item.name)!''}" placeholder="请输入优惠券名" require="false"/>
            <@input label="用户uuid" name="user" value="${(item.user)!''}" placeholder="请输入用户uuid" require="false"/>
            <@input label="最小订单金额" name="minPrice" value="${(item.minPrice)!''}" placeholder="请输入最小订单金额" require="false"/>
            <@input label="优惠金额" name="couponPrice" value="${(item.couponPrice)!''}" placeholder="请输入优惠金额" require="false"/>
            <@input label="兑换所需积分" name="needIntegral" value="${(item.needIntegral)!''}" placeholder="请输入兑换所需积分" require="false"/>
            <@input label="优惠券类型1:满减优惠券 2:服务抵用券" name="type" value="${(item.type)!''}" placeholder="请输入优惠券类型1:满减优惠券 2:服务抵用券" require="false"/>
            <@input label="适用范围(0表示不通用仅指定医院使用 1表示通用)" name="application" value="${(item.application)!''}" placeholder="请输入适用范围(0表示不通用仅指定医院使用 1表示通用)" require="false"/>
            <@input label="医院uuid" name="hospital" value="${(item.hospital)!''}" placeholder="请输入医院uuid" require="false"/>
            <@input label="开始时间" name="beginTime" value="${(item.beginTime?string('yyyy-MM-dd HH:mm:ss'))!''}" placeholder="请输入开始时间" require="false" id="beginTimeDatePick"/>
            <@input label="过期时间" name="expireTime" value="${(item.expireTime?string('yyyy-MM-dd HH:mm:ss'))!''}" placeholder="请输入过期时间" require="false" id="expireTimeDatePick"/>
            <@input label="操作人t_user" name="operator" value="${(item.operator)!''}" placeholder="请输入操作人t_user" require="false"/>
            <@input label="描述" name="description" value="${(item.description)!''}" placeholder="请输入描述" require="false"/>
            <@input label="备注" name="remark" value="${(item.remark)!''}" placeholder="请输入备注" require="false"/>
            <@input label="0: 未使用 1:已使用" name="status" value="${(item.status)!''}" placeholder="请输入0: 未使用 1:已使用" require="false"/>
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


        laydate.render({
            elem: '#beginTimeDatePick'
        });
        laydate.render({
            elem: '#expireTimeDatePick'
        });
        form.on('submit(*)', function(data){
            $.ajax({
                type: "POST",
                url: "/admin/coupon/update",
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
<#include "../include/public.ftl"/>

<@head_add_edit title="订单-详情"/>

    <@body_empty>
        <@form_padding title="详情订单">
            <input type="hidden" name="id" value="${item.id!''}"/>
            <@input label="订单编号" name="orderNo" value="${(item.orderNo)!''}" readonly="readonly"/>
            <@input label="用户uuid" name="user" value="${(item.user)!''}" readonly="readonly"/>
            <@input label="项目uuid" name="project" value="${(item.project)!''}" readonly="readonly"/>
            <@input label="项目类型(0表示单项目 1表示组合项目)" name="projectType" value="${(item.projectType)!''}" readonly="readonly"/>
            <@input label="订单状态（1:待付款 2:待预约 3:进行中 4:已完成 5:待退款 6:已退款）" name="orderStatus" value="${(item.orderStatus)!''}" readonly="readonly"/>
            <@input label="服务状态(0:已接单 1:已采样 2:已送检 3:已上传报告)" name="serviceStatus" value="${(item.serviceStatus)!''}" readonly="readonly"/>
            <@input label="接单时间" name="receiveTime" value="${(item.receiveTime?string('yyyy-MM-dd HH:mm:ss'))!''}" readonly="readonly"/>
            <@input label="采样时间" name="samplingTime" value="${(item.samplingTime?string('yyyy-MM-dd HH:mm:ss'))!''}" readonly="readonly"/>
            <@input label="送检时间" name="testTime" value="${(item.testTime?string('yyyy-MM-dd HH:mm:ss'))!''}" readonly="readonly"/>
            <@input label="上传报告时间" name="uploadReportTime" value="${(item.uploadReportTime?string('yyyy-MM-dd HH:mm:ss'))!''}" readonly="readonly"/>
            <@input label="订单地址" name="address" value="${(item.address)!''}" readonly="readonly"/>
            <@input label="就医人uuid" name="patient" value="${(item.patient)!''}" readonly="readonly"/>
            <@input label="订单地址状态(0 表示三环内, 1表示三环外)" name="range" value="${(item.range)!''}" readonly="readonly"/>
            <@input label="纸质报告(0 表示不需要, 1表示需要)" name="paperReport" value="${(item.paperReport)!''}" readonly="readonly"/>
            <@input label="优惠券uuid" name="coupon" value="${(item.coupon)!''}" readonly="readonly"/>
            <@input label="项目金额" name="projectPrice" value="${(item.projectPrice)!''}" readonly="readonly"/>
            <@input label="服务金额" name="servicePrice" value="${(item.servicePrice)!''}" readonly="readonly"/>
            <@input label="优惠金额" name="couponPrice" value="${(item.couponPrice)!''}" readonly="readonly"/>
            <@input label="订单金额" name="price" value="${(item.price)!''}" readonly="readonly"/>
            <@input label="订单退款申请时间" name="applicationRefundTime" value="${(item.applicationRefundTime?string('yyyy-MM-dd HH:mm:ss'))!''}" readonly="readonly"/>
            <@input label="订单退款时间" name="refundTime" value="${(item.refundTime?string('yyyy-MM-dd HH:mm:ss'))!''}" readonly="readonly"/>
            <@input label="用户备注" name="userRemark" value="${(item.userRemark)!''}" readonly="readonly"/>
            <@input label="操作人" name="operator" value="${(item.operator)!''}" readonly="readonly"/>
            <@input label="描述" name="description" value="${(item.description)!''}" readonly="readonly"/>
            <@input label="备注" name="remark" value="${(item.remark)!''}" readonly="readonly"/>
            <@input label="状态" name="status" value="${(item.status)!''}" readonly="readonly"/>
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
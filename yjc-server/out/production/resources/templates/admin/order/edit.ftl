<#include "../include/public.ftl"/>

<@head_add_edit title="订单-编辑"/>

    <@body_empty>
        <@form_padding title="编辑订单">
            <input type="hidden" name="id" value="${item.id!''}"/>
            <@input label="订单编号" name="orderNo" value="${(item.orderNo)!''}" placeholder="请输入订单编号" require="false"/>
            <@input label="用户uuid" name="user" value="${(item.user)!''}" placeholder="请输入用户uuid" require="false"/>
            <@input label="项目uuid" name="project" value="${(item.project)!''}" placeholder="请输入项目uuid" require="false"/>
            <@input label="项目类型(0表示单项目 1表示组合项目)" name="projectType" value="${(item.projectType)!''}" placeholder="请输入项目类型(0表示单项目 1表示组合项目)" require="false"/>
            <@input label="订单状态（1:待付款 2:待预约 3:进行中 4:已完成 5:待退款 6:已退款）" name="orderStatus" value="${(item.orderStatus)!''}" placeholder="请输入订单状态（1:待付款 2:待预约 3:进行中 4:已完成 5:待退款 6:已退款）" require="false"/>
            <@input label="服务状态(0:已接单 1:已采样 2:已送检 3:已上传报告)" name="serviceStatus" value="${(item.serviceStatus)!''}" placeholder="请输入服务状态(0:已接单 1:已采样 2:已送检 3:已上传报告)" require="false"/>
            <@input label="接单时间" name="receiveTime" value="${(item.receiveTime?string('yyyy-MM-dd HH:mm:ss'))!''}" placeholder="请输入接单时间" require="false" id="receiveTimeDatePick"/>
            <@input label="采样时间" name="samplingTime" value="${(item.samplingTime?string('yyyy-MM-dd HH:mm:ss'))!''}" placeholder="请输入采样时间" require="false" id="samplingTimeDatePick"/>
            <@input label="送检时间" name="testTime" value="${(item.testTime?string('yyyy-MM-dd HH:mm:ss'))!''}" placeholder="请输入送检时间" require="false" id="testTimeDatePick"/>
            <@input label="上传报告时间" name="uploadReportTime" value="${(item.uploadReportTime?string('yyyy-MM-dd HH:mm:ss'))!''}" placeholder="请输入上传报告时间" require="false" id="uploadReportTimeDatePick"/>
            <@input label="订单地址" name="address" value="${(item.address)!''}" placeholder="请输入订单地址" require="false"/>
            <@input label="就医人uuid" name="patient" value="${(item.patient)!''}" placeholder="请输入就医人uuid" require="false"/>
            <@input label="订单地址状态(0 表示三环内, 1表示三环外)" name="range" value="${(item.range)!''}" placeholder="请输入订单地址状态(0 表示三环内, 1表示三环外)" require="false"/>
            <@input label="纸质报告(0 表示不需要, 1表示需要)" name="paperReport" value="${(item.paperReport)!''}" placeholder="请输入纸质报告(0 表示不需要, 1表示需要)" require="false"/>
            <@input label="优惠券uuid" name="coupon" value="${(item.coupon)!''}" placeholder="请输入优惠券uuid" require="false"/>
            <@input label="项目金额" name="projectPrice" value="${(item.projectPrice)!''}" placeholder="请输入项目金额" require="false"/>
            <@input label="服务金额" name="servicePrice" value="${(item.servicePrice)!''}" placeholder="请输入服务金额" require="false"/>
            <@input label="优惠金额" name="couponPrice" value="${(item.couponPrice)!''}" placeholder="请输入优惠金额" require="false"/>
            <@input label="订单金额" name="price" value="${(item.price)!''}" placeholder="请输入订单金额" require="false"/>
            <@input label="订单退款申请时间" name="applicationRefundTime" value="${(item.applicationRefundTime?string('yyyy-MM-dd HH:mm:ss'))!''}" placeholder="请输入订单退款申请时间" require="false" id="applicationRefundTimeDatePick"/>
            <@input label="订单退款时间" name="refundTime" value="${(item.refundTime?string('yyyy-MM-dd HH:mm:ss'))!''}" placeholder="请输入订单退款时间" require="false" id="refundTimeDatePick"/>
            <@input label="用户备注" name="userRemark" value="${(item.userRemark)!''}" placeholder="请输入用户备注" require="false"/>
            <@input label="操作人" name="operator" value="${(item.operator)!''}" placeholder="请输入操作人" require="false"/>
            <@input label="描述" name="description" value="${(item.description)!''}" placeholder="请输入描述" require="false"/>
            <@input label="备注" name="remark" value="${(item.remark)!''}" placeholder="请输入备注" require="false"/>
            <@input label="状态" name="status" value="${(item.status)!''}" placeholder="请输入状态" require="false"/>
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
            elem: '#receiveTimeDatePick'
        });
        laydate.render({
            elem: '#samplingTimeDatePick'
        });
        laydate.render({
            elem: '#testTimeDatePick'
        });
        laydate.render({
            elem: '#uploadReportTimeDatePick'
        });
        laydate.render({
            elem: '#applicationRefundTimeDatePick'
        });
        laydate.render({
            elem: '#refundTimeDatePick'
        });
        form.on('submit(*)', function(data){
            $.ajax({
                type: "POST",
                url: "/admin/order/update",
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
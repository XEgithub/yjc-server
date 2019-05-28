<#include "../include/public.ftl"/>

<@head title="订单列表"/>

    <@body>
        <@search_box>
            <@button_add text="新增"/>
            <@input_search name="name" label="名称" placeholder="请输入名称" />
            <@button_search/>
            <@button_reset/>
            <@batch_delete/>
        </@search_box>

        <@table id="myTable" filter="myTable"/>

    </@body>
<@footer>

<#-- table操作栏 -->
<@table_operator/>
<script>
    layui.use(['element', 'table', 'form', 'layer'], function(){
        var element = layui.element;
        var table = layui.table;
        var form = layui.form;
        var layer = layui.layer;
        var $ = layui.jquery;


        //渲染表格
        table.render({
            elem: '#myTable',
            id: 'myTable',
            url: '/admin/order/list',
            method: 'post',
            contentType: 'application/json',
            page: true,
            request: {
                pageName: 'pageNo',
                limitName: 'pageSize'
            },
            cols: [[ //表头
                {type: 'checkbox', fixed: 'left'}, //开启复选框，固定左侧
                {field: 'id', title: 'ID', width:80, fixed: 'left', sort: true}, //固定左侧
                {field: 'orderNo', title: '订单编号', sort: true},
                {field: 'user', title: '用户uuid', sort: true},
                {field: 'project', title: '项目uuid', sort: true},
                {field: 'projectType', title: '项目类型(0表示单项目 1表示组合项目)', sort: true},
                {field: 'orderStatus', title: '订单状态（1:待付款 2:待预约 3:进行中 4:已完成 5:待退款 6:已退款）', sort: true},
                {field: 'serviceStatus', title: '服务状态(0:已接单 1:已采样 2:已送检 3:已上传报告)', sort: true},
                {field: 'receiveTime', title: '接单时间', sort: true},
                {field: 'samplingTime', title: '采样时间', sort: true},
                {field: 'testTime', title: '送检时间', sort: true},
                {field: 'uploadReportTime', title: '上传报告时间', sort: true},
                {field: 'address', title: '订单地址', sort: true},
                {field: 'patient', title: '就医人uuid', sort: true},
                {field: 'range', title: '订单地址状态(0 表示三环内, 1表示三环外)', sort: true},
                {field: 'paperReport', title: '纸质报告(0 表示不需要, 1表示需要)', sort: true},
                {field: 'coupon', title: '优惠券uuid', sort: true},
                {field: 'projectPrice', title: '项目金额', sort: true},
                {field: 'servicePrice', title: '服务金额', sort: true},
                {field: 'couponPrice', title: '优惠金额', sort: true},
                {field: 'price', title: '订单金额', sort: true},
                {field: 'applicationRefundTime', title: '订单退款申请时间', sort: true},
                {field: 'refundTime', title: '订单退款时间', sort: true},
                {field: 'userRemark', title: '用户备注', sort: true},
                {field: 'createTime', title: '创建时间', sort: true},
                {field: 'updateTime', title: '修改时间', sort: true},
                {field: 'operator', title: '操作人', sort: true},
                {field: 'description', title: '描述', sort: true},
                {field: 'remark', title: '备注', sort: true},
                {field: 'status', title: '状态', sort: true},
                {field: 'reserve', title: '预留', sort: true},
                {title:'操作', toolbar: '#operator', width:200, fixed: 'right'} //编辑，删除操作栏，固定右侧
            ]]
        });

        //新增弹窗
        <@table_add_plugin module="order"/>
        //批量删除
        <@table_batch_delete_plugin module="order"/>
        //表格搜索
        <@table_search_plugin/>
        //表格行操作按钮
        <@table_operator_plugin module="order"/>
        //表格列排序
        <@table_sort_plugin/>
        //表格重载
        <@table_reload_plugin/>

    });
</script>
</@footer>
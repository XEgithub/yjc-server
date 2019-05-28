<#include "../include/public.ftl"/>

<@head title="微信用户列表"/>

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
            url: '/admin/coupon/list',
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
                {field: 'name', title: '优惠券名', sort: true},
                {field: 'user', title: '用户uuid', sort: true},
                {field: 'minPrice', title: '最小订单金额', sort: true},
                {field: 'couponPrice', title: '优惠金额', sort: true},
                {field: 'needIntegral', title: '兑换所需积分', sort: true},
                {field: 'type', title: '优惠券类型1:满减优惠券 2:服务抵用券', sort: true},
                {field: 'application', title: '适用范围(0表示不通用仅指定医院使用 1表示通用)', sort: true},
                {field: 'hospital', title: '医院uuid', sort: true},
                {field: 'beginTime', title: '开始时间', sort: true},
                {field: 'expireTime', title: '过期时间', sort: true},
                {field: 'createTime', title: '创建时间', sort: true},
                {field: 'updateTime', title: '修改时间', sort: true},
                {field: 'operator', title: '操作人t_user', sort: true},
                {field: 'description', title: '描述', sort: true},
                {field: 'remark', title: '备注', sort: true},
                {field: 'status', title: '0: 未使用 1:已使用', sort: true},
                {field: 'reserve', title: '预留', sort: true},
                {title:'操作', toolbar: '#operator', width:200, fixed: 'right'} //编辑，删除操作栏，固定右侧
            ]]
        });

        //新增弹窗
        <@table_add_plugin module="coupon"/>
        //批量删除
        <@table_batch_delete_plugin module="coupon"/>
        //表格搜索
        <@table_search_plugin/>
        //表格行操作按钮
        <@table_operator_plugin module="coupon"/>
        //表格列排序
        <@table_sort_plugin/>
        //表格重载
        <@table_reload_plugin/>

    });
</script>
</@footer>
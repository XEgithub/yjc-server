<#include "../include/public.ftl"/>

<@head title="患者列表"/>

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
            url: '/admin/patient/list',
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
                {field: 'user', title: '用户', sort: true},
                {field: 'name', title: '姓名', sort: true},
                {field: 'sex', title: '性别', sort: true},
                {field: 'age', title: '年龄', sort: true},
                {field: 'weight', title: '体重', sort: true},
                {field: 'height', title: '身高', sort: true},
                {field: 'phone', title: '手机号', sort: true},
                {field: 'birthday', title: '生日', sort: true},
                {field: 'address', title: '地址', sort: true},
                {field: 'certificatesType', title: '证件类型', sort: true},
                {field: 'certificatesNo', title: '证件号码', sort: true},
                {field: 'default', title: '是否默认(0表示不默认, 1表示默认)', sort: true},
                {field: 'createTime', title: '创建时间', sort: true},
                {field: 'updateTime', title: '修改时间', sort: true},
                {field: 'description', title: '描述', sort: true},
                {field: 'status', title: '状态', sort: true},
                {field: 'remark', title: '备注', sort: true},
                {field: 'reserve', title: '预留', sort: true},
                {title:'操作', toolbar: '#operator', width:200, fixed: 'right'} //编辑，删除操作栏，固定右侧
            ]]
        });

        //新增弹窗
        <@table_add_plugin module="patient"/>
        //批量删除
        <@table_batch_delete_plugin module="patient"/>
        //表格搜索
        <@table_search_plugin/>
        //表格行操作按钮
        <@table_operator_plugin module="patient"/>
        //表格列排序
        <@table_sort_plugin/>
        //表格重载
        <@table_reload_plugin/>

    });
</script>
</@footer>
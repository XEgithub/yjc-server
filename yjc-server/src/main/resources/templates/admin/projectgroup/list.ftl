<#include "../include/public.ftl"/>

<@head title="轮播图列表"/>

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
            url: '/admin/projectgroup/list',
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
                {field: 'projectType', title: '项目类型(0表示单项目 1表示组合项目)', sort: true},
                {field: 'logo', title: 'logo', sort: true},
                {field: 'banner', title: '背景', sort: true},
                {field: 'image', title: '列表图片', sort: true},
                {field: 'image1', title: '图片1', sort: true},
                {field: 'image2', title: '图片2', sort: true},
                {field: 'image3', title: '图片3', sort: true},
                {field: 'name', title: '名称', sort: true},
                {field: 'hospital', title: '医院uuid', sort: true},
                {field: 'oldPrice', title: '原价', sort: true},
                {field: 'nowPrice', title: '现价', sort: true},
                {field: 'period', title: '周期', sort: true},
                {field: 'notes', title: '注意事项', sort: true},
                {field: 'classify1', title: '首页分类uuid', sort: true},
                {field: 'classify2', title: '导航分类uuid', sort: true},
                {field: 'keyword', title: '关键词', sort: true},
                {field: 'remark', title: '备注', sort: true},
                {field: 'remark1', title: '备注1', sort: true},
                {field: 'createTime', title: '创建时间', sort: true},
                {field: 'updateTime', title: '修改时间', sort: true},
                {field: 'operator', title: '操作人', sort: true},
                {field: 'description', title: '描述', sort: true},
                {field: 'detail', title: '详情', sort: true},
                {field: 'status', title: '状态（下线=0，审核=1，审核失败=2，上线=3）', sort: true},
                {field: 'reserve', title: '预留', sort: true},
                {title:'操作', toolbar: '#operator', width:200, fixed: 'right'} //编辑，删除操作栏，固定右侧
            ]]
        });

        //新增弹窗
        <@table_add_plugin module="projectgroup"/>
        //批量删除
        <@table_batch_delete_plugin module="projectgroup"/>
        //表格搜索
        <@table_search_plugin/>
        //表格行操作按钮
        <@table_operator_plugin module="projectgroup"/>
        //表格列排序
        <@table_sort_plugin/>
        //表格重载
        <@table_reload_plugin/>

    });
</script>
</@footer>
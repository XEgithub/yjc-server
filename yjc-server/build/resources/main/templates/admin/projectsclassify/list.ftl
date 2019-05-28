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
            url: '/admin/projectsclassify/list',
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
                {field: 'project', title: '项目uuid', sort: true},
                {field: 'type', title: '单项目/组合项目 project/group', sort: true},
                {field: 'classify', title: '分类id 对应表classify', sort: true},
                {field: 'createTime', title: '创建时间', sort: true},
                {field: 'updateTime', title: '修改时间', sort: true},
                {title:'操作', toolbar: '#operator', width:200, fixed: 'right'} //编辑，删除操作栏，固定右侧
            ]]
        });

        //新增弹窗
        <@table_add_plugin module="projectsclassify"/>
        //批量删除
        <@table_batch_delete_plugin module="projectsclassify"/>
        //表格搜索
        <@table_search_plugin/>
        //表格行操作按钮
        <@table_operator_plugin module="projectsclassify"/>
        //表格列排序
        <@table_sort_plugin/>
        //表格重载
        <@table_reload_plugin/>

    });
</script>
</@footer>
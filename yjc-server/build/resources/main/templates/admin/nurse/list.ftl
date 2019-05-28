<#include "../include/public.ftl"/>

<@head title="护士列表"/>

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
            url: '/admin/nurse/list',
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
                {field: 'nickName', title: '昵称', sort: true},
                {field: 'name', title: '姓名', sort: true},
                {field: 'age', title: '年龄', sort: true},
                {field: 'sex', title: '性别', sort: true},
                {field: 'logo', title: '头像', sort: true},
                {field: 'certificatesType', title: '证件类型', sort: true},
                {field: 'certificatesNo', title: '证件号码', sort: true},
                {field: 'phone', title: '手机号码', sort: true},
                {field: 'idCardJust', title: '身份证照片（正）', sort: true},
                {field: 'idCardAgainst', title: '身份证照片（反）', sort: true},
                {field: 'professionImage', title: '专业技术资格证书', sort: true},
                {field: 'practiceImage', title: '护士执业证书', sort: true},
                {field: 'chestCardImage', title: '胸牌照片', sort: true},
                {field: 'address', title: '住址', sort: true},
                {field: 'working', title: '就职医院', sort: true},
                {field: 'user', title: '用户', sort: true},
                {field: 'hospital', title: '医院', sort: true},
                {field: 'evaluate', title: '个人评价', sort: true},
                {field: 'totalNumber', title: '累计订单数量', sort: true},
                {field: 'balance', title: '余额', sort: true},
                {field: 'score', title: '积分', sort: true},
                {field: 'remark', title: '备注', sort: true},
                {field: 'createTime', title: '创建时间', sort: true},
                {field: 'updateTime', title: '修改时间', sort: true},
                {field: 'operator', title: '操作人', sort: true},
                {field: 'description', title: '描述', sort: true},
                {field: 'status', title: '状态（0,1,2,3；默认创建，审核中，审核失败，上线）', sort: true},
                {field: 'reserve', title: '预留', sort: true},
                {title:'操作', toolbar: '#operator', width:200, fixed: 'right'} //编辑，删除操作栏，固定右侧
            ]]
        });

        //新增弹窗
        <@table_add_plugin module="nurse"/>
        //批量删除
        <@table_batch_delete_plugin module="nurse"/>
        //表格搜索
        <@table_search_plugin/>
        //表格行操作按钮
        <@table_operator_plugin module="nurse"/>
        //表格列排序
        <@table_sort_plugin/>
        //表格重载
        <@table_reload_plugin/>

    });
</script>
</@footer>
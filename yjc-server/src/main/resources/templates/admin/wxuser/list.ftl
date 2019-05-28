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
            url: '/admin/wxuser/list',
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
                {field: 'subscribe', title: '', sort: true},
                {field: 'openid', title: '', sort: true},
                {field: 'nickname', title: '昵称', sort: true},
                {field: 'sex', title: '', sort: true},
                {field: 'province', title: '省', sort: true},
                {field: 'city', title: '所在城市', sort: true},
                {field: 'country', title: '', sort: true},
                {field: 'language', title: '', sort: true},
                {field: 'headimgurl', title: '头像', sort: true},
                {field: 'subscribeTime', title: '关注时间', sort: true},
                {field: 'unionid', title: '', sort: true},
                {field: 'remark', title: '公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注', sort: true},
                {field: 'groupid', title: '用户所在的分组ID（兼容旧的用户分组接口）', sort: true},
                {field: 'tagidList', title: '用户被打上的标签ID列表', sort: true},
                {field: 'subscribeScene', title: '返回用户关注的渠道来源，ADD_SCENE_SEARCH 公众号搜索，ADD_SCENE_ACCOUNT_MIGRATION 公众号迁移，ADD_SCENE_PROFILE_CARD 名片分享，ADD_SCENE_QR_CODE 扫描二维码，ADD_SCENEPROFILE LINK 图文页内名称点击，ADD_SCENE_PROFILE_ITEM 图文页右上角菜单，ADD_SCENE_PAID 支付后关注，ADD_SCENE_OTHERS 其他', sort: true},
                {field: 'source', title: '来源', sort: true},
                {field: 'address', title: '定位地址', sort: true},
                {field: 'qrScene', title: '二维码扫码场景', sort: true},
                {field: 'qrSceneStr', title: '二维码扫码场景描述', sort: true},
                {field: 'createTime', title: '', sort: true},
                {field: 'updateTime', title: '', sort: true},
                {field: 'reserve', title: '', sort: true},
                {field: 'remark1', title: '', sort: true},
                {title:'操作', toolbar: '#operator', width:200, fixed: 'right'} //编辑，删除操作栏，固定右侧
            ]]
        });

        //新增弹窗
        <@table_add_plugin module="wxuser"/>
        //批量删除
        <@table_batch_delete_plugin module="wxuser"/>
        //表格搜索
        <@table_search_plugin/>
        //表格行操作按钮
        <@table_operator_plugin module="wxuser"/>
        //表格列排序
        <@table_sort_plugin/>
        //表格重载
        <@table_reload_plugin/>

    });
</script>
</@footer>
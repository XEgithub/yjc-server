<#include "../include/public.ftl"/>

<@head_dtree title="微信用户列表"/>

    <@body_lr>
        <@body_lt_tree>

            <@card_header title="微信用户"/>
            <@tree_dtree id="myTree"/>

        </@body_lt_tree>
        <@body_lt_list>

            <@search_box>
                <@button_add text="新增一级"/>
                <@input_search name="name" label="名称" placeholder="请输入名称" />
                <@button_search/>
                <@button_reset/>
                <@batch_delete/>
            </@search_box>

            <@table id="myTable" filter="myTable"/>

        </@body_lt_list>
    </@body_lr>

<@footer>

<#-- table操作栏 -->
<@table_operator/>
<script>
    layui.config({
        base: '/layuiadmin/layui_ext/dtree/'
    }).extend({
        dtree: 'dtree'
    }).use(['element', 'table', 'form', 'layer', 'dtree'], function(){
        var element = layui.element;
        var table = layui.table;
        var form = layui.form;
        var layer = layui.layer;
        var $ = layui.jquery;
        var dtree = layui.dtree;
        var tablePid = 1;

        //渲染树
        var myTree = dtree.render({
            elem: "#myTree",
            //async: false  // 只需将该参数设置为false，即可同步加载
            method: "GET",
            url: "/admin/classify/lazy",
            dataStyle:"layuiStyle",
            dot: false,
            icon: 5,
            toolbar:true,
            toolbarScroll:"#treeContainer",
            initLevel: 1,
            request:{
                "pid":"1"
            },
            defaultRequest: {
                nodeId: "pid"
            },
            response: {
                rootName: "list", //根节点名称
                statusCode: 0, //返回码
                title: "name", //节点名称
                isLast: "leaf" //是否最后一级节点
            },
            toolbarStyle: {
                title: "地区",
                area: ["50%", "400px"]
            },
            toolbarBtn:[
                //新增编辑框增加输入框条目
                [
                    {"label":"名称", "name":"name", "type":"text"},
                    {"label":"路径", "name":"url", "type":"text"},
                    {"label":"图标", "name":"icon", "type":"text"},
                    {"label":"备注", "name":"remark", "type":"text"},
                    {"label":"排序", "name":"sortt", "type":"text"},
                    {"label":"创建时间", "name":"createTime", "type":"text"},
                    {"label":"更新时间", "name":"updateTime", "type":"text"},
                ],
                //修改编辑框增加输入框条目
                [
                    {"label":"名称", "name":"name", "type":"text"},
                    {"label":"路径", "name":"url", "type":"text"},
                    {"label":"图标", "name":"icon", "type":"text"},
                    {"label":"备注", "name":"remark", "type":"text"},
                    {"label":"排序", "name":"sortt", "type":"text"},
                    {"label":"创建时间", "name":"createTime", "type":"text"},
                    {"label":"更新时间", "name":"updateTime", "type":"text"},
                ]
            ],
            toolbarFun: {
                //右键菜单 - 增加，需补全发送参数
                addTreeNode: function(treeNode){
                    var param = {
                        pid: treeNode.parentId,
                        name: treeNode.addNodeName,
                            ...
                        //示例：code: treeNode.code
                    };
                    $.ajax({
                        type: "POST",
                        url: "/admin/classify/add",
                        contentType: "application/json",
                        data: JSON.stringify(param),
                        success: function (res) {
                            if (res.code === 0) {
                                layer.msg('添加成功！');
                                myTree.changeTreeNodeAdd(res.data);
                                reloadTable({pid: param.pid});
                            } else {
                                layer.alert(res.msg);
                            }
                        }
                    });
                },
                //右键菜单 - 编辑框数据回显，需补全编辑框参数
                editTreeLoad: function(treeNode){
                    $.get('/admin/classify/detail/' + treeNode.pid, function (res) {
                        var param = {
                                ...
                            //示例："code" : res.data.code
                        };
                        myTree.changeTreeNodeDone(param);
                    });
                },
                //右键菜单 - 编辑，需补全发送参数
                editTreeNode: function(treeNode){
                    var param = {
                        id: treeNode.pid,
                        name: treeNode.editNodeName,
                            ...
                        //示例：code: treeNode.code
                    };
                    $.ajax({
                        type: "POST",
                        url: "/admin/classify/update",
                        contentType: "application/json",
                        data: JSON.stringify(param),
                        success: function (res) {
                            if (res.code === 0) {
                                layer.msg('更新成功！');
                                myTree.changeTreeNodeEdit(true);
                                if (param.id !== tablePid) {
                                    reloadTable({pid: param.pid});
                                }
                            } else {
                                layer.alert(res.msg);
                            }
                        }
                    });
                },
                //右键菜单 - 删除
                delTreeNode: function(treeNode){
                    $.get('/admin/classify/delete/' + treeNode.pid, function (res) {
                        if (res.code === 0) {
                            layer.msg('删除成功！');
                            myTree.changeTreeNodeDel(true);
                        } else {
                            layer.msg(res.msg);
                        }
                    });
                }
            }

        });

        //点击树节点名称事件
        dtree.on("node('myTree')" ,function(param){
            if (!param.isLeaf) {
                tablePid = param.pid;
                reloadTable({pid: param.pid});
            }
        });

        //渲染表格
        table.render({
            elem: '#myTable',
            id: 'myTable',
            url: '/admin/classify/list',
            method: 'post',
            contentType: 'application/json',
            page: true,
            request: {
                pageName: 'pageNo',
                limitName: 'pageSize'
            },
            cols: [[ //表头
                {type: 'checkbox', fixed: 'left'}, //开启复选框，固定左侧
                {field: 'name', title: '名称', sort: true},
                {field: 'url', title: '路径', sort: true},
                {field: 'icon', title: '图标', sort: true},
                {field: 'remark', title: '备注', sort: true},
                {field: 'sortt', title: '排序', sort: true},
                {field: 'createTime', title: '创建时间', sort: true},
                {field: 'updateTime', title: '更新时间', sort: true},
                {title:'操作', toolbar: '#operator', width:200, fixed: 'right'} //编辑，删除操作栏，固定右侧
            ]]
        });

        //新增弹窗
        <@table_add_plugin module="classify"/>
        //批量删除
        <@table_batch_delete_plugin module="classify"/>
        //表格搜索
        <@table_search_plugin/>
        //表格行操作按钮
        <@table_operator_plugin module="classify"/>
        //表格列排序
        <@table_sort_plugin/>
        //表格重载
        <@table_reload_plugin/>

    });
</script>
</@footer>
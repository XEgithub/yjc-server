<#assign ctx=request.getContextPath()>
<#--公共顶部-->
<#macro head title="管理系统" keywords="" description="">
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta name="robots" content="noarchive">
    <title>${title}</title>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <link rel="shortcut icon" href="http://lbs.amap.com/dev/web/public/images/favicon.ico?t=20160513"/>
    <link rel="stylesheet" href="/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="/layuiadmin/style/admin.css" media="all">
    <link rel="stylesheet" href="/css/base.css" media="all">
    <#nested>
</head>
<body>
</#macro>

<#--公共顶部-->
<#macro head_dtree title="管理系统" keywords="" description="">
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta name="robots" content="noarchive">
    <title>${title}</title>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <link rel="shortcut icon" href="http://lbs.amap.com/dev/web/public/images/favicon.ico?t=20160513"/>
    <link rel="stylesheet" href="/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="/layuiadmin/style/admin.css" media="all">
    <link rel="stylesheet" href="/layuiadmin/layui_ext/dtree/dtree.css">
    <link rel="stylesheet" href="/layuiadmin/layui_ext/dtree/font/iconfont.css">
    <link rel="stylesheet" href="/css/base.css" media="all">
    <#nested>
</head>
<body>
</#macro>

<#--新增修改公共顶部-->
<#macro head_add_edit title="管理系统" keywords="" description="">
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta name="robots" content="noarchive">
    <title>${title}</title>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <link rel="shortcut icon" href="http://lbs.amap.com/dev/web/public/images/favicon.ico?t=20160513"/>
    <link rel="stylesheet" href="/layuiadmin/layui/css/layui.css" media="all">
    <#nested>
</head>
<body>
</#macro>

<#-- list页面内容体 -->
<#macro body>
    <div class="layui-fluid">
        <div class="layui-card">
            <#nested >
        </div>
    </div>
</#macro>

<#-- 树形结构容器 -->
<#macro body_lr>
    <div class="layui-fluid">
        <div class="layui-row layui-col-space10">
            <#nested />
        </div>
    </div>
</#macro>

<#-- 树形页面树结构容器 -->
<#macro body_lt_tree class="">
    <div class="layui-col-xs6 layui-col-md3">
        <!-- 填充内容 -->
        <div class="layui-card">
            <#nested />
        </div>
    </div>
</#macro>

<#-- 树形页面list容器 -->
<#macro body_lt_list class="">
    <div class="layui-col-xs7 layui-col-md9">
        <div class="layui-card">
            <#nested />
        </div>
    </div>
</#macro>

<#-- 树形页面树内容体 -->
<#macro card_header title="树" href="" show_add_btn="false">
    <div class="layui-card-header">
        <div class="layui-row">
            <div class="layui-col-md9">
                ${title}
            </div>
            <div class="layui-col-md3">
                <#if show_add_btn == 'true'>
                    <button class="layui-btn layui-btn-xs layui-btn-primary btn_add_edit" href="${href}">
                        <i class="layui-icon">&#xe654;</i> 新增
                    </button>
                </#if>
            </div>
        </div>
    </div>
</#macro>

<#-- 空容器 -->
<#macro body_empty>
    <#nested >
</#macro>

<#--公共底部-->
<#macro footer copyright="true">
<script src="/layuiadmin/layui/layui.js"></script>
<script>
    function el(id){
        return document.getElementById(id);
    }
    var treeContainer = el('treeContainer');
    if (treeContainer) {
        treeContainer.style.height = (window.innerHeight - 150) + 'px';
    }
</script>
    <#nested>
</body>
</html>
</#macro>

<#--面包屑-->
<#macro nav>
    <div class="layui-row">
        <span class="layui-breadcrumb">
          <#nested >
        </span>
    </div>
</#macro>

<#macro input inline="" label="名称" type="text" name="name" value="" placeholder="" readonly="" id="" assist="" require="true">
    <#if inline=="true">
    <div class="layui-inline">
        <div class="layui-form-item">
            <label class="layui-form-label">${label}</label>
            <div class="layui-input-inline">
                <input type="${type}" name="${name}" value="${value}"
                       <#if require=="true">required lay-verify="required"</#if>
                       placeholder="${placeholder}" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <#else>
    <div class="layui-form-item">
        <label class="layui-form-label">${label}</label>
        <#if assist!="">
            <div class="layui-input-inline">
                <input type="${type}" name="${name}" value="${value}"
                       <#if require=="true">required lay-verify="required"</#if>
                       placeholder="${placeholder}" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux">${assist}</div>
        <#else>
            <div class="layui-input-block">
                <input type="${type}" name="${name}" value="${value}"
                       <#if require=="true">required lay-verify="required"</#if>
                       <#if readonly != ""> readonly="readonly" </#if>
                       <#if id != ""> id="${id}" </#if>
                       placeholder="${placeholder}" autocomplete="off" class="layui-input">
            </div>
        </#if>
    </div>
    </#if>
</#macro>

<#macro select id="select" inline="" label="选择框" name="name" filter="select">
    <#if inline=="true">
    <div class="layui-inline">
        <div class="layui-form-item">
            <label class="layui-form-label">${label}</label>
            <div class="layui-input-block">
                <select name="${name}" lay-verify="required" lay-filter="${filter}" id="${id}">
                    <#--<option value=""></option>-->
                    <#nested>
                </select>
            </div>
        </div>
    </div>
    <#else>
    <div class="layui-form-item">
        <label class="layui-form-label">${label}</label>
        <div class="layui-input-block">
            <select name="${name}" lay-verify="required" lay-filter="${filter}" id="${id}">
                <option value=""></option>
                <#nested>
            </select>
        </div>
    </div>
    </#if>
</#macro>

<#macro option value="" text="文本" selected="">
    <option value="${value}" <#if selected??>selected="selected"</#if>>${text}</option>
</#macro>

<#macro checkbox label="选择框" name="name">
    <div class="layui-form-item">
        <label class="layui-form-label">${label}</label>
        <div class="layui-input-block">
            <#nested>
        </div>
    </div>
</#macro>

<#macro checkbox_item title="文本" name="name" value="" checked="">
    <input type="checkbox" name="${name}" title="${title}" value="${value}" ${checked}>
</#macro>

<#macro switch label="开关" name="switch" checked="">
    <div class="layui-form-item">
        <label class="layui-form-label">${label}</label>
        <div class="layui-input-block">
            <input type="checkbox" name="${name}" lay-skin="switch" ${checked}>
        </div>
    </div>
</#macro>

<#macro radio label="单选框">
    <div class="layui-form-item">
        <label class="layui-form-label">${label}</label>
        <div class="layui-input-block">
            <#nested>
        </div>
    </div>
</#macro>

<#macro radio_item name="sex" value="男" checked="">
    <input type="radio" name="sex" value="女" title="女" <#if checked??>checked</#if>>
</#macro>

<#macro textarea label="文本域" name="desc" value="" placeholder="请输入内容" id="demo" readonly="">
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">${label}</label>
        <div class="layui-input-block">
            <textarea <#if id??>id="${id}" </#if> name="${name}" placeholder="${placeholder}"
                ${readonly} class="layui-textarea">${value}</textarea>
        </div>
    </div>
</#macro>

<#macro ueditor label="文本域" value="" name="desc" placeholder="请输入内容" id="editor">
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">${label}</label>
        <div class="layui-input-block">
           <script id="${id}" name="${name}" type="text/plain" style="width:100;height:500px;">${value}</script>
        </div>
    </div>
</#macro>

<#macro ueditor_lib>
    <script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="/ueditor/lang/zh-cn/zh-cn.js"></script>
</#macro>

<#macro ueditor_init id="editor">
    <script type="text/javascript">
        var ue = UE.getEditor('${id}');
    </script>
</#macro>

<#macro upload label="图片" name="image" icon="" value="" text="上传图片" id="demo">
    <div class="layui-form-item">
        <label class="layui-form-label">${label}</label>
        <div class="layui-input-block">
            <input type="hidden" name="${name}" value="${value}" id="f_${id}" required lay-verify="required">
            <button class="layui-btn" type="button" <#if id??>id="${id}"</#if>>
                <i class="layui-icon">${icon}</i> ${text}
            </button>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label"></label>
        <div class="layui-input-block" id="preview" style="max-height: 200px">
        </div>
    </div>
</#macro>

<#macro button id="demo" type="button" text="确定" href="" icon="&#xe615;" right="false">
    <div class="layui-inline <#if right="true">fr<#else>mr-10</#if>">
        <div class="layui-form-item">
            <#if href != "">
                <a class="layui-btn" href="${href}">
                    <i class="layui-icon">${icon}</i> ${text}
                </a>
            <#else>
                <button class="layui-btn" type="${type}" <#if id??>id="${id}"</#if>>
                    <i class="layui-icon">${icon}</i> ${text}
                </button>
            </#if>
        </div>
    </div>
</#macro>

<#macro button_search text="搜索" href="" right="false">
    <div class="layui-inline <#if right="true">fr<#else>mr-10</#if>">
        <div class="layui-form-item">
            <button class="layui-btn layui-btn-sm" type="button" lay-submit lay-filter="search">
                <i class="layui-icon">&#xe615;</i> ${text}
            </button>
        </div>
    </div>
</#macro>

<#macro button_reset right="false">
    <div class="layui-inline <#if right="true">fr<#else>mr-10</#if>">
        <div class="layui-form-item">
            <button class="layui-btn layui-btn-sm layui-btn-normal" type="reset" lay-filter="reset">
                <i class="layui-icon">&#xe669;</i> 重置
            </button>
        </div>
    </div>
</#macro>

<#macro batch_delete id="batchDelete" text="批量删除" href="" icon="&#xe640;" right="false">
    <div class="layui-inline <#if right="true">fr<#else>mr-10</#if>">
        <div class="layui-form-item">
            <button class="layui-btn layui-btn-sm layui-btn-danger" type="button" <#if id??>id="${id}"</#if>>
                <i class="layui-icon">${icon}</i> ${text}
            </button>
        </div>
    </div>
</#macro>

<#macro button_add text="确定" right="false">
    <div class="layui-inline <#if right="true">fr<#else>mr-10</#if>">
        <div class="layui-form-item">
            <button class="layui-btn layui-btn-sm btn_add" type="button">
                <i class="layui-icon">&#xe654;</i> ${text}
            </button>
        </div>
    </div>
</#macro>

<#macro button_edit href="" filter="">
    <button class="layui-btn layui-btn-sm btn_add_edit" href="${href}" lay-filter="${filter}">
        <i class="layui-icon">&#xe642;</i> 编辑
    </button>
</#macro>

<#macro sync_table_button_edit href="" event="">
    <button class="layui-btn layui-btn-sm" href="${href}" lay-event="${event}">
        <i class="layui-icon">&#xe642;</i> 编辑
    </button>
</#macro>

<#macro button_operator href="" class="" text="操作" icon="&#xe674;" disabled="">
    <button class="layui-btn layui-btn-sm ${class} btn_add_edit" href="${href}" <#if disabled != ''>disabled</#if>>
        <i class="layui-icon">${icon}</i> ${text}
    </button>
</#macro>

<#macro button_view href="" text="查看" icon="&#xe60b;">
    <button class="layui-btn layui-btn-sm view_detail" href="${href}">
        <i class="layui-icon">${icon}</i> ${text}
    </button>
</#macro>

<#macro button_del href="" filter="">
    <button class="layui-btn layui-btn-sm layui-btn-danger btn_del" data-href="${href}" type="button" lay-filter="${filter}">
        <i class="layui-icon">&#xe640;</i> 删除
    </button>
</#macro>

<#macro sync_table_button_del href="" event="">
    <button class="layui-btn layui-btn-sm layui-btn-danger" data-href="${href}" type="button" lay-event="${event}">
        <i class="layui-icon">&#xe640;</i> 删除
    </button>
</#macro>

<#macro search_box action="" method="post">
    <form class="layui-form layui-card-header layuiadmin-card-header-auto search-box" method="${method}" action="${action}">
        <div class="layui-form-item">
            <#nested >
        </div>
    </form>
</#macro>

<#macro input_search label="名称" id="" type="text" name="name" value="" placeholder="请输入" readonly="">
    <div class="layui-inline">
        <div class="layui-form-item">
            <label class="layui-form-label">${label}</label>
            <div class="layui-input-inline">
                <input type="${type}" name="${name}" value="${value}"
                       <#if id != ''> id="${id}" </#if>
                       <#if readonly != ''> readonly="${readonly}" </#if>
                       placeholder="${placeholder}" class="layui-input search-input">
            </div>
        </div>
    </div>
</#macro>

<#macro tree id="tree" name="树">
    <fieldset class="layui-elem-field">
        <legend>${name}</legend>
        <div class="layui-field-box">
            <ul id="${id}"></ul>
        </div>
    </fieldset>
</#macro>

<#macro tree_sample id="tree">
    <div class="layui-card-body" id="treeContainer" style="overflow-y: scroll;">
        <div class="eleTree" id="${id}" lay-filter="lazytree"></div>
    </div>
</#macro>

<#macro tree_dtree id="tree">
    <div class="layui-card-body" id="treeContainer" style="overflow-y: scroll;">
        <ul id="${id}" class="dtree" data-id="0"></ul>
    </div>
</#macro>

<#macro fieldset name="子节点">
    <fieldset class="layui-elem-field">
        <legend>${name}</legend>
        <div class="layui-field-box">
            <#nested >
        </div>
    </fieldset>
</#macro>

<#--表格-->
<#macro table id="myTable" filter="myTable">
    <table id="${id}" lay-filter="${filter}"></table>
</#macro>

<#--表格操作条-->
<#macro table_operator id="operator">
    <script type="text/html" id="operator">
        <a class="layui-btn layui-btn-normal layui-btn-sm" lay-event="view">查看</a>
        <a class="layui-btn layui-btn-sm" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="del">删除</a>
    </script>
</#macro>

<#--渲染表格数据-->
<#macro table_render module="">
    //渲染表格
    table.render({
        elem: '#myTable',
        id: 'myTable',
        url: '/admin/${module}/list',
        method: 'post',
        contentType: 'application/json',
        page: true,
        request: {
            pageName: 'pageNo',
            limitName: 'pageSize'
        },
        cols: [[ //表头
            <#nested />
        ]]
    });
</#macro>

<#--表格添加弹窗-->
<#macro table_add_plugin module="替换对应模块名" area="['70%', '90%']" full="false">
    //新增
    $('.btn_add').click(function () {
        var index = layer.open({
            type: 2,
            title: '新增',
            maxmin: true,
            area: ${area},
            content: '/admin/${module}/v_add',
            btn: ['确定', '取消'],
            yes: function(index, layero){
                var body = layer.getChildFrame('body', index);
                $(body).find('[lay-submit]').click();
            }
        });
        <#if full == 'true'>
        layer.full(index);
        </#if>
    });
</#macro>

<#--批量删除插件-->
<#macro table_batch_delete_plugin module="替换对应模块名">
    //批量删除
    $('#batchDelete').click(function () {
        var checkStatus = table.checkStatus('myTable');
        var data = checkStatus.data;
        var ids = [];
        for (var i = 0; i < data.length; i++) {
            ids.push(data[i].id);
        }
        if (ids.length === 0) {
            layer.msg('请选择要删除的数据！');
            return;
        }
        layer.confirm('确定要删除吗？', {
            btn: ['确定','取消'] //按钮
        }, function(){
            $.ajax({
                type: "POST",
                url: "/admin/${module}/batch_delete",
                contentType: "application/json",
                data: JSON.stringify(ids),
                success: function (res) {
                    reloadTable();
                    layer.msg("删除成功！");
                }
            });
        });
    });
</#macro>

<#--搜索表单-->
<#macro table_search_plugin>
    //搜索
    form.on('submit(search)', function(data){
        reloadTable(data.field);
        return false;
    });
</#macro>

<#--表格操作栏插件-->
<#macro table_operator_plugin module="替换对应模块名" area="['70%', '90%']" full="false" tree="false">
    //监听行操作事件
    table.on('tool(myTable)', function(obj){
        var data = obj.data;
        //console.log(obj)
        if(obj.event === 'del'){
            layer.confirm('确定要删除吗？', function(index){
                $.get('/admin/${module}/delete/' + data.id, function (res) {
                    if (res.code === 0) {
                        reloadTable();
                        <#if tree == 'true'>
                            dtree.reload('myTree');
                        </#if>
                        layer.close(index);
                    }
                });
            });
        } else if(obj.event === 'edit'){
            var index1 = layer.open({
                type: 2,
                title: '编辑',
                maxmin: true,
                area: ${area},
                content: '/admin/${module}/v_update/' + data.id,
                btn: ['确定', '取消'],
                yes: function(index, layero){
                    var body = layer.getChildFrame('body', index);
                    $(body).find('[lay-submit]').click();
                }
            });
            <#if full == 'true'>
                layer.full(index1);
            </#if>
        } else if (obj.event === 'view'){
            var index2 = layer.open({
                type: 2,
                title: '详情',
                maxmin: true,
                area: ${area},
                content: '/admin/${module}/v_detail/' + data.id,
                btn: ['关闭']
            });
            <#if full == 'true'>
                layer.full(index2);
            </#if>
        }
    });
</#macro>

<#--表格排序-->
<#macro table_sort_plugin>
    //监听排序事件
    table.on('sort(myTable)', function(obj){
        table.reload('myTable', {
            initSort: obj,
            method: 'post',
            contentType: 'application/json',
            where: {
                field: obj.field,
                order: obj.type
            }
        });
    });
</#macro>

<#--表格重载-->
<#macro table_reload_plugin>
    var reloadTable = function (params) {
        table.reload('myTable', {
            method: 'post',
            contentType: 'application/json',
            where: params
        });
    }
</#macro>

<#macro table_asyn id="table">
    <div class="layui-card-body">
        <table id="${id}" lay-filter="${id}"></table>
    </div>
</#macro>

<#-- 图片预览 -->
<#macro preview_photo>
    $('.preview').click(function () {
        layer.open({
            type: 1,
            title: false,
            closeBtn: 0,
            area: '600px',
            skin: 'layui-layer-nobg', //没有背景色
            shadeClose: true,
            content: '<img width="100%" src="'+$(this).attr("src")+'">'
        });
    });
</#macro>

<#macro form title="默认标题" action="" id="formDemo" padding="">
    <form class="layui-form layui-form-pane" method="post" action="${action}" <#if padding != "">style="padding:${padding}"</#if>>
        <#nested>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="${id}">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</#macro>

<#macro form_padding title="默认标题" action="" id="addForm" padding="20px 30px 0 0;">
    <form class="layui-form" method="post" action="${action}" id="${id}" style="padding:${padding}">
        <#nested>
    </form>
</#macro>

<#macro simpleform title="默认标题" action="" id="formDemo">
    <form class="layui-form layui-form-pane" method="post" action="${action}">
        <#nested>
    </form>
</#macro>
<#include "../include/public.ftl"/>

<@head_add_edit title="轮播图-新增"/>

    <@body_empty>
        <@form_padding title="新增轮播图">
            <@input label="项目类型(0表示单项目 1表示组合项目)" name="projectType" placeholder="请输入项目类型(0表示单项目 1表示组合项目)" require="false"/>
            <@input label="logo" name="logo" placeholder="请输入logo" require="false"/>
            <@input label="背景" name="banner" placeholder="请输入背景" require="false"/>
            <@input label="列表图片" name="image" placeholder="请输入列表图片" require="false"/>
            <@input label="图片1" name="image1" placeholder="请输入图片1" require="false"/>
            <@input label="图片2" name="image2" placeholder="请输入图片2" require="false"/>
            <@input label="图片3" name="image3" placeholder="请输入图片3" require="false"/>
            <@input label="名称" name="name" placeholder="请输入名称" require="false"/>
            <@input label="医院uuid" name="hospital" placeholder="请输入医院uuid" require="false"/>
            <@input label="原价" name="oldPrice" placeholder="请输入原价" require="false"/>
            <@input label="现价" name="nowPrice" placeholder="请输入现价" require="false"/>
            <@input label="周期" name="period" placeholder="请输入周期" require="false"/>
            <@input label="注意事项" name="notes" placeholder="请输入注意事项" require="false"/>
            <@input label="首页分类uuid" name="classify1" placeholder="请输入首页分类uuid" require="false"/>
            <@input label="导航分类uuid" name="classify2" placeholder="请输入导航分类uuid" require="false"/>
            <@input label="关键词" name="keyword" placeholder="请输入关键词" require="false"/>
            <@input label="备注" name="remark" placeholder="请输入备注" require="false"/>
            <@input label="备注1" name="remark1" placeholder="请输入备注1" require="false"/>
            <@input label="操作人" name="operator" placeholder="请输入操作人" require="false"/>
            <@input label="描述" name="description" placeholder="请输入描述" require="false"/>
            <@input label="详情" name="detail" placeholder="请输入详情" require="false"/>
            <@input label="状态（下线=0，审核=1，审核失败=2，上线=3）" name="status" placeholder="请输入状态（下线=0，审核=1，审核失败=2，上线=3）" require="false"/>
            <@input label="预留" name="reserve" placeholder="请输入预留" require="false"/>
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
        form.on('submit(*)', function(data){
            $.ajax({
                type: "POST",
                url: "/admin/projectgroup/add",
                contentType: "application/json",
                data: JSON.stringify(data.field),
                success: function (res) {
                    if (res.code === 0) {
                        parent.layui.table.reload('myTable');
                        layer.msg("添加成功！");
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
<#include "../include/public.ftl"/>

<@head title="登录">
<link rel="stylesheet" href="/layuiadmin/style/login.css">
</@head>

<div class="layadmin-user-login layadmin-user-display-show" id="LAY-user-login" style="display: none;">

    <div class="layadmin-user-login-main">
        <div class="layadmin-user-login-box layadmin-user-login-header">
            <h2>后台管理系统</h2>
            <p>陕西鑫珠商务咨询有限公司</p>
        </div>
        <form class="layadmin-user-login-box layadmin-user-login-body layui-form" action="/admin/login" method="post">
            <div class="layui-form-item">
                <label class="layadmin-user-login-icon layui-icon layui-icon-username" for="LAY-user-login-username"></label>
                <input type="text" name="username" required lay-verify="required" placeholder="用户名" class="layui-input" autocomplete="false">
            </div>
            <div class="layui-form-item">
                <label class="layadmin-user-login-icon layui-icon layui-icon-password" for="LAY-user-login-password"></label>
                <input type="password" name="password" required lay-verify="required" placeholder="密码" class="layui-input" autocomplete="false">
            </div>
            <div class="layui-form-item">
                <div class="layui-row">
                    <div class="layui-col-xs7">&nbsp;
                        <#if message??>
                            <span class="layui-badge">${message!''}</span>
                        </#if>
                    </div>
                    <div class="layui-col-xs5">
                        <a href="/admin/forget" class="layadmin-user-jump-change layadmin-link" style="margin-top: 7px;">忘记密码？</a>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <button class="layui-btn layui-btn-fluid" type="submit">登 入</button>
            </div>
            <div class="layui-trans layui-form-item layadmin-user-login-other">

                <a href="/admin/reg" class="layadmin-user-jump-change layadmin-link">注册帐号</a>
            </div>
        </form>
    </div>

    <div class="layui-trans layadmin-user-login-footer">

        <#--<p>© 2018 <a href="#" target="_blank"></a></p>-->
    </div>

</div>
<@footer>
<script>
    layui.use(['element','form'], function(){
        var form = layui.form;
        var $ = layui.jquery;

    });
</script>
</@footer>
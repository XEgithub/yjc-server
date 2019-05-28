<#include "../include/public.ftl"/>

<@head title="注册">
<link rel="stylesheet" href="/layuiadmin/style/login.css">
</@head>

<div class="layadmin-user-login layadmin-user-display-show">
    <div class="layadmin-user-login-main">
        <div class="layadmin-user-login-box layadmin-user-login-header">
            <h2>后台管理系统</h2>
            <p>陕西鑫珠商务咨询有限公司</p>
        </div>
        <form id="form" class="layadmin-user-login-box layadmin-user-login-body layui-form" action="/admin/reg" method="post">
            <div class="layui-form-item">
                <label class="layadmin-user-login-icon layui-icon layui-icon-cellphone"></label>
                <input type="text" name="phone" required lay-verify="phone" placeholder="手机" class="layui-input" id="phone">
            </div>
            <div class="layui-form-item">
                <div class="layui-row">
                    <div class="layui-col-xs7">
                        <label class="layadmin-user-login-icon layui-icon layui-icon-vercode" ></label>
                        <input type="text" name="verifyCode" required lay-verify="required" placeholder="验证码" class="layui-input">
                    </div>
                    <div class="layui-col-xs5">
                        <div style="margin-left: 10px;">
                            <button type="button" class="layui-btn layui-btn-primary layui-btn-fluid" id="sendCode">获取验证码</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layadmin-user-login-icon layui-icon layui-icon-password"></label>
                <input type="password" name="password" required lay-verify="required" placeholder="密码" class="layui-input" id="password">
            </div>
            <div class="layui-form-item">
                <label class="layadmin-user-login-icon layui-icon layui-icon-password" for="LAY-user-login-repass"></label>
                <input type="password" name="repassword" required lay-verify="required" placeholder="确认密码" class="layui-input" id="repassword">
            </div>
            <div class="layui-form-item">
                <label class="layadmin-user-login-icon layui-icon layui-icon-username" for="LAY-user-login-nickname"></label>
                <input type="text" name="realname" required lay-verify="required" placeholder="姓名" class="layui-input">
            </div>
            <div class="layui-form-item">
                <label class="layadmin-user-login-icon layui-icon layui-icon-username" for="LAY-user-login-nickname"></label>
                <input type="text" name="invitCode" required lay-verify="required" placeholder="邀请码" class="layui-input">
            </div>
            <div class="layui-form-item" style="min-height: 10px">
                <#--<input type="checkbox" name="agreement" lay-skin="primary" title="同意用户协议" checked>-->
            </div>
            <div class="layui-form-item">
                <button class="layui-btn layui-btn-fluid" type="button" id="regBtn">注 册</button>
            </div>
            <div class="layui-trans layui-form-item layadmin-user-login-other">
                <#if message??>
                    <span class="layui-badge">${message!''}</span>
                </#if>
                <a href="/admin/login" class="layadmin-user-jump-change layadmin-link layui-hide-xs">用已有帐号登入</a>
                <a href="/admin/login" class="layadmin-user-jump-change layadmin-link layui-hide-sm layui-show-xs-inline-block">登入</a>
            </div>
        </form>
    </div>

</div>
<@footer>
<script>
    layui.use(['element','form'], function(){
        var form = layui.form;
        var $ = layui.jquery;
        var allow = true;
        var countdown = 60;

        //发送验证码
        $('#sendCode').click(function () {
            if (!allow) {
                return;
            }
            var phone = $('#phone').val();
            if (!phone){
                layer.msg("请输入手机号码");
                return;
            }
            if(!isPoneAvailable(phone)){
                layer.msg("手机号码格式不正确");
                return;
            }
            var self = $(this);
            self.attr('disabled',"true");

            var url = "/admin/sendCode";
            var param = {
                phone: phone
            };
            $.post(url, param, function (res) {
                if (res.code === 0) {
                    allow = false;
                    layer.msg("发送验证码成功");
                    settime(self, countdown);
                } else {
                    layer.msg("发送验证码失败");
                }
            })
        });

        //注册
        $('#regBtn').click(function () {
            var phone = $('#phone').val();
            if (!phone){
                layer.msg("请输入手机号码");
                return;
            };
            if(!isPoneAvailable(phone)){
                layer.msg("手机号码格式不正确");
                return;
            };
            var password = $('#password').val();
            var repassword = $('#repassword').val();
            if (password && password.trim().length <6) {
                layer.msg("密码最少6位");
                return;
            }
            if (password !== repassword) {
                layer.msg("两次输入密码不一致");
                return;
            }
            var regUrl = "/admin/reg";
            var regParam = $('#form').serialize();
            $.post(regUrl, regParam, function (res) {
                if (res.code === 0) {
                    layer.msg("注册成功");
                    setTimeout(function () {
                        location.href = "/admin/login";
                    }, 1000)
                } else {
                    layer.msg(res.msg)
                }
            });
        });

        //倒计时
        function settime(self, val) {
            if (countdown === 0) {
                self.attr('disabled',"false");
                self.html("获取验证码");
                countdown = 60;
                allow = true;
            } else {
                self.attr('disabled',"true");
                self.html("重新发送(" + countdown + ")");
                countdown--;
                setTimeout(function() {
                    settime(self, countdown)
                },1000)
            }
        }

        //验证手机号
        function isPoneAvailable(str) {
            var myreg=/^[1][3,4,5,7,8][0-9]{9}$/;
            if (!myreg.test(str)) {
                return false;
            } else {
                return true;
            }
        }
    });
</script>
</@footer>
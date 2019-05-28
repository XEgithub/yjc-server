package com.dk.interceptor;

import com.dk.config.Constant;
import com.dk.shiro.JWToken;
import jodd.util.StringUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * 功能描述:
 *
 * @auther: my
 * @Date: 2018/10/23
 */
public class WxAuthInterceptor implements HandlerInterceptor {

    private String appId = this.getAppId();
    private String host = this.getHost();

    private String wxOauthUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
            "appid=APP_ID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_userinfo&state=#wechat_redirect";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String requestURI = request.getRequestURI();
//        if (requestURI.contains("rest") || requestURI.contains("admin") || requestURI.contains("wechat")) {
//            return true;
//        }
        // 只有返回true才会继续向下执行，返回false取消当前请求
        String bussinessUrl = host + "/wechat/openauth?redirect_uri="+ requestURI;
        String redirectUrl = wxOauthUrl.replace("APP_ID", appId);
        redirectUrl = redirectUrl.replace("REDIRECT_URI", bussinessUrl);
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(Constant.WX_TOKEN)) {
                    String token = cookie.getValue();
                    String openId = JWToken.get(token, Constant.WX_TOKEN);
                    if (StringUtil.isBlank(openId)) {
                        response.sendRedirect(redirectUrl);
                        return false;
                    } else {
                        return true;
                    }
                }
            }
        }
        response.sendRedirect(redirectUrl);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }


    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}

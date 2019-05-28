package com.dk.utils;

import com.dk.config.Constant;
import com.dk.shiro.JWToken;
import jodd.util.StringUtil;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;

/**
 *
 * 功能描述:
 *
 * @auther: my
 * @Date: 2018/10/23
 */
@Component
public class WxUtil {

    private static final Logger log = LoggerFactory.getLogger(WxUtil.class);

    @Autowired
    private WxMpService wxService;

    public WxMpUser getWxUser(String code){
        if (StringUtil.isBlank(code)){
            return null;
        }
        try {
            WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxService.oauth2getAccessToken(code);
            return wxService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);
        } catch (WxErrorException e) {
            log.error("获取openid失败");
            e.printStackTrace();
        }
        return null;
    }

    public static Cookie getCookie(){
        String token = JWToken.createToken(Constant.WX_TOKEN, "oJxTqsrmuVqETmEudpa1CJz8hsiM");
        Cookie cookie = new Cookie(Constant.WX_TOKEN, token);
        //有效期24小时
        cookie.setMaxAge(24 * 60 * 60);
        //设置作用域
        cookie.setPath("/");
        return cookie;
    }


}

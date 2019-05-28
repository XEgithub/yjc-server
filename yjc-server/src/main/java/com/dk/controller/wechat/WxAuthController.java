package com.dk.controller.wechat;

import com.dk.config.Constant;
import com.dk.data.entity.WxUser;
import com.dk.service.WxUserService;
import com.dk.shiro.JWToken;
import com.dk.utils.WxUtil;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 *
 * 功能描述:
 *
 * @auther: my
 * @Date: 2018/10/23
 */
@CrossOrigin("*")
@Controller
@RequestMapping("wechat")
public class WxAuthController {

    @Autowired
    private WxUtil wxUtil;

    @Autowired
    private WxUserService wxUserService;

    @GetMapping("openauth")
    public String openauth(String code, String redirect_uri, HttpServletResponse response){
        WxMpUser wxMpUser = wxUtil.getWxUser(code);
        if (wxMpUser != null) {
            WxUser wxUser = wxUserService.findByOpenid(wxMpUser);
            String token = JWToken.createToken(Constant.WX_TOKEN, wxUser.getUuid());
            Long expires = LocalDateTime.now().plusMinutes(61).toInstant(ZoneOffset.of("+8")).toEpochMilli();
            Cookie cookie = new Cookie(Constant.WX_TOKEN, token);
            //有效期24小时
            cookie.setMaxAge(24 * 60 * 60);
            //设置作用域
            cookie.setPath("/");
            response.addCookie(cookie);

            Cookie cookie1 = new Cookie(Constant.EXPIRES, expires.toString());
            cookie1.setMaxAge(24 * 60 * 60);
            cookie1.setPath("/");
            response.addCookie(cookie1);
        }
        return "redirect:" + redirect_uri;
    }

}

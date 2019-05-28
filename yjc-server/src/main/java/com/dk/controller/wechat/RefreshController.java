package com.dk.controller.wechat;

import com.dk.config.Constant;
import com.dk.data.entity.WxUser;
import com.dk.response.HttpStateCode;
import com.dk.response.WrappedResponse;
import com.dk.service.WxUserService;
import com.dk.shiro.JWToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest")
public class RefreshController {

    @Autowired
    private WxUserService wxUserService;

    @RequestMapping("refreshToken")
    public WrappedResponse refresh(@CookieValue(name = Constant.WX_TOKEN, required = false) String token){
        WrappedResponse result = new WrappedResponse();
        if (StringUtils.isBlank(token)) {
            result.setCode(HttpStateCode.INVALID_TOKEN.getValue());
            result.setMsg(HttpStateCode.INVALID_TOKEN.getReasonPhrase());
            return result;
        }
        String uuid = JWToken.getUuid(token);
        WxUser wxUser = wxUserService.findByUuid(uuid);
        if (wxUser == null) {
            result.setCode(HttpStateCode.NO_CONTENT.getValue());
            result.setMsg(HttpStateCode.NO_CONTENT.getReasonPhrase());
            return result;
        }
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        result.setSuccessObject(JWToken.createToken(Constant.WX_TOKEN, wxUser.getUuid()));
        return result;
    }

}

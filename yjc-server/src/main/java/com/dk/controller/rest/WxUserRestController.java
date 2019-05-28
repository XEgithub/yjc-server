package com.dk.controller.rest;

import com.dk.config.Constant;
import com.dk.data.entity.Nurse;
import com.dk.data.entity.WxUserExtend;
import com.dk.response.HttpStateCode;
import com.dk.response.WrappedResponse;
import com.dk.service.NurseService;
import com.dk.service.WxUserExtendService;
import com.dk.service.WxUserService;
import com.dk.shiro.JWToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName WxUserRestController
 * @Description
 * @Author zyf
 * @Date 2019/1/10 14:21
 **/
@RestController
    @RequestMapping("rest/user")
public class WxUserRestController {

    @Autowired
    WxUserService wxUserService;

    @Autowired
    WxUserExtendService wxUserExtendService;

    @Autowired
    NurseService nurseService;

    /*
     * @Description 获取身份
     * @Date 2019/1/10
     * @Param
     * @return
     * @Author zhy
     **/
    @GetMapping("identity")
    public WrappedResponse identity(@CookieValue(name = Constant.WX_TOKEN, required = false) String token) {
        WrappedResponse result = new WrappedResponse();
        if (StringUtils.isBlank(token)) {
            result.setCode(HttpStateCode.INVALID_TOKEN.getValue());
            result.setMsg(HttpStateCode.INVALID_TOKEN.getReasonPhrase());
            return result;
        }
        String user = JWToken.getUuid(token);
        WxUserExtend wxUserExtend = wxUserExtendService.findByUser(user);
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("identity", wxUserExtend.getIdentity());
        result.setSuccessObject(resultMap);
        return result;
    }

    /*
     * @Description 切换身份
     * @Date 2019/1/10
     * @Param
     * @return
     * @Author zhy
     **/
    @GetMapping("switchover")
    public WrappedResponse switchover(String identity,
                                      @CookieValue(name = Constant.WX_TOKEN, required = false) String token) {
        WrappedResponse result = new WrappedResponse();
        if (!"1".equals(identity) && !"2".equals(identity)) {
            result.setCode(HttpStateCode.PARAMETERS_NULL.getValue());
            result.setMsg(HttpStateCode.PARAMETERS_NULL.getReasonPhrase());
            return result;
        }
        if (StringUtils.isBlank(token)) {
            result.setCode(HttpStateCode.INVALID_TOKEN.getValue());
            result.setMsg(HttpStateCode.INVALID_TOKEN.getReasonPhrase());
            return result;
        }
        String user = JWToken.getUuid(token);
        WxUserExtend bean = wxUserExtendService.findByUser(user);
        bean.setIdentity(identity);
        WxUserExtend wxUserExtend = wxUserExtendService.update(bean);
        if (wxUserExtend == null) {
            result.setCode(HttpStateCode.UPDATE_FAILURE.getValue());
            result.setMsg(HttpStateCode.UPDATE_FAILURE.getReasonPhrase());
            return result;
        }
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("identity", wxUserExtend.getIdentity());
        result.setSuccessObject(resultMap);
        return result;
    }

    /*
     * @Description 判断是否已经验证身份
     * @Date 2019/1/14
     * @Param
     * @return
     * @Author zhy
     **/
    @GetMapping("authentication")
    public WrappedResponse authentication(@CookieValue(name = Constant.WX_TOKEN, required = false) String token) {
        WrappedResponse result = new WrappedResponse();
        if (StringUtils.isBlank(token)) {
            result.setCode(HttpStateCode.INVALID_TOKEN.getValue());
            result.setMsg(HttpStateCode.INVALID_TOKEN.getReasonPhrase());
            return result;
        }
        String user = JWToken.getUuid(token);
        Map<String, Object> resultMap = new HashMap<>();
        int personal = 0;
        Nurse nurse = nurseService.findByUser(user);
        String phone = nurse.getPhone();
        if (phone != null && !"".equals(phone)) {
            personal = 1;
        }
        resultMap.put("personal", personal);
        resultMap.put("identity", nurse.getStatus());
        resultMap.put("serviceStatus", nurse.getServiceStatus());
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        result.setSuccessObject(resultMap);
        return result;
    }


}

package com.dk.controller.rest;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dk.config.Constant;
import com.dk.data.dto.SearchInformDto;
import com.dk.data.entity.Inform;
import com.dk.response.HttpStateCode;
import com.dk.response.WrappedResponse;
import com.dk.service.InformService;
import com.dk.shiro.JWToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName InformRestController
 * @Description
 * @Author zyf
 * @Date 2018/12/11 15:16
 **/
@RestController
@RequestMapping("rest/inform")
public class InformRestController {

    @Autowired
    InformService informService;

    /*
     * @Description 消息提醒列表
     * @Date 2018/12/11
     * @Param
     * @return
     * @Author zhy
     **/
    @GetMapping("list")
    public WrappedResponse list(@CookieValue(name = Constant.WX_TOKEN, required = false) String token,
                                SearchInformDto param) {
        WrappedResponse result = new WrappedResponse();
        if (StringUtils.isBlank(token)) {
            result.setCode(HttpStateCode.INVALID_TOKEN.getValue());
            result.setMsg(HttpStateCode.INVALID_TOKEN.getReasonPhrase());
            return result;
        }
        String user = JWToken.getUuid(token);
        param.setUser(user);
        IPage<Inform> informIPage = informService.findAll(param);
        if (informIPage.getRecords().size() == 0) {
            result.setSuccessObject(new String[0]);
        } else {
            result.setSuccessObject(informIPage);
        }
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        return result;
    }

    /*
     * @Description 改变消息读取状态为已读
     * @Date 2018/12/11
     * @Param
     * @return
     * @Author zhy
     **/
    @GetMapping("read")
    public WrappedResponse read(@RequestParam(value = "uuid", required = true) String uuid) {
        WrappedResponse result = new WrappedResponse();
        if ("".equals(uuid)) {
            result.setCode(HttpStateCode.PARAMETERS_NULL.getValue());
            result.setMsg(HttpStateCode.PARAMETERS_NULL.getReasonPhrase());
            return result;
        }
        int flag = informService.readByUuid(uuid);
        if (flag != 1) {
            result.setCode(HttpStateCode.UPDATE_FAILURE.getValue());
            result.setMsg(HttpStateCode.UPDATE_FAILURE.getReasonPhrase());
            return result;
        }
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        return result;
    }

    /*
     * @Description 删除提醒消息
     * @Date 2018/12/11
     * @Param
     * @return
     * @Author zhy
     **/
    @GetMapping("delete")
    public WrappedResponse delete(@RequestParam(value = "uuid", required = true) String uuid) {
        WrappedResponse result = new WrappedResponse();
        if ("".equals(uuid)) {
            result.setCode(HttpStateCode.PARAMETERS_NULL.getValue());
            result.setMsg(HttpStateCode.PARAMETERS_NULL.getReasonPhrase());
            return result;
        }
        int flag = informService.delete(uuid);
        if (flag != 1) {
            result.setCode(HttpStateCode.DELETE_FAILURE.getValue());
            result.setMsg(HttpStateCode.DELETE_FAILURE.getReasonPhrase());
            return result;
        }
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        return result;
    }

    /*
     * @Description 消息数量
     * @Date 2018/12/13
     * @Param 用户uuid
     * @return
     * @Author zhy
     **/
    @GetMapping("total")
    public WrappedResponse total(@CookieValue(name = Constant.WX_TOKEN, required = false) String token,
                                 SearchInformDto condition) {
        WrappedResponse result = new WrappedResponse();
        if (StringUtils.isBlank(token)) {
            result.setCode(HttpStateCode.INVALID_TOKEN.getValue());
            result.setMsg(HttpStateCode.INVALID_TOKEN.getReasonPhrase());
            return result;
        }
        String user = JWToken.getUuid(token);
        condition.setUser(user);
        condition.setMarkRead("0");
        int count = informService.count(condition);
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        result.setSuccessObject(count);
        return result;
    }


}

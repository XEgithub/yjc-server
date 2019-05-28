package com.dk.wx.event;

import com.dk.response.HttpStateCode;
import com.dk.response.WrappedResponse;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("wxconfig")
public class EventController {

    @Autowired
    private CoreService coreService;


    @RequestMapping(value = "/event", method = RequestMethod.GET)
    private WrappedResponse event(HttpServletRequest request) {
        WrappedResponse result = new WrappedResponse();
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        result.setSuccessObject(coreService.processRequest(request));

        return result;
    }
}

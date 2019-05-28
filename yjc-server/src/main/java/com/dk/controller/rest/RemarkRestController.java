package com.dk.controller.rest;

import com.dk.data.entity.Remark;
import com.dk.response.HttpStateCode;
import com.dk.response.WrappedResponse;
import com.dk.service.RemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName RemarkRestController
 * @Description
 * @Author zyf
 * @Date 2018/12/6 14:14
 **/
@RestController
@RequestMapping("rest/remark")
public class RemarkRestController {

    @Autowired
    RemarkService remarkService;

    /*
     * @Description 备注列表
     * @Date 2018/12/14
     * @Param
     * @return
     * @Author zhy
     **/
    @GetMapping("list")
    public WrappedResponse list() {
        WrappedResponse result = new WrappedResponse();
        List<Remark> remarkList = remarkService.getAll();
        if (remarkList != null) {
            result.setCode(HttpStateCode.NO_CONTENT.getValue());
            result.setMsg(HttpStateCode.NO_CONTENT.getReasonPhrase());
            result.setSuccessObject(remarkList);
        }
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        result.setSuccessObject(remarkList);
        return result;
    }

}

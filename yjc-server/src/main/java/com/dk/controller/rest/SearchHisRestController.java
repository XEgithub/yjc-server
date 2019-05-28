package com.dk.controller.rest;

import com.dk.config.Constant;
import com.dk.data.dto.SearchSearchHisDto;
import com.dk.data.entity.SearchHis;
import com.dk.response.HttpStateCode;
import com.dk.response.WrappedResponse;
import com.dk.service.SearchHisService;
import com.dk.shiro.JWToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName SearchHisRestController
 * @Description
 * @Author zyf
 * @Date 2018/12/12 9:57
 **/
@RestController
@RequestMapping("rest/searchHis")
public class SearchHisRestController {

    @Autowired
    private SearchHisService searchHisService;

    /*
     * @Description 历史搜索列表
     * @Date 2018/12/12
     * @Param
     * @return
     * @Author zhy
     **/
    @GetMapping("list")
    public WrappedResponse list(@CookieValue(name = Constant.WX_TOKEN, required = false) String token) {
        WrappedResponse result = new WrappedResponse();
        SearchSearchHisDto condition = new SearchSearchHisDto();
        if (StringUtils.isBlank(token)) {
            result.setCode(HttpStateCode.INVALID_TOKEN.getValue());
            result.setMsg(HttpStateCode.INVALID_TOKEN.getReasonPhrase());
            return result;
        }
        String user = JWToken.getUuid(token);
        condition.setUser(user);
        condition.setField("time");
        condition.setOrder("desc");
        List<SearchHis> searchHisList = searchHisService.findAll(condition).getRecords();
        if (searchHisList == null) {
            result.setCode(HttpStateCode.NO_CONTENT.getValue());
            result.setMsg(HttpStateCode.NO_CONTENT.getReasonPhrase());
            return result;
        }
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        result.setSuccessObject(searchHisList);
        return result;
    }

    /*
     * @Description 清除搜索历史记录
     * @Date 2019/1/4
     * @Param
     * @return
     * @Author zhy
     **/
    @GetMapping("clear")
    public WrappedResponse clear(@CookieValue(name = Constant.WX_TOKEN, required = false) String token) {
        WrappedResponse result = new WrappedResponse();
        if (StringUtils.isBlank(token)) {
            result.setCode(HttpStateCode.INVALID_TOKEN.getValue());
            result.setMsg(HttpStateCode.INVALID_TOKEN.getReasonPhrase());
            return result;
        }
        String user = JWToken.getUuid(token);
        int flag = searchHisService.clearHis(user);
        if (flag == 0) {
            result.setCode(HttpStateCode.OPERATION_FAILURE.getValue());
            result.setMsg(HttpStateCode.OPERATION_FAILURE.getReasonPhrase());
            return result;
        }
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        return result;
    }


}

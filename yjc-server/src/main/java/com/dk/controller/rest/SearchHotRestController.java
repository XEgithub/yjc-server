package com.dk.controller.rest;

import com.dk.data.dto.SearchSearchHotDto;
import com.dk.data.entity.SearchHot;
import com.dk.response.HttpStateCode;
import com.dk.response.WrappedResponse;
import com.dk.service.SearchHotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName SearchHotRestController
 * @Description
 * @Author zyf
 * @Date 2018/12/11 17:40
 **/
@RestController
@RequestMapping("rest/searchHot")
public class SearchHotRestController {

    @Autowired
    SearchHotService searchHotService;

    /*
     * @Description 热搜列表
     * @Date 2018/12/11
     * @Param
     * @return
     * @Author zhy
     **/
    @GetMapping("list")
    public WrappedResponse list(SearchSearchHotDto param) {
        WrappedResponse result = new WrappedResponse();
        param.setPageNo(1);
        param.setPageSize(10);
        param.setField("time");
        param.setOrder("desc");
        List<SearchHot> searchHotList = searchHotService.findAll(param).getRecords();
        if (searchHotList.size() == 0) {
            result.setCode(HttpStateCode.NO_CONTENT.getValue());
            result.setMsg(HttpStateCode.NO_CONTENT.getReasonPhrase());
            return result;
        }
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        result.setSuccessObject(searchHotList);
        return result;
    }


}

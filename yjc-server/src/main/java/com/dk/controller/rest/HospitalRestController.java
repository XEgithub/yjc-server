package com.dk.controller.rest;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dk.data.dto.SearchHospitalDto;
import com.dk.data.entity.Hospital;
import com.dk.response.HttpStateCode;
import com.dk.response.WrappedResponse;
import com.dk.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HospitalRestController
 * @Description
 * @Author zyf
 * @Date 2018/12/4 11:58
 **/
@RestController
@RequestMapping(value = "rest/hospital")
public class HospitalRestController {

    @Autowired
    HospitalService hospitalService;

    /*
     * @Description 获取医院列表
     * @Date 2018/12/4
     * @Param
     * @return
     * @Author zhy
     **/
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public WrappedResponse list(SearchHospitalDto condition) {
        WrappedResponse result = new WrappedResponse();
        IPage<Hospital> hospitalList = hospitalService.findAll(condition);
        if (hospitalList.getRecords().size() == 0) {
            result.setCode(HttpStateCode.NO_CONTENT.getValue());
            result.setMsg(HttpStateCode.NO_CONTENT.getReasonPhrase());
            return result;
        }
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        result.setSuccessObject(hospitalList);
        return result;
    }

    /*
     * @Description 根据医院uuid获取医院详情
     * @Date 2018/12/4
     * @Param
     * @return
     * @Author zhy
     **/
    @RequestMapping(value = "/getByUuid", method = RequestMethod.GET)
    public WrappedResponse getByUuid(@RequestParam(value = "uuid", required =true) String uuid,
                                     @RequestParam(value = "type", required = true) String type) {
        WrappedResponse result = new WrappedResponse();
        if ("".equals(uuid)) {
            result.setCode(HttpStateCode.PARAMETERS_NULL.getValue());
            result.setMsg(HttpStateCode.PARAMETERS_NULL.getReasonPhrase());
            return result;
        }
        Hospital hospital = hospitalService.findByUuid(uuid);
        if (hospital == null) {
            result.setCode(HttpStateCode.NO_CONTENT.getValue());
            result.setMsg(HttpStateCode.NO_CONTENT.getReasonPhrase());
            return result;
        }
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        result.setSuccessObject(hospital);
        return result;
    }

}

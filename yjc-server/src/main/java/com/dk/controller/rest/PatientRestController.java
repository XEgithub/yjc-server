package com.dk.controller.rest;

import cn.sourcespro.commons.utils.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dk.config.Constant;
import com.dk.data.dto.AddPatientDto;
import com.dk.data.dto.EditPatientDto;
import com.dk.data.dto.SearchPatientDto;
import com.dk.data.entity.Patient;
import com.dk.response.HttpStateCode;
import com.dk.response.WrappedResponse;
import com.dk.service.OrderService;
import com.dk.service.PatientService;
import com.dk.shiro.JWToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName PatientRestController
 * @Description
 * @Author zyf
 * @Date 2018/12/6 9:37
 **/
@RestController
@RequestMapping("rest/patient")
public class PatientRestController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private OrderService orderService;

    /*
     * @Description 添加就医人
     * @Date 2018/12/6
     * @Param
     * @return
     * @Author zhy
     **/
    @PostMapping("add")
    public WrappedResponse add(@CookieValue(name = Constant.WX_TOKEN, required = false) String token,
                               @Validated @RequestBody AddPatientDto param, BindingResult paramResult) {
        WrappedResponse result = new WrappedResponse();
        if (paramResult.hasErrors()) {
            result.setCode(HttpStateCode.PARAMETERS_NULL.getValue());
            result.setMsg(HttpStateCode.PARAMETERS_NULL.getReasonPhrase());
            return result;
        }
        Patient bean = new Patient();
        BeanUtil.copyProperties(param, bean);
        if (StringUtils.isBlank(token)) {
            result.setCode(HttpStateCode.INVALID_TOKEN.getValue());
            result.setMsg(HttpStateCode.INVALID_TOKEN.getReasonPhrase());
            return result;
        }
        String user = JWToken.getUuid(token);
        bean.setUser(user);
        Patient patient = patientService.add(bean);
        if (patient != null) {
            result.setCode(HttpStateCode.OK.getValue());
            result.setMsg(HttpStateCode.OK.getReasonPhrase());
            result.setSuccessObject(patient);
        }
        return result;
    }

    /*
     * @Description 就医人列表
     * @Date 2018/12/10
     * @Param
     * @return
     * @Author zhy
     **/
    @GetMapping("list")
    public WrappedResponse list(@CookieValue(name = Constant.WX_TOKEN, required = false) String token,
                                SearchPatientDto condition) {
        WrappedResponse result = new WrappedResponse();
        if (StringUtils.isBlank(token)) {
            result.setCode(HttpStateCode.INVALID_TOKEN.getValue());
            result.setMsg(HttpStateCode.INVALID_TOKEN.getReasonPhrase());
            return result;
        }
        String user = JWToken.getUuid(token);
        condition.setUser(user);
        IPage<Patient> patientIPage = patientService.findAll(condition);
        if (patientIPage.getRecords().isEmpty()) {
            result.setCode(HttpStateCode.NO_CONTENT.getValue());
            result.setMsg(HttpStateCode.NO_CONTENT.getReasonPhrase());
            return result;
        }
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        result.setSuccessObject(patientIPage);
        return result;
    }

    /*
     * @Description 就医人详情
     * @Date 2018/12/10
     * @Param
     * @return
     * @Author zhy
     **/
    @GetMapping("detail")
    public WrappedResponse detail(@RequestParam(value = "uuid", required = true) String uuid) {
        WrappedResponse result = new WrappedResponse();
        if ("".equals(uuid)) {
            result.setCode(HttpStateCode.PARAMETERS_NULL.getValue());
            result.setMsg(HttpStateCode.PARAMETERS_NULL.getReasonPhrase());
            return result;
        }
        Patient patient = patientService.findByUuid(uuid);
        if (patient == null) {
            result.setCode(HttpStateCode.NO_CONTENT.getValue());
            result.setMsg(HttpStateCode.NO_CONTENT.getReasonPhrase());
            return result;
        }
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        result.setSuccessObject(patient);
        return result;
    }

    /*
     * @Description 删除就医人
     * @Date 2018/12/10
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
        int count = orderService.countByPatient(uuid);
        if (count != 0) {
            result.setCode(HttpStateCode.LOCKED.getValue());
            result.setMsg(HttpStateCode.LOCKED.getReasonPhrase());
            return result;
        }
        int flag = patientService.delete(uuid);
        if (flag != 1) {
            result.setCode(HttpStateCode.NO_CONTENT.getValue());
            result.setMsg(HttpStateCode.NO_CONTENT.getReasonPhrase());
            return result;
        }
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        return result;
    }

    /*
     * @Description 更新就医人
     * @Date 2018/12/10
     * @Param
     * @return
     * @Author zhy
     **/
    @PostMapping("update")
    public WrappedResponse update(@Validated @RequestBody EditPatientDto editPatientDto, BindingResult paramResult) {
        WrappedResponse result = new WrappedResponse();
        if (paramResult.hasErrors()) {
            result.setCode(HttpStateCode.PARAMETERS_NULL.getValue());
            result.setMsg(HttpStateCode.PARAMETERS_NULL.getReasonPhrase());
            return result;
        }
        Patient bean = new Patient();
        BeanUtil.copyProperties(editPatientDto, bean);
        Patient patient = patientService.update(bean);
        if (patient == null) {
            result.setCode(HttpStateCode.UPDATE_FAILURE.getValue());
            result.setMsg(HttpStateCode.UPDATE_FAILURE.getReasonPhrase());
            return result;
        }
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        result.setSuccessObject(patient);
        return result;
    }


}

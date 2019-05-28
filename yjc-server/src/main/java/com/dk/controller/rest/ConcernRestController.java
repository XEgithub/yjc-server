package com.dk.controller.rest;

import cn.sourcespro.commons.utils.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dk.config.Constant;
import com.dk.data.dto.AddConcernDto;
import com.dk.data.dto.SearchConcernDto;
import com.dk.data.entity.Concern;
import com.dk.data.entity.Project;
import com.dk.data.entity.ProjectGroup;
import com.dk.response.HttpStateCode;
import com.dk.response.WrappedResponse;
import com.dk.service.*;
import com.dk.shiro.JWToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @ClassName ConcernRestController
 * @Description
 * @Author zyf
 * @Date 2018/12/12 18:35
 **/
@RestController
@RequestMapping("rest/concern")
public class ConcernRestController {

    @Autowired
    ConcernService concernService;

    @Autowired
    ProjectService projectService;

    @Autowired
    ProjectGroupService projectGroupService;

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    CommentService commentService;

    @Autowired
    CouponService couponService;

    /*
     * @Description 添加/取消关注
     * @Date 2018/12/12
     * @Param
     * @return
     * @Author zhy
     **/
    @PostMapping("add")
    public WrappedResponse add(@CookieValue(name = Constant.WX_TOKEN, required = false) String token,
                               @Validated @RequestBody AddConcernDto param, BindingResult paramResult) {
        WrappedResponse result = new WrappedResponse();
        if (paramResult.hasErrors()) {
            result.setCode(HttpStateCode.PARAMETERS_NULL.getValue());
            result.setMsg(HttpStateCode.PARAMETERS_NULL.getReasonPhrase());
            return result;
        }
        Concern bean = new Concern();
        bean.setUser(param.getUser());
        bean.setProject(param.getProject());
        bean.setType(param.getType());
        bean.setHospital(param.getHospital());
        Project project = projectService.findByUuid(param.getProject());
        if(project != null) {
            bean.setImage(project.getLogo());
            bean.setName(project.getName());
            bean.setOldPrice(project.getOldPrice());
            bean.setNowPrice(project.getNowPrice());
        } else {
            ProjectGroup projectGroup = projectGroupService.findByUuid(param.getProject());
            if(projectGroup != null) {
                bean.setImage(projectGroup.getLogo());
                bean.setName(projectGroup.getName());
                bean.setOldPrice(projectGroup.getOldPrice());
                bean.setNowPrice(projectGroup.getNowPrice());
            }
        }
        if (StringUtils.isBlank(token)) {
            result.setCode(HttpStateCode.INVALID_TOKEN.getValue());
            result.setMsg(HttpStateCode.INVALID_TOKEN.getReasonPhrase());
            return result;
        }
        String user = JWToken.getUuid(token);
        bean.setUser(user);
        Concern resultObj = null;
        Concern concern = concernService.findByCondition(bean);
        if (concern != null) {
            if (param.getStatus() == 0) {
                concern.setCancelTime(new Date());
            } else {
                concern.setConcernTime(new Date());
            }
            concern.setStatus(param.getStatus());
            resultObj = concernService.update(concern);
            if (resultObj == null) {
                result.setCode(HttpStateCode.SAVE_FAILURE.getValue());
                result.setMsg(HttpStateCode.SAVE_FAILURE.getReasonPhrase());
                return result;
            }
        } else {
            resultObj = concernService.add(bean);
            if (resultObj == null) {
                result.setCode(HttpStateCode.SAVE_FAILURE.getValue());
                result.setMsg(HttpStateCode.SAVE_FAILURE.getReasonPhrase());
                return result;
            }
        }
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        result.setSuccessObject(resultObj);
        return result;
    }

    /*
     * @Description 关注列表
     * @Date 2018/12/27
     * @Param
     * @return
     * @Author zhy
     **/
    @GetMapping("list")
    public WrappedResponse list(@CookieValue(name = Constant.WX_TOKEN, required = false) String token,
                                SearchConcernDto condition) {
        WrappedResponse result = new WrappedResponse();
        if (StringUtils.isBlank(token)) {
            result.setCode(HttpStateCode.INVALID_TOKEN.getValue());
            result.setMsg(HttpStateCode.INVALID_TOKEN.getReasonPhrase());
            return result;
        }
        String user = JWToken.getUuid(token);
        condition.setUser(user);
        condition.setStatus(Byte.valueOf("0"));
        IPage<Concern> concernIPage = concernService.findAll(condition);
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        result.setSuccessObject(concernIPage);
        return result;
    }

}

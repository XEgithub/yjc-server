package com.dk.controller.rest;

import cn.sourcespro.commons.utils.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dk.config.Constant;
import com.dk.data.dto.ProjectDto;
import com.dk.data.dto.SearchCouponDto;
import com.dk.data.entity.*;
import com.dk.response.HttpStateCode;
import com.dk.response.WrappedResponse;
import com.dk.service.*;
import com.dk.shiro.JWToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @ClassName CouponRestController
 * @Description
 * @Author zyf
 * @Date 2018/12/10 11:56
 **/
@RestController
@RequestMapping("rest/coupon")
public class CouponRestController {

    @Autowired
    private CouponService couponService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectGroupService projectGroupService;

    @Autowired
    private WxUserService wxUserService;

    @Autowired
    private WxUserExtendService wxUserExtendService;

    /*
     * @Description 根据项目获取用户可用优惠券列表
     * @Date 2018/12/10
     * @Param
     * @return
     * @Author zhy
     **/
    @PostMapping("usable/list")
    public WrappedResponse usableList(@CookieValue(name = Constant.WX_TOKEN, required = false) String token,
                                      @RequestBody SearchCouponDto condition) {
        WrappedResponse result = new WrappedResponse();
        if (condition == null || condition.getProjects() == null || condition.getProjects().length == 0) {
            result.setCode(HttpStateCode.PARAMETERS_NULL.getValue());
            result.setMsg(HttpStateCode.PARAMETERS_NULL.getReasonPhrase());
            return result;
        }
        ProjectDto[] projectDtos = condition.getProjects();
        ProjectDto projectDto = null;
        List<Project> projectList = new ArrayList<>();
        List<ProjectGroup> projectGroupList = new ArrayList<>();
        int length = projectDtos.length;
        for (int i = 0; i < length; i++) {
            projectDto = projectDtos[i];
            if ("0".equals(projectDto.getType())) {
                Project project = projectService.findByUuid(projectDto.getUuid());
                projectList.add(project);
            } else {
                ProjectGroup projectGroup = projectGroupService.findByUuid(projectDto.getUuid());
                projectGroupList.add(projectGroup);
            }
        }
        BigDecimal totalMoney = new BigDecimal("0");
        if (!projectList.isEmpty()) {
            for (Project project : projectList) {
                BigDecimal nowPrice = project.getNowPrice();
                totalMoney = totalMoney.add(nowPrice);
            }
        }
        if (!projectGroupList.isEmpty()) {
            for (ProjectGroup projectGroup : projectGroupList) {
                BigDecimal nowPrice = projectGroup.getNowPrice();
                totalMoney = totalMoney.add(nowPrice);
            }
        }
        condition.setBeginTime(new Date());
        condition.setExpireTime(new Date());
        condition.setMinPrice(totalMoney);
        if (StringUtils.isBlank(token)) {
            result.setCode(HttpStateCode.INVALID_TOKEN.getValue());
            result.setMsg(HttpStateCode.INVALID_TOKEN.getReasonPhrase());
            return result;
        }
        String user = JWToken.getUuid(token);
        condition.setUser(user);
        condition.setStatus(Byte.valueOf("0"));
        condition.setType(false);
        List<Coupon> couponList = couponService.findUsableAll(condition);
        if (couponList.isEmpty()) {
            result.setCode(HttpStateCode.OK.getValue());
            result.setMsg(HttpStateCode.OK.getReasonPhrase());
            result.setSuccessObject(new String[0]);
            return result;
        }
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        result.setSuccessObject(couponList);
        return result;
    }

    /*
     * @Description 用户优惠券列表
     * @Date 2018/12/24
     * @Param
     * @return
     * @Author zhy
     **/
    @GetMapping("list")
    public WrappedResponse list(@CookieValue(name = Constant.WX_TOKEN, required = false) String token,
                                SearchCouponDto condition) {
        WrappedResponse result = new WrappedResponse();
        if (condition == null || condition.getStatus() == null) {
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
        condition.setUser(user);
        condition.setType(false);
        IPage<Coupon> couponIPage = couponService.findAll(condition);
        result.setSuccessObject(couponIPage);
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        return result;
    }

    /*
     * @Description 积分兑换优惠券
     * @Date 2019/1/2
     * @Param
     * @return
     * @Author zhy
     **/
    @GetMapping("conversion")
    public WrappedResponse conversion(@CookieValue(name = Constant.WX_TOKEN, required = false) String token,
                                      @RequestParam(value = "uuid", required = true) String uuid) {
        WrappedResponse result = new WrappedResponse();
        if (StringUtils.isBlank(token)) {
            result.setCode(HttpStateCode.INVALID_TOKEN.getValue());
            result.setMsg(HttpStateCode.INVALID_TOKEN.getReasonPhrase());
            return result;
        }
        String user = JWToken.getUuid(token);
        WxUser wxUser = wxUserService.getByUuid(user);
        Coupon coupon = couponService.findByUuid(uuid);
        if (coupon.getNeedIntegral() > wxUser.getIntegral()) {
            result.setCode(HttpStateCode.OPERATION_FAILURE.getValue());
            result.setMsg(HttpStateCode.OPERATION_FAILURE.getReasonPhrase());
            return result;
        }
        Coupon bean = new Coupon();
        BeanUtil.copyProperties(coupon, bean);
        bean.setType(false);
        bean.setUser(user);
        Coupon cou = couponService.add(bean);
        if (cou == null) {
            result.setCode(HttpStateCode.OPERATION_FAILURE.getValue());
            result.setMsg(HttpStateCode.OPERATION_FAILURE.getReasonPhrase());
            return result;
        }
        WxUserExtend wxUserExtend = wxUserExtendService.findByUser(user);
        Long integral = wxUser.getIntegral() - coupon.getNeedIntegral();
        wxUserExtend.setIntegral(integral);
        WxUserExtend wxUserEx = wxUserExtendService.update(wxUserExtend);
        if (wxUserEx == null) {
            result.setCode(HttpStateCode.OPERATION_FAILURE.getValue());
            result.setMsg(HttpStateCode.OPERATION_FAILURE.getReasonPhrase());
            return result;
        }
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        result.setSuccessObject(wxUserEx);
        return result;
    }


}

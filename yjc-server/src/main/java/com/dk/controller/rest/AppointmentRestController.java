package com.dk.controller.rest;

import cn.sourcespro.commons.utils.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dk.config.Constant;
import com.dk.data.dto.AddAppointmentDto;
import com.dk.data.dto.SearchAppointmentDto;
import com.dk.data.dto.SearchOrderDto;
import com.dk.data.entity.Appointment;
import com.dk.data.entity.Order;
import com.dk.data.entity.Project;
import com.dk.data.entity.ProjectGroup;
import com.dk.response.HttpStateCode;
import com.dk.response.WrappedResponse;
import com.dk.service.AppointmentService;
import com.dk.service.OrderService;
import com.dk.service.ProjectGroupService;
import com.dk.service.ProjectService;
import com.dk.shiro.JWToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

/**
 * @ClassName AppointmentRestController
 * @Description
 * @Author zyf
 * @Date 2018/12/12 15:07
 **/
@RestController
@RequestMapping("rest/appointment")
public class AppointmentRestController {

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    OrderService orderService;

    @Autowired
    ProjectService projectService;

    @Autowired
    ProjectGroupService projectGroupService;

    /*
     * @Description 添加预约
     * @Date 2018/12/12
     * @Param
     * @return
     * @Author zhy
     **/
    @PostMapping("add")
    public WrappedResponse add(@CookieValue(name = Constant.WX_TOKEN, required = false) String token,
                               @Validated @RequestBody AddAppointmentDto param, BindingResult paramResult) {
        WrappedResponse result = new WrappedResponse();
        if (paramResult.hasErrors() || param.getAppointmentTime().getTime() <= System.currentTimeMillis()) {
            result.setCode(HttpStateCode.PARAMETERS_NULL.getValue());
            result.setMsg(HttpStateCode.PARAMETERS_NULL.getReasonPhrase());
            return result;
        }
        Appointment app = appointmentService.findByOrderList(param.getOrderList());
        if (app != null) {
            result.setCode(HttpStateCode.ALREADY_REPORTED.getValue());
            result.setMsg(HttpStateCode.ALREADY_REPORTED.getReasonPhrase());
            return result;
        }
        Appointment bean = new Appointment();
        BeanUtil.copyProperties(param, bean);
        if (StringUtils.isBlank(token)) {
            result.setCode(HttpStateCode.INVALID_TOKEN.getValue());
            result.setMsg(HttpStateCode.INVALID_TOKEN.getReasonPhrase());
            return result;
        }
        String user = JWToken.getUuid(token);
        Order order = orderService.findByUserAndUuid(user, param.getOrderList());
        if (order == null) {
            result.setCode(HttpStateCode.OPERATION_FAILURE.getValue());
            result.setMsg(HttpStateCode.OPERATION_FAILURE.getReasonPhrase());
            return result;
        }
//      orderStatus订单状态（1:待付款 2:待预约 3:进行中 4:已完成 5:已评价 6:待退款 7:已退款）
//      serviceStatus服务状态(0:未接单 1:已接单 2:已采样 3:已送检 4:已上传报告)
        order.setOrderStatus("3");
        order.setServiceStatus("0");
        orderService.update(order);
        bean.setUserRemark(order.getUserRemark());
        bean.setNotes(order.getNotes());
        bean.setOperator(order.getUser());
        bean.setAddress(order.getAddress());
        bean.setHospital(order.getHospital());
        String[] project = order.getProject().split("-");
        Project pro = projectService.findByUuid(project[0]);
        if (pro != null && "陪诊服务".equals(pro.getName())) {
            bean.setType("1");
        } else {
            bean.setType("0");
        }
        Appointment appointment = appointmentService.add(bean);
        if (appointment == null) {
            result.setCode(HttpStateCode.SAVE_FAILURE.getValue());
            result.setMsg(HttpStateCode.SAVE_FAILURE.getReasonPhrase());
            return result;
        }
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        result.setSuccessObject(appointment);
        return result;
    }

    /*
     * @Description 是否符合免费重新预约或取消预约规则
     * @Date 2018/12/14
     * @Param 
     * @return 
     * @Author zhy
     **/
    @GetMapping("beforeRebook")
    public WrappedResponse isRebook(@CookieValue(name = Constant.WX_TOKEN, required = false) String token,
                                    @RequestParam(value = "uuid", required = true) String uuid) {
        WrappedResponse result = new WrappedResponse();
        if ("".equals(uuid)) {
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
        Order order = orderService.findByUserAndUuid(user, uuid);
        Boolean flag = appointmentService.isExtraPrice(order.getUuid());
        Map<String, Object> map = new HashMap<>();
        map.put("isRebook", flag);
        if (flag) {
            BigDecimal servicePrice = order.getServicePrice();
            BigDecimal extra = servicePrice.multiply(BigDecimal.valueOf(0.7)).setScale(2, BigDecimal.ROUND_HALF_UP);
            map.put("extraPrice", extra);
        } else {
            map.put("extraPrice", 0);
        }
        result.setSuccessObject(map);
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        return result;
    }

    /*
     * @Description 取消预约
     * @Date 2018/12/14
     * @Param
     * @return
     * @Author zhy
     **/
    @GetMapping("cancel")
    public WrappedResponse cancel(@RequestParam(value = "uuid", required = true) String uuid) {
        WrappedResponse result = new WrappedResponse();
        if ("".equals(uuid)) {
            result.setCode(HttpStateCode.PARAMETERS_NULL.getValue());
            result.setMsg(HttpStateCode.PARAMETERS_NULL.getReasonPhrase());
            return result;
        }
//        appointmentService.extraPrice(uuid);
        Appointment bean = appointmentService.findByUuid(uuid);
        bean.setStatus(Byte.valueOf("2"));
        Appointment appointment = appointmentService.update(bean);
        if (appointment == null) {
            result.setCode(HttpStateCode.UPDATE_FAILURE.getValue());
            result.setMsg(HttpStateCode.UPDATE_FAILURE.getReasonPhrase());
            return result;
        }
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        result.setSuccessObject(appointment);
        return result;
    }

    /*
     * @Description 根据订单获取预约
     * @Date 2018/12/14
     * @Param
     * @return
     * @Author zhy
     **/
    @GetMapping("getByOrder")
    public WrappedResponse list(@CookieValue(name = Constant.WX_TOKEN, required = false) String token,
                                SearchAppointmentDto condition) {
        WrappedResponse result = new WrappedResponse();
        if (condition.getOrderList() == null || "".equals(condition.getOrder())) {
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
        IPage<Appointment> informIPage = appointmentService.findAllByUser(condition);
        if (informIPage.getRecords().size() == 0) {
            result.setCode(HttpStateCode.NO_CONTENT.getValue());
            result.setMsg(HttpStateCode.NO_CONTENT.getReasonPhrase());
            return result;
        }
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        result.setSuccessObject(informIPage);
        return result;
    }

    /*
     * @Description 预约列表
     * @Date 2018/12/24
     * @Param
     * @return
     * @Author zhy
     **/
    @GetMapping("list")
    public WrappedResponse list(@CookieValue(name = Constant.WX_TOKEN, required = false) String token,
                                SearchOrderDto condition) {
        WrappedResponse result = new WrappedResponse();
        if (StringUtils.isBlank(token)) {
            result.setCode(HttpStateCode.INVALID_TOKEN.getValue());
            result.setMsg(HttpStateCode.INVALID_TOKEN.getReasonPhrase());
            return result;
        }
        String user = JWToken.getUuid(token);
        Map<String, Object> resultMap = new HashMap<>();
        List<Object> records = new ArrayList<>();
        Map<String, Object> map = null;
        List<Object> projectList = null;
        condition.setUser(user);
        IPage<Order> orderIPage = orderService.getAllByAppointment(condition);
        List<Order> orderList = orderIPage.getRecords();
        for (Order order : orderList) {
            map = new HashMap<>();
            map.put("order", order);
            String[] projectsArray = order.getProject().split(",");
            int length = projectsArray.length;
            for (int i = 0; i < length; i++) {
                projectList = new ArrayList<>();
                String[] projectArray = projectsArray[i].split("-");
                if ("0".equals(projectArray[1])) {
                    Project project = projectService.findByUuid(projectArray[0]);
                    projectList.add(project);
                } else {
                    ProjectGroup projectGroup = projectGroupService.findByUuid(projectArray[0]);
                    projectList.add(projectGroup);
                }
            }
            map.put("projectList", projectList);
            Appointment appointment = appointmentService.findByOrderList(order.getUuid());
            map.put("appointment", appointment);
            records.add(map);
        }
        resultMap.put("records", records);
        resultMap.put("total", orderIPage.getTotal());
        resultMap.put("size", orderIPage.getSize());
        resultMap.put("current", orderIPage.getCurrent());
        resultMap.put("pages", orderIPage.getPages());
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        result.setSuccessObject(resultMap);
        return result;
    }

}

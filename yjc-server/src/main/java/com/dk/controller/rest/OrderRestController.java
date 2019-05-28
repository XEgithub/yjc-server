package com.dk.controller.rest;

import cn.sourcespro.commons.utils.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dk.config.Constant;
import com.dk.data.dto.AddOrderDto;
import com.dk.data.dto.EditOrderDto;
import com.dk.data.dto.ProjectDto;
import com.dk.data.dto.SearchOrderDto;
import com.dk.data.entity.*;
import com.dk.response.HttpStateCode;
import com.dk.response.WrappedResponse;
import com.dk.service.*;
import com.dk.shiro.JWToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

/**
 * @ClassName OrderListRestController
 * @Description
 * @Author zyf
 * @Date 2018/12/6 15:50
 **/
@RestController
@RequestMapping("rest/order")
public class OrderRestController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectGroupService projectGroupService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private CouponService couponService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private InformService informService;

    /*
     * @Description 添加订单
     * @Date 2018/12/14
     * @Param
     * @return
     * @Author zhy
     **/
    @PostMapping("add")
    public WrappedResponse add(@CookieValue(name = Constant.WX_TOKEN, required = false) String token,
                               @Validated @RequestBody AddOrderDto param, BindingResult paramResult) {
        WrappedResponse result = new WrappedResponse();
        if (paramResult.hasErrors()) {
            result.setCode(HttpStateCode.PARAMETERS_NULL.getValue());
            result.setMsg(HttpStateCode.PARAMETERS_NULL.getReasonPhrase());
            return result;
        }
        if (param.getCoupon() != null && !"".equals(param.getCoupon())) {
            Coupon coupon = couponService.findByUuid(param.getCoupon());
            if (coupon != null && coupon.getStatus() != 1) {
                coupon.setStatus(Byte.valueOf("1"));
                couponService.update(coupon);
            }
        }
        BigDecimal decimal = param.getProjectPrice().add(param.getServicePrice()).subtract(param.getCouponPrice());
        BigDecimal decimalPrice = param.getPrice();
        if (!(decimal.compareTo(decimalPrice) == 0)) {
            result.setCode(HttpStateCode.OPERATION_FAILURE.getValue());
            result.setMsg(HttpStateCode.OPERATION_FAILURE.getReasonPhrase());
            return result;
        }
        Order bean = new Order();
        BeanUtil.copyProperties(param, bean);
        if (StringUtils.isBlank(token)) {
            result.setCode(HttpStateCode.INVALID_TOKEN.getValue());
            result.setMsg(HttpStateCode.INVALID_TOKEN.getReasonPhrase());
            return result;
        }
        String user = JWToken.getUuid(token);
        bean.setUser(user);
        ProjectDto[] projectDto = param.getProjects();
        StringBuffer uuidsStr = new StringBuffer();
        int length = projectDto.length;
        for (int i = 0; i < length; i++) {
            String hospital = null;
            ProjectDto pro = projectDto[i];
            if ("0".equals(pro.getType())) {
                Project project = projectService.findByUuid(pro.getUuid());
                if (hospital == null) {
                    hospital = project.getHospital();
                } else {
                    if (!hospital.equals(project.getHospital())) {
                        result.setCode(HttpStateCode.OPERATION_FAILURE.getValue());
                        result.setMsg(HttpStateCode.OPERATION_FAILURE.getReasonPhrase());
                        return result;
                    }
                }
            bean.setHospital(hospital);
            } else {
                ProjectGroup projectGroup = projectGroupService.findByUuid(pro.getUuid());
                if (hospital == null) {
                    hospital = projectGroup.getHospital();
                } else {
                    if (!hospital.equals(projectGroup.getHospital())) {
                        result.setCode(HttpStateCode.OPERATION_FAILURE.getValue());
                        result.setMsg(HttpStateCode.OPERATION_FAILURE.getReasonPhrase());
                        return result;
                    }
                }
            }
            uuidsStr.append(pro.getUuid() + "-" + pro.getType());
            if (i < length - 1) {
                uuidsStr.append(",");
            }
        }
        bean.setProject(uuidsStr.toString());
        Patient patient = patientService.findByUuid(param.getPatient());
        bean.setAddress(patient.getAddress());
        Order order = orderService.add(bean);
        if (order == null) {
            result.setCode(HttpStateCode.SAVE_FAILURE.getValue());
            result.setMsg(HttpStateCode.SAVE_FAILURE.getReasonPhrase());
            return result;
        }
        for (int i = 0; i < length; i++) {
            ShoppingCart shoppingCart = shoppingCartService.findByUserAndProjectUuid(user, projectDto[i].getUuid());
            if (shoppingCart != null) {
                shoppingCart.setStatus(true);
                shoppingCartService.update(shoppingCart);
            }
        }
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        result.setSuccessObject(order);
        return result;
    }

    /*
     * @Description 订单列表
     * @Date 2018/12/14
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
        condition.setUser(user);
        IPage<Order> orderIPage = orderService.getAll(condition);
        List<Order> orderList = orderIPage.getRecords();
        List<Object> records = new ArrayList<>();
        for (Order order : orderList) {
            List<Object> projectList = new ArrayList<>();
            Map<String, Object> map = new HashMap<>();
            map.put("order", order);
            Patient patient = patientService.findByOrderUuid(order.getUuid());
            map.put("patient", patient);
            String[] projectsArray = order.getProject().split(",");
            int length = projectsArray.length;
            for (int i = 0; i < length; i++) {
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
            records.add(map);
        }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("records", records);
        resultMap.put("total", orderIPage.getTotal());
        resultMap.put("size", orderIPage.getSize());
        resultMap.put("current", orderIPage.getCurrent());
        resultMap.put("pages", orderIPage.getPages());
        if (orderIPage == null) {
            result.setCode(HttpStateCode.NO_CONTENT.getValue());
            result.setMsg(HttpStateCode.NO_CONTENT.getReasonPhrase());
            return result;
        }
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        result.setSuccessObject(resultMap);
        return result;
    }

    /*
     * @Description 订单详情
     * @Date 2018/12/12
     * @Param
     * @return
     * @Author zhy
     **/
    @GetMapping("detail")
    public WrappedResponse detail(@CookieValue(name = Constant.WX_TOKEN, required = false) String token,
                                  SearchOrderDto condition) {
        WrappedResponse result = new WrappedResponse();
        if (condition == null || condition.getUuid() == null || "".equals(condition.getUuid())) {
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
        Order order = orderService.findByUserAndUuid(user,condition.getUuid());

        List<Object> projectList = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("order", order);
        String[] projectsArray = order.getProject().split(",");
        int length = projectsArray.length;
        for (int i = 0; i < length; i++) {
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
        Patient patient = patientService.findByUuid(order.getPatient());
        map.put("patient", patient);

        Appointment appointment = appointmentService.findByOrderList(order.getUuid());
        map.put("appointment", appointment);

        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        result.setSuccessObject(map);
        return result;
    }

    /*
     * @Description 订单删除
     * @Date 2018/12/11
     * @Param
     * @return
     * @Author zhy
     **/
    @GetMapping("delete")
    public WrappedResponse delete(@CookieValue(name = Constant.WX_TOKEN, required = false) String token,
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
        if (order == null) {
            result.setCode(HttpStateCode.OPERATION_FAILURE.getValue());
            result.setMsg(HttpStateCode.OPERATION_FAILURE.getReasonPhrase());
            return result;
        }
        if (!"1".equals(order.getOrderStatus()) && !"4".equals(order.getOrderStatus()) && "5".equals(order.getOrderStatus()) && "7".equals(order.getOrderStatus())) {
            result.setCode(HttpStateCode.LOCKED.getValue());
            result.setMsg(HttpStateCode.LOCKED.getReasonPhrase());
            return result;
        }
        int flag = orderService.delete(order.getUuid());
        if (flag != 1) {
            result.setCode(HttpStateCode.DELETE_FAILURE.getValue());
            result.setMsg(HttpStateCode.DELETE_FAILURE.getReasonPhrase());
            return result;
        }
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        result.setSuccessObject(order);
        return result;
    }

    /*
     * @Description 更新订单信息
     * @Date 2018/12/18
     * @Param
     * @return
     * @Author zhy
     **/
    @PostMapping("update")
    public WrappedResponse update(@CookieValue(name = Constant.WX_TOKEN, required = false) String token,
                                  @Validated @RequestBody EditOrderDto param, BindingResult paramResult) {
        WrappedResponse result = new WrappedResponse();
        if (paramResult.hasErrors()) {
            result.setCode(HttpStateCode.PARAMETERS_NULL.getValue());
            result.setMsg(HttpStateCode.PARAMETERS_NULL.getReasonPhrase());
            return result;
        }
        BigDecimal decimal = param.getProjectPrice().add(param.getServicePrice()).subtract(param.getCouponPrice());
        BigDecimal decimalPrice = param.getPrice();
        if (!(decimal.compareTo(decimalPrice) == 0)) {
            result.setCode(HttpStateCode.OPERATION_FAILURE.getValue());
            result.setMsg(HttpStateCode.OPERATION_FAILURE.getReasonPhrase());
            return result;
        }
        if (StringUtils.isBlank(token)) {
            result.setCode(HttpStateCode.INVALID_TOKEN.getValue());
            result.setMsg(HttpStateCode.INVALID_TOKEN.getReasonPhrase());
            return result;
        }
        String user = JWToken.getUuid(token);
        Order bean = orderService.findByUserAndUuid(user, param.getUuid());
        if (!"1".equals(bean.getOrderStatus())) {
            result.setCode(HttpStateCode.LOCKED.getValue());
            result.setMsg(HttpStateCode.LOCKED.getReasonPhrase());
            return result;
        }
        if (param.getCoupon() != null && !"".equals(param.getCoupon())) {
            if (bean.getCoupon() != null && !"".equals(bean.getCoupon())) {
                Coupon coupon = couponService.findByUuid(bean.getCoupon());
                coupon.setStatus(Byte.valueOf("0"));
                couponService.update(coupon);
            }
            Coupon coup = couponService.findByUuid(param.getCoupon());
            coup.setStatus(Byte.valueOf("1"));
            couponService.update(coup);
        }
        Patient patient = patientService.findByUuid(param.getPatient());
        bean.setAddress(patient.getAddress());
        bean.setUserRemark(param.getRemark());
        bean.setPaperReport(param.getPaperReport());
        Order order = orderService.update(bean);
        if (order == null) {
            result.setCode(HttpStateCode.SAVE_FAILURE.getValue());
            result.setMsg(HttpStateCode.SAVE_FAILURE.getReasonPhrase());
            return result;
        }
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        result.setSuccessObject(order);
        return result;
    }

    /*
     * @Description 更改支付状态为已支付
     * @Date 2018/12/19
     * @Param
     * @return
     * @Author zhy
     **/
    @GetMapping("paid")
    public WrappedResponse update(@CookieValue(name = Constant.WX_TOKEN, required = false) String token,
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
        Order bean = orderService.findByUserAndUuid(user, uuid);
        if (bean == null) {
            result.setCode(HttpStateCode.NOT_FOUND.getValue());
            result.setMsg(HttpStateCode.NOT_FOUND.getReasonPhrase());
            return result;
        }
        bean.setOrderStatus("2");
        Order order = orderService.update(bean);
        if (order == null) {
            result.setCode(HttpStateCode.SAVE_FAILURE.getValue());
            result.setMsg(HttpStateCode.SAVE_FAILURE.getReasonPhrase());
            return result;
        }
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        result.setSuccessObject(order);
        return result;
    }

    /*
     * @Description 取消订单
     * @Date 2018/12/19
     * @Param
     * @return
     * @Author zhy
     **/
    @GetMapping("cancel")
    public WrappedResponse cancel(@CookieValue(name = Constant.WX_TOKEN, required = false) String token,
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
        Order bean = orderService.findByUserAndUuid(user, uuid);
        if (bean != null && !"1".equals(bean.getOrderStatus())) {
            result.setCode(HttpStateCode.LOCKED.getValue());
            result.setMsg(HttpStateCode.LOCKED.getReasonPhrase());
            return result;
        }
        bean.setOrderStatus("8");
        Order order = orderService.update(bean);
        if (order == null) {
            result.setCode(HttpStateCode.UPDATE_FAILURE.getValue());
            result.setMsg(HttpStateCode.UPDATE_FAILURE.getReasonPhrase());
            return result;
        }
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        result.setSuccessObject(order);
        return result;
    }

    /*
     * @Description 申请退款
     * @Date 2018/12/19
     * @Param
     * @return
     * @Author zhy
     **/
    @GetMapping("beforeRefund")
    public WrappedResponse refund(@CookieValue(name = Constant.WX_TOKEN, required = false) String token,
                                  @RequestParam(value = "uuid", required = true) String uuid) {
        WrappedResponse result = new WrappedResponse();
        if (StringUtils.isBlank(token)) {
            result.setCode(HttpStateCode.INVALID_TOKEN.getValue());
            result.setMsg(HttpStateCode.INVALID_TOKEN.getReasonPhrase());
            return result;
        }
        String user = JWToken.getUuid(token);
        Order order = orderService.findByUserAndUuid(user, uuid);
        if (order == null) {
            result.setCode(HttpStateCode.NOT_FOUND.getValue());
            result.setMsg(HttpStateCode.NOT_FOUND.getReasonPhrase());
            return result;
        }
        String oStatus = order.getOrderStatus();
//      订单状态（1:待付款 2:待预约 3:进行中 4:已完成 5:已评价 6:待退款 7:已退款）
//      服务状态(0:未接单 1:已接单 2:已采样 3:已送检 4:已上传报告)
        if("1".equals(oStatus) || "4".equals(oStatus) || "5".equals(oStatus) || "6".equals(oStatus) || "7".equals(oStatus)) {
            result.setCode(HttpStateCode.LOCKED.getValue());
            result.setMsg(HttpStateCode.LOCKED.getReasonPhrase());
            return result;
        }
        if ("3".equals(oStatus)) {
            String sStatus = order.getServiceStatus();
            if ("2".equals(sStatus) || "3".equals(sStatus) || "4".equals(sStatus)) {
                result.setCode(HttpStateCode.LOCKED.getValue());
                result.setMsg(HttpStateCode.LOCKED.getReasonPhrase());
                return result;
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("order", order);
        List<Object> projectList = new ArrayList<>();
        String[] projectsArray = order.getProject().split(",");
        for (int i = 0; i <projectsArray.length; i++) {
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
        BigDecimal price = order.getPrice();
        map.put("refundPrice", price);
        if ("3".equals(oStatus)) {
            Appointment appointment = appointmentService.findByOrderList(order.getUuid());
            Boolean flag = appointmentService.isExtraPrice(appointment.getUuid());
            if (flag) {
                BigDecimal servicePrice = order.getServicePrice();
                BigDecimal extra = servicePrice.multiply(BigDecimal.valueOf(0.7)).setScale(2, BigDecimal.ROUND_HALF_UP);
                map.put("refundPrice", price.subtract(extra));
            }
        }
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        result.setSuccessObject(map);
        return result;
    }

    /*
     * @Description 提交退款
     * @Date 2018/12/20
     * @Param
     * @return
     * @Author zhy
     **/
    @PostMapping("submitRefund")
    public WrappedResponse refund(@CookieValue(name = Constant.WX_TOKEN, required = false) String token,
                                  @RequestBody Order param) {
        WrappedResponse result = new WrappedResponse();
        if (param == null || param.getUuid() == null || param.getRefundPrice() == null || param.getRefundCause() == null) {
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
        Order bean = orderService.findByUserAndUuid(user, param.getUuid());
        if ("3".equals(bean.getOrderStatus()) && "1".equals(bean.getServiceStatus())) {
            Appointment appointment = appointmentService.findByOrderList(bean.getUuid());
            Boolean flag = appointmentService.isExtraPrice(appointment.getUuid());
            if (flag) {
                BigDecimal servicePrice = bean.getServicePrice();
                BigDecimal extra = servicePrice.multiply(BigDecimal.valueOf(0.7)).setScale(2, BigDecimal.ROUND_HALF_UP);
                if (param.getRefundPrice().compareTo(bean.getPrice().subtract(extra)) == 0) {
                    bean.setRebookPrice(extra);
                } else {
                    result.setCode(HttpStateCode.OPERATION_FAILURE.getValue());
                    result.setMsg(HttpStateCode.OPERATION_FAILURE.getReasonPhrase());
                    return result;
                }
            }
        } else {
            if (bean.getPrice().compareTo(param.getRefundPrice()) != 0) {
                result.setCode(HttpStateCode.OPERATION_FAILURE.getValue());
                result.setMsg(HttpStateCode.OPERATION_FAILURE.getReasonPhrase());
                return result;
            }
        }
        bean.setOrderStatus("6");
        bean.setRefundPrice(param.getRefundPrice());
        bean.setApplicationRefundTime(new Date());
        bean.setRefundCause(param.getRefundCause());
        bean.setOperator(user);
        Order order = orderService.update(bean);
        if (order == null) {
            result.setCode(HttpStateCode.OPERATION_FAILURE.getValue());
            result.setMsg(HttpStateCode.OPERATION_FAILURE.getReasonPhrase());
            return result;
        }
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        result.setSuccessObject(order);
        return result;
    }

    /*
     * @Description 更改退款状态为已退款
     * @Date 2018/12/20
     * @Param
     * @return
     * @Author zhy
     **//*
    @GetMapping("refund")
    public WrappedResponse affirmRefund(@CookieValue(name = Constant.WX_TOKEN, required = false) String token,
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
        Order bean = orderService.findByUserAndUuid(user, uuid);
        if (bean.getRefundTime() != null) {
            result.setCode(HttpStateCode.LOCKED.getValue());
            result.setMsg(HttpStateCode.LOCKED.getReasonPhrase());
            return result;
        }
        bean.setOrderStatus("7");
        bean.setOperator(user);
        bean.setRefundTime(new Date());
        Order order = orderService.update(bean);
        if (order == null) {
            result.setCode(HttpStateCode.UPDATE_FAILURE.getValue());
            result.setMsg(HttpStateCode.UPDATE_FAILURE.getReasonPhrase());
            return result;
        }
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        result.setSuccessObject(order);
        return result;
    }*/


}

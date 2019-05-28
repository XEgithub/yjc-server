package com.dk.controller.rest;

import cn.sourcespro.commons.utils.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dk.config.Constant;
import com.dk.data.dto.AddBarcodeDto;
import com.dk.data.dto.AddNurseDto;
import com.dk.data.dto.SearchCommentDto;
import com.dk.data.dto.SearchNurseAppointmentDto;
import com.dk.data.entity.*;
import com.dk.response.HttpStateCode;
import com.dk.response.WrappedResponse;
import com.dk.service.*;
import com.dk.shiro.JWToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName NurseRestController
 * @Description
 * @Author zyf
 * @Date 2019/1/13 15:26
 **/
@RestController
@RequestMapping("rest/nurse")
public class NurseRestController {

    @Autowired
    private NurseService nurseService;

    @Autowired
    private NurseAppointmentService nurseAppointmentService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private BarcodeService barcodeService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectGroupService projectGroupService;

    @Autowired
    private BillService billService;

    @Autowired
    private CommentService commentService;

    /**
     * @Description 抢单列表
     * @Date 2019/1/14
     * @Param
     * @return
     * @Author zhy
     **/
    @RequestMapping(value = "lobby", method = RequestMethod.GET)
    public WrappedResponse lobby(@CookieValue(name = Constant.WX_TOKEN, required = false) String token,
                                 SearchNurseAppointmentDto condition) {
        WrappedResponse result = new WrappedResponse();
        if (StringUtils.isBlank(token)) {
            result.setCode(HttpStateCode.INVALID_TOKEN.getValue());
            result.setMsg(HttpStateCode.INVALID_TOKEN.getReasonPhrase());
            return result;
        }
        String user = JWToken.getUuid(token);
        Nurse nurse = nurseService.findByUser(user);
        if (nurse == null) {
            result.setCode(HttpStateCode.NO_CONTENT.getValue());
            result.setMsg(HttpStateCode.NO_CONTENT.getReasonPhrase());
            return result;
        }
        if (condition.getStatus() == 1) {
            condition.setStatus(null);
        }
        condition.setNurse(nurse.getUuid());
        IPage<NurseAppointment> nurseAppointmentIPage = nurseAppointmentService.findAll(condition);
        List<NurseAppointment> nurseAppointments = nurseAppointmentIPage.getRecords();

        Map<String, Object> resultMap = new HashMap<>();
        List<Object> records = new ArrayList<>();
        Map<String, Object> map;
        for (NurseAppointment nurseAppointment : nurseAppointments) {
            if (nurseAppointment == null) {
                result.setCode(HttpStateCode.OPERATION_FAILURE.getValue());
                result.setMsg(HttpStateCode.OPERATION_FAILURE.getReasonPhrase());
                return result;
            }
            map = new HashMap<>();
            map.put("nurseAppointment", nurseAppointment);
            Appointment appointment = appointmentService.findByUuid(nurseAppointment.getAppointment());
            map.put("appointment", appointment);
            Order order = orderService.findByAppointment(appointment.getUuid());
            map.put("order", order);
            Patient patient = patientService.findByOrderUuid(order.getUuid());
            map.put("patient", patient);
            records.add(map);
        }
        resultMap.put("records", records);
        resultMap.put("total", nurseAppointmentIPage.getTotal());
        resultMap.put("size", nurseAppointmentIPage.getSize());
        resultMap.put("current", nurseAppointmentIPage.getCurrent());
        resultMap.put("pages", nurseAppointmentIPage.getPages());
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        result.setSuccessObject(resultMap);
        return result;


    }

    /**
     * @Description 订单详情
     * @Date 2019/1/16
     * @Param
     * @return
     * @Author zhy
     **/
    @GetMapping("lobbyDetail")
    public WrappedResponse lobbyDetail(@CookieValue(name = Constant.WX_TOKEN, required = false) String token,
                                       @RequestParam(value = "uuid", required = true)String uuid) {
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
        Map<String, Object> resultMap = new HashMap<>();
        Nurse nurse = nurseService.findByUser(user);
        NurseAppointment ns = nurseAppointmentService.findByNurseAndUuid(nurse.getUuid(), uuid);
        if (ns == null) {
            result.setCode(HttpStateCode.OPERATION_FAILURE.getValue());
            result.setMsg(HttpStateCode.OPERATION_FAILURE.getReasonPhrase());
            return result;
        }
        Appointment appointment = appointmentService.findByUuid(ns.getAppointment());
        resultMap.put("appointment", appointment);
        List<Barcode> barcode = barcodeService.findByAppointment(appointment.getUuid());
        resultMap.put("barcode", barcode);
        Order order = orderService.findByAppointment(appointment.getUuid());
        resultMap.put("order", order);
        List<Object> projects = new ArrayList<>();
        String[] projectsArray = order.getProject().split(",");
        int length = projectsArray.length;
        for (int i = 0; i < length; i++) {
            String[] projectArray = projectsArray[i].split("-");
            if ("0".equals(projectArray[1])) {
                Project project = projectService.findByUuid(projectArray[0]);
                projects.add(project);
            } else {
                ProjectGroup projectGroup = projectGroupService.findByUuid(projectArray[0]);
                projects.add(projectGroup);
            }
        }
        resultMap.put("projects", projects);
        Patient patient = patientService.findByOrderUuid(order.getUuid());
        resultMap.put("patient", patient);
        NurseAppointment nurseAppointment = nurseAppointmentService.findByUuid(uuid);
        resultMap.put("nurseAppointment",nurseAppointment);
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        result.setSuccessObject(resultMap);
        return result;
    }

    /**
     * @Description 个人信息提交
     * @Date 2019/1/14
     * @Param
     * @return
     * @Author zhy
     **/
    @PostMapping("addPersonal")
    public WrappedResponse addPersonal(@CookieValue(name = Constant.WX_TOKEN, required = false) String token,
                                       @RequestBody AddNurseDto param) {
        WrappedResponse result = new WrappedResponse();
        if (param.getLogo() == null || param.getName() == null || param.getSex() == null ||
            param.getAge() == null || param.getPhone() == null || param.getCertificatesNo() == null ||
            param.getCertificatesType() == null || param.getAddress() == null || param.getHospital() == null) {
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
        Nurse bean = nurseService.findByUser(user);
        BeanUtil.copyProperties(param, bean);
        Nurse nur = nurseService.update(bean);
        if (nur == null) {
            result.setCode(HttpStateCode.SAVE_FAILURE.getValue());
            result.setMsg(HttpStateCode.SAVE_FAILURE.getReasonPhrase());
            return result;
        }
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        result.setSuccessObject(nur);
        return result;
    }

    /**
     * @Description 身份认证提交审核
     * @Date 2019/1/14
     * @Param 
     * @return 
     * @Author zhy
     **/
    @PostMapping("addIdentity")
    public WrappedResponse addIdentity(@CookieValue(name = Constant.WX_TOKEN, required = false) String token,
                                       @RequestBody AddNurseDto param) {
        WrappedResponse result = new WrappedResponse();
        if (param.getIdCardJust() == null || param.getIdCardAgainst() == null || param.getProfessionImage() == null ||
            param.getPracticeImage() == null || param.getChestCardImage() == null) {
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
        Nurse bean = nurseService.findByUser(user);
        BeanUtil.copyProperties(param, bean);
        bean.setStatus(Byte.valueOf("1"));
        Nurse nur = nurseService.update(bean);
        if (nur == null) {
            result.setCode(HttpStateCode.SAVE_FAILURE.getValue());
            result.setMsg(HttpStateCode.SAVE_FAILURE.getReasonPhrase());
            return result;
        }
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        result.setSuccessObject(nur);
        return result;
    }

    /**
     * @Description 接单状态设置
     * @Date 2019/1/14
     * @Param 
     * @return 
     * @Author zhy
     **/
    @GetMapping("status")
    public WrappedResponse status(@CookieValue(name = Constant.WX_TOKEN, required = false) String token,
                                  @RequestParam(value = "status", required = true) Boolean status) {
        WrappedResponse result = new WrappedResponse();
        if (StringUtils.isBlank(token)) {
            result.setCode(HttpStateCode.INVALID_TOKEN.getValue());
            result.setMsg(HttpStateCode.INVALID_TOKEN.getReasonPhrase());
            return result;
        }
        String user = JWToken.getUuid(token);
        Nurse bean = nurseService.findByUser(user);
        bean.setServiceStatus(status);
        Nurse nurse = nurseService.update(bean);
        if (nurse == null) {
            result.setCode(HttpStateCode.UPDATE_FAILURE.getValue());
            result.setMsg(HttpStateCode.UPDATE_FAILURE.getReasonPhrase());
            return result;
        }
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        result.setSuccessObject(nurse);
        return result;
    }

    /**
     * @Description 自动派单
     * @Date 2019/1/14
     * @Param 
     * @return 
     * @Author zhy
     **/
    @GetMapping("auto")
    public WrappedResponse auto() {
        WrappedResponse result = new WrappedResponse();
        appointmentService.autoAllocation();
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        return result;
    }

    /**
     * @Description 接受预约
     * @Date 2019/1/15
     * @Param
     * @return
     * @Author zhy
     **/
    @GetMapping("accept")
    public WrappedResponse accept(@CookieValue(name = Constant.WX_TOKEN, required = false) String token,
                                  @RequestParam(value = "uuid", required = true)String uuid) {
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
        Nurse nurse = nurseService.findByUser(user);
        NurseAppointment bean = nurseAppointmentService.findByNurseAndUuid(nurse.getUuid(), uuid);
        if (bean == null) {
            result.setCode(HttpStateCode.OPERATION_FAILURE.getValue());
            result.setMsg(HttpStateCode.OPERATION_FAILURE.getReasonPhrase());
            return result;
        }
        bean.setStatus(Byte.valueOf("3"));
        nurseAppointmentService.update(bean);
        Order order = orderService.findByAppointment(bean.getAppointment());
        order.setServiceStatus("1");
        Order or = orderService.update(order);
        if (or == null) {
            result.setCode(HttpStateCode.OPERATION_FAILURE.getValue());
            result.setMsg(HttpStateCode.OPERATION_FAILURE.getReasonPhrase());
            return result;
        }
        Appointment appointment = appointmentService.findByUuid(bean.getAppointment());
        appointment.setStatus(Byte.valueOf("1"));
        Appointment ap = appointmentService.update(appointment);
        if (ap == null) {
            result.setCode(HttpStateCode.OPERATION_FAILURE.getValue());
            result.setMsg(HttpStateCode.OPERATION_FAILURE.getReasonPhrase());
            return result;
        }
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        result.setSuccessObject(bean);
        return result;
    }

    /**
     * @Description 取消接单
     * @Date 2019/1/15
     * @Param 
     * @return 
     * @Author zhy
     **/
    @GetMapping("cancel")
    public WrappedResponse cancel(@CookieValue(name = Constant.WX_TOKEN, required = false) String token,
                                  @RequestParam(value = "uuid", required = false)String uuid) {
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
        Nurse nurse = nurseService.findByUser(user);
        NurseAppointment bean = nurseAppointmentService.findByNurseAndUuid(nurse.getUuid(), uuid);
        bean.setStatus(Byte.valueOf("1"));
        NurseAppointment nurseAppointment = nurseAppointmentService.update(bean);
        if (nurseAppointment == null) {
            result.setCode(HttpStateCode.OPERATION_FAILURE.getValue());
            result.setMsg(HttpStateCode.OPERATION_FAILURE.getReasonPhrase());
            return result;
        }
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        result.setSuccessObject(nurseAppointment);
        return result;
    }

    /**
     * @Description 上传采样凭证
     * @Date 2019/1/16
     * @Param 
     * @return 
     * @Author zhy
     **/
    @PostMapping("proof")
    public WrappedResponse proof(@RequestBody Appointment param) {
        WrappedResponse result = new WrappedResponse();
        if (param.getUuid() == null || param.getEvidence() == null) {
            result.setCode(HttpStateCode.PARAMETERS_NULL.getValue());
            result.setMsg(HttpStateCode.PARAMETERS_NULL.getReasonPhrase());
            return result;
        }
        Order order = orderService.findByAppointment(param.getUuid());
        order.setServiceStatus("2");
        Order or = orderService.update(order);
        if (or == null) {
            result.setCode(HttpStateCode.OPERATION_FAILURE.getValue());
            result.setMsg(HttpStateCode.OPERATION_FAILURE.getReasonPhrase());
            return result;
        }
        Appointment appointment = appointmentService.findByUuid(param.getUuid());
        appointment.setEvidence(appointment.getEvidence());
        Appointment app = appointmentService.update(appointment);
        if (app == null) {
            result.setCode(HttpStateCode.SAVE_FAILURE.getValue());
            result.setMsg(HttpStateCode.SAVE_FAILURE.getReasonPhrase());
            return result;
        }
        NurseAppointment nurseAppointment = nurseAppointmentService.findByAppointmentAndStatus(appointment.getUuid(), Byte.valueOf("3")).get(0);
        nurseAppointment.setStatus(Byte.valueOf("4"));
        NurseAppointment ns = nurseAppointmentService.update(nurseAppointment);
        if (ns == null) {
            result.setCode(HttpStateCode.OPERATION_FAILURE.getValue());
            result.setMsg(HttpStateCode.OPERATION_FAILURE.getReasonPhrase());
            return result;
        }
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        result.setSuccessObject(app);
        return result;
    }

    /**
     * @Description 送检录码
     * @Date 2019/1/16
     * @Param 
     * @return 
     * @Author zhy
     **/
    @PostMapping("submission")
    public WrappedResponse submission(@CookieValue(name = Constant.WX_TOKEN, required = false) String token,
                                      @RequestBody AddBarcodeDto[] barcodeDtos) {
        WrappedResponse result = new WrappedResponse();
        int length = barcodeDtos.length;
        if (length == 0) {
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
        Order order = orderService.findByAppointment(barcodeDtos[0].getAppointment());
        if (!user.equals(order.getUser())) {
            result.setCode(HttpStateCode.OPERATION_FAILURE.getValue());
            result.setMsg(HttpStateCode.OPERATION_FAILURE.getReasonPhrase());
            return result;
        }
        order.setServiceStatus("3");
        Order or = orderService.update(order);
        if (or == null) {
            result.setCode(HttpStateCode.OPERATION_FAILURE.getValue());
            result.setMsg(HttpStateCode.OPERATION_FAILURE.getReasonPhrase());
            return result;
        }
        NurseAppointment nurseAppointment = nurseAppointmentService.findByAppointmentAndStatus(barcodeDtos[0].getAppointment(), Byte.valueOf("4")).get(0);
        nurseAppointment.setStatus(Byte.valueOf("7"));
        NurseAppointment ns = nurseAppointmentService.update(nurseAppointment);
        if (ns == null) {
            result.setCode(HttpStateCode.OPERATION_FAILURE.getValue());
            result.setMsg(HttpStateCode.OPERATION_FAILURE.getReasonPhrase());
            return result;
        }
        Barcode barcode = new Barcode();
        for (int i = 0; i < length; i++) {
            BeanUtil.copyProperties(barcodeDtos[i], barcode);
            Barcode bar = barcodeService.add(barcode);
            if (bar == null) {
                result.setCode(HttpStateCode.OPERATION_FAILURE.getValue());
                result.setMsg(HttpStateCode.OPERATION_FAILURE.getReasonPhrase());
                return result;
            }
        }
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        return result;
    }

    /**
     * @Description 护士对账
     * @Date 2019/1/18
     * @Param
     * @return
     * @Author zhy
     **/
    @GetMapping("bill")
    public WrappedResponse bill(@CookieValue(name = Constant.WX_TOKEN, required = false) String token) {
        WrappedResponse result = new WrappedResponse();
        if (StringUtils.isBlank(token)) {
            result.setCode(HttpStateCode.INVALID_TOKEN.getValue());
            result.setMsg(HttpStateCode.INVALID_TOKEN.getReasonPhrase());
            return result;
        }
        String user = JWToken.getUuid(token);
        Nurse nurse = nurseService.findByUser(user);
        List<Bill> bills = billService.findByNurse(nurse.getUuid());
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("nurse", nurse);
        resultMap.put("bills", bills);
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        result.setSuccessObject(resultMap);
        return result;
    }

    /**
     * @Description 护士评论
     * @Date 2019/1/18
     * @Param
     * @return
     * @Author zhy
     **/
    @GetMapping("comment")
    public WrappedResponse comment(@CookieValue(name = Constant.WX_TOKEN, required = false) String token,
                                   SearchCommentDto condition) {
        WrappedResponse result = new WrappedResponse();
        if (StringUtils.isBlank(token)) {
            result.setCode(HttpStateCode.INVALID_TOKEN.getValue());
            result.setMsg(HttpStateCode.INVALID_TOKEN.getReasonPhrase());
            return result;
        }
        String user = JWToken.getUuid(token);
        Nurse nurse = nurseService.findByUser(user);
        condition.setNurse(nurse.getUuid());
        IPage<Comment> commentIPage = commentService.findByNurse(condition);
        int count = commentIPage.getRecords().size();
        Comment comment = null;
        for (int i = 0; i < count; i++) {
            comment = commentIPage.getRecords().get(i);
            commentIPage.getRecords().get(i).setContents(comment.getContent().split(","));
            if (comment.getImage() != null) {
                commentIPage.getRecords().get(i).setImages(comment.getImage().split(","));
            }
        }
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        result.setSuccessObject(commentIPage);
        return result;
    }

}























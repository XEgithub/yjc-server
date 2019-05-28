package com.dk.controller.admin;

import cn.sourcespro.commons.data.vo.PageVo;
import cn.sourcespro.commons.data.vo.Result;
import cn.sourcespro.commons.data.vo.Vo;
import cn.sourcespro.commons.utils.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dk.data.dto.AddAppointmentDto;
import com.dk.data.dto.EditAppointmentDto;
import com.dk.data.dto.SearchAppointmentDto;
import com.dk.data.entity.Appointment;
import com.dk.service.AppointmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 预约 Controller
 *
 * @author ban
 * @date 2018/12/12
 */
@Controller
@RequestMapping(value = "/admin/appointment")
public class AppointmentController {

    private static final Logger logger = LoggerFactory.getLogger(AppointmentController.class);

    @Autowired
    private AppointmentService service;

    @GetMapping("v_list")
    String vlist(Model model){
        return "/admin/appointment/list";
    }

    @PostMapping("list")
    @ResponseBody
    PageVo<Appointment> list(@RequestBody SearchAppointmentDto condition){
        logger.info("搜索预约，参数：\n {}", condition.toString());
        IPage<Appointment> page = service.findAll(condition);
        return Result.page(page);
    }

    @GetMapping("v_add")
    String add(){
        return "/admin/appointment/add";
    }

    @PostMapping("add")
    @ResponseBody
    Vo add(@RequestBody @Validated AddAppointmentDto param, BindingResult result){
        logger.info("添加预约，参数：\n {}", param.toString());
        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldError();
            String errorMsg = fieldError != null ? fieldError.getDefaultMessage() : "参数异常";
            logger.error("添加预约:{}", errorMsg);
            return Result.paramError(errorMsg);
        }
        Appointment bean = new Appointment();
        BeanUtil.copyProperties(param, bean);
        Appointment repo = service.add(bean);
        return Result.ok();
    }

    @GetMapping("v_update/{id}")
    String update(@PathVariable("id") Long id, Model model){
        model.addAttribute("item", service.findById(id));
        return "/admin/appointment/edit";
    }

    @PostMapping("update")
    @ResponseBody
    Vo update(@RequestBody @Validated EditAppointmentDto param, BindingResult result){
        logger.info("更新预约，参数：\n {}", param.toString());
        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldError();
            String errorMsg = fieldError != null ? fieldError.getDefaultMessage() : "参数异常";
            logger.error("更新预约:{}", errorMsg);
            return Result.paramError(errorMsg);
        }
        Appointment bean = new Appointment();
        BeanUtil.copyProperties(param, bean);
        Appointment repo = service.update(bean);
        return Result.ok();
    }

    @GetMapping("v_detail/{id}")
    String detail(@PathVariable("id") Long id, Model model){
        model.addAttribute("item", service.findById(id));
        return "/admin/appointment/detail";
    }

    @GetMapping("delete/{id}")
    @ResponseBody
    Vo delete(@PathVariable("id") Long id){
        logger.info("删除预约，参数：id = {}", id);
        service.delete(id);
        return Result.ok();
    }

    @PostMapping("batch_delete")
    @ResponseBody
    Vo delete(@RequestBody List<Long> ids){
        logger.info("批量删除预约，参数：idList = {}", ids.toArray());
        service.batchDeleteById(ids);
        return Result.ok();
    }

}
package com.dk.controller.admin;

import cn.sourcespro.commons.data.vo.PageVo;
import cn.sourcespro.commons.data.vo.Result;
import cn.sourcespro.commons.data.vo.Vo;
import cn.sourcespro.commons.utils.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dk.data.dto.AddNurseDto;
import com.dk.data.dto.EditNurseDto;
import com.dk.data.dto.SearchNurseDto;
import com.dk.data.entity.Nurse;
import com.dk.service.NurseService;
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
 * 护士 Controller
 *
 * @author ban
 * @date 2019/01/10
 */
@Controller
@RequestMapping(value = "/admin/nurse")
public class NurseController {

    private static final Logger logger = LoggerFactory.getLogger(NurseController.class);

    @Autowired
    private NurseService service;

    @GetMapping("v_list")
    String vlist(Model model){
        return "/admin/nurse/list";
    }

    @PostMapping("list")
    @ResponseBody
    PageVo<Nurse> list(@RequestBody SearchNurseDto condition){
        logger.info("搜索护士，参数：\n {}", condition.toString());
        IPage<Nurse> page = service.findAll(condition);
        return Result.page(page);
    }

    @GetMapping("v_add")
    String add(){
        return "/admin/nurse/add";
    }

    @PostMapping("add")
    @ResponseBody
    Vo add(@RequestBody @Validated AddNurseDto param, BindingResult result){
        logger.info("添加护士，参数：\n {}", param.toString());
        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldError();
            String errorMsg = fieldError != null ? fieldError.getDefaultMessage() : "参数异常";
            logger.error("添加护士:{}", errorMsg);
            return Result.paramError(errorMsg);
        }
        Nurse bean = new Nurse();
        BeanUtil.copyProperties(param, bean);
        Nurse repo = service.add(bean);
        return Result.ok();
    }

    @GetMapping("v_update/{id}")
    String update(@PathVariable("id") Long id, Model model){
        model.addAttribute("item", service.findById(id));
        return "/admin/nurse/edit";
    }

    @PostMapping("update")
    @ResponseBody
    Vo update(@RequestBody @Validated EditNurseDto param, BindingResult result){
        logger.info("更新护士，参数：\n {}", param.toString());
        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldError();
            String errorMsg = fieldError != null ? fieldError.getDefaultMessage() : "参数异常";
            logger.error("更新护士:{}", errorMsg);
            return Result.paramError(errorMsg);
        }
        Nurse bean = new Nurse();
        BeanUtil.copyProperties(param, bean);
        Nurse repo = service.update(bean);
        return Result.ok();
    }

    @GetMapping("v_detail/{id}")
    String detail(@PathVariable("id") Long id, Model model){
        model.addAttribute("item", service.findById(id));
        return "/admin/nurse/detail";
    }

    @GetMapping("delete/{id}")
    @ResponseBody
    Vo delete(@PathVariable("id") Long id){
        logger.info("删除护士，参数：id = {}", id);
        service.delete(id);
        return Result.ok();
    }

    @PostMapping("batch_delete")
    @ResponseBody
    Vo delete(@RequestBody List<Long> ids){
        logger.info("批量删除护士，参数：idList = {}", ids.toArray());
        service.batchDeleteById(ids);
        return Result.ok();
    }

}
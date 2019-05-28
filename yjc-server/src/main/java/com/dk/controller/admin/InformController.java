package com.dk.controller.admin;

import cn.sourcespro.commons.data.vo.PageVo;
import cn.sourcespro.commons.data.vo.Result;
import cn.sourcespro.commons.data.vo.Vo;
import cn.sourcespro.commons.utils.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dk.data.dto.AddInformDto;
import com.dk.data.dto.EditInformDto;
import com.dk.data.dto.SearchInformDto;
import com.dk.data.entity.Inform;
import com.dk.service.InformService;
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
 * 微信用户 Controller
 *
 * @author ban
 * @date 2018/12/11
 */
@Controller
@RequestMapping(value = "/admin/inform")
public class InformController {

    private static final Logger logger = LoggerFactory.getLogger(InformController.class);

    @Autowired
    private InformService service;

    @GetMapping("v_list")
    String vlist(Model model){
        return "/admin/inform/list";
    }

    @PostMapping("list")
    @ResponseBody
    PageVo<Inform> list(@RequestBody SearchInformDto condition){
        logger.info("搜索微信用户，参数：\n {}", condition.toString());
        IPage<Inform> page = service.findAll(condition);
        return Result.page(page);
    }

    @GetMapping("v_add")
    String add(){
        return "/admin/inform/add";
    }

    @PostMapping("add")
    @ResponseBody
    Vo add(@RequestBody @Validated AddInformDto param, BindingResult result){
        logger.info("添加微信用户，参数：\n {}", param.toString());
        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldError();
            String errorMsg = fieldError != null ? fieldError.getDefaultMessage() : "参数异常";
            logger.error("添加微信用户:{}", errorMsg);
            return Result.paramError(errorMsg);
        }
        Inform bean = new Inform();
        BeanUtil.copyProperties(param, bean);
        Inform repo = service.add(bean);
        return Result.ok();
    }

    @GetMapping("v_update/{id}")
    String update(@PathVariable("id") Long id, Model model){
        model.addAttribute("item", service.findById(id));
        return "/admin/inform/edit";
    }

    @PostMapping("update")
    @ResponseBody
    Vo update(@RequestBody @Validated EditInformDto param, BindingResult result){
        logger.info("更新微信用户，参数：\n {}", param.toString());
        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldError();
            String errorMsg = fieldError != null ? fieldError.getDefaultMessage() : "参数异常";
            logger.error("更新微信用户:{}", errorMsg);
            return Result.paramError(errorMsg);
        }
        Inform bean = new Inform();
        BeanUtil.copyProperties(param, bean);
        Inform repo = service.update(bean);
        return Result.ok();
    }

    @GetMapping("v_detail/{id}")
    String detail(@PathVariable("id") Long id, Model model){
        model.addAttribute("item", service.findById(id));
        return "/admin/inform/detail";
    }

    @GetMapping("delete/{id}")
    @ResponseBody
    Vo delete(@PathVariable("id") Long id){
        logger.info("删除微信用户，参数：id = {}", id);
        service.delete(id);
        return Result.ok();
    }

    @PostMapping("batch_delete")
    @ResponseBody
    Vo delete(@RequestBody List<Long> ids){
        logger.info("批量删除微信用户，参数：idList = {}", ids.toArray());
        service.batchDeleteById(ids);
        return Result.ok();
    }

}
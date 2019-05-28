package com.dk.controller.admin;

import cn.sourcespro.commons.data.vo.PageVo;
import cn.sourcespro.commons.data.vo.Result;
import cn.sourcespro.commons.data.vo.Vo;
import cn.sourcespro.commons.utils.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dk.data.dto.AddConcernDto;
import com.dk.data.dto.EditConcernDto;
import com.dk.data.dto.SearchConcernDto;
import com.dk.data.entity.Concern;
import com.dk.service.ConcernService;
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
 * 关注 Controller
 *
 * @author ban
 * @date 2018/12/12
 */
@Controller
@RequestMapping(value = "/admin/concern")
public class ConcernController {

    private static final Logger logger = LoggerFactory.getLogger(ConcernController.class);

    @Autowired
    private ConcernService service;

    @GetMapping("v_list")
    String vlist(Model model){
        return "/admin/concern/list";
    }

    @PostMapping("list")
    @ResponseBody
    PageVo<Concern> list(@RequestBody SearchConcernDto condition){
        logger.info("搜索关注，参数：\n {}", condition.toString());
        IPage<Concern> page = service.findAll(condition);
        return Result.page(page);
    }

    @GetMapping("v_add")
    String add(){
        return "/admin/concern/add";
    }

    @PostMapping("add")
    @ResponseBody
    Vo add(@RequestBody @Validated AddConcernDto param, BindingResult result){
        logger.info("添加关注，参数：\n {}", param.toString());
        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldError();
            String errorMsg = fieldError != null ? fieldError.getDefaultMessage() : "参数异常";
            logger.error("添加关注:{}", errorMsg);
            return Result.paramError(errorMsg);
        }
        Concern bean = new Concern();
        BeanUtil.copyProperties(param, bean);
        Concern repo = service.add(bean);
        return Result.ok();
    }

    @GetMapping("v_update/{id}")
    String update(@PathVariable("id") Long id, Model model){
        model.addAttribute("item", service.findById(id));
        return "/admin/concern/edit";
    }

    @PostMapping("update")
    @ResponseBody
    Vo update(@RequestBody @Validated EditConcernDto param, BindingResult result){
        logger.info("更新关注，参数：\n {}", param.toString());
        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldError();
            String errorMsg = fieldError != null ? fieldError.getDefaultMessage() : "参数异常";
            logger.error("更新关注:{}", errorMsg);
            return Result.paramError(errorMsg);
        }
        Concern bean = new Concern();
        BeanUtil.copyProperties(param, bean);
        Concern repo = service.update(bean);
        return Result.ok();
    }

    @GetMapping("v_detail/{id}")
    String detail(@PathVariable("id") Long id, Model model){
        model.addAttribute("item", service.findById(id));
        return "/admin/concern/detail";
    }

    @GetMapping("delete/{id}")
    @ResponseBody
    Vo delete(@PathVariable("id") Long id){
        logger.info("删除关注，参数：id = {}", id);
        service.delete(id);
        return Result.ok();
    }

    @PostMapping("batch_delete")
    @ResponseBody
    Vo delete(@RequestBody List<Long> ids){
        logger.info("批量删除关注，参数：idList = {}", ids.toArray());
        service.batchDeleteById(ids);
        return Result.ok();
    }

}
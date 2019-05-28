package com.dk.controller.admin;

import cn.sourcespro.commons.data.vo.PageVo;
import cn.sourcespro.commons.data.vo.Result;
import cn.sourcespro.commons.data.vo.Vo;
import cn.sourcespro.commons.utils.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dk.data.dto.AddRemarkDto;
import com.dk.data.dto.EditRemarkDto;
import com.dk.data.dto.SearchRemarkDto;
import com.dk.data.entity.Remark;
import com.dk.service.RemarkService;
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
 * 订单 Controller
 *
 * @author ban
 * @date 2018/12/06
 */
@Controller
@RequestMapping(value = "/admin/remark")
public class RemarkController {

    private static final Logger logger = LoggerFactory.getLogger(RemarkController.class);

    @Autowired
    private RemarkService service;

    @GetMapping("v_list")
    String vlist(Model model){
        return "/admin/remark/list";
    }

    @PostMapping("list")
    @ResponseBody
    PageVo<Remark> list(@RequestBody SearchRemarkDto condition){
        logger.info("搜索订单，参数：\n {}", condition.toString());
        IPage<Remark> page = service.findAll(condition);
        return Result.page(page);
    }

    @GetMapping("v_add")
    String add(){
        return "/admin/remark/add";
    }

    @PostMapping("add")
    @ResponseBody
    Vo add(@RequestBody @Validated AddRemarkDto param, BindingResult result){
        logger.info("添加订单，参数：\n {}", param.toString());
        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldError();
            String errorMsg = fieldError != null ? fieldError.getDefaultMessage() : "参数异常";
            logger.error("添加订单:{}", errorMsg);
            return Result.paramError(errorMsg);
        }
        Remark bean = new Remark();
        BeanUtil.copyProperties(param, bean);
        Remark repo = service.add(bean);
        return Result.ok();
    }

    @GetMapping("v_update/{id}")
    String update(@PathVariable("id") Long id, Model model){
        model.addAttribute("item", service.findById(id));
        return "/admin/remark/edit";
    }

    @PostMapping("update")
    @ResponseBody
    Vo update(@RequestBody @Validated EditRemarkDto param, BindingResult result){
        logger.info("更新订单，参数：\n {}", param.toString());
        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldError();
            String errorMsg = fieldError != null ? fieldError.getDefaultMessage() : "参数异常";
            logger.error("更新订单:{}", errorMsg);
            return Result.paramError(errorMsg);
        }
        Remark bean = new Remark();
        BeanUtil.copyProperties(param, bean);
        Remark repo = service.update(bean);
        return Result.ok();
    }

    @GetMapping("v_detail/{id}")
    String detail(@PathVariable("id") Long id, Model model){
        model.addAttribute("item", service.findById(id));
        return "/admin/remark/detail";
    }

    @GetMapping("delete/{id}")
    @ResponseBody
    Vo delete(@PathVariable("id") Long id){
        logger.info("删除订单，参数：id = {}", id);
        service.delete(id);
        return Result.ok();
    }

    @PostMapping("batch_delete")
    @ResponseBody
    Vo delete(@RequestBody List<Long> ids){
        logger.info("批量删除订单，参数：idList = {}", ids.toArray());
        service.batchDeleteById(ids);
        return Result.ok();
    }

}
package com.dk.controller.admin;

import cn.sourcespro.commons.data.vo.PageVo;
import cn.sourcespro.commons.data.vo.Result;
import cn.sourcespro.commons.data.vo.Vo;
import cn.sourcespro.commons.utils.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dk.data.dto.AddBillDto;
import com.dk.data.dto.EditBillDto;
import com.dk.data.dto.SearchBillDto;
import com.dk.data.entity.Bill;
import com.dk.service.BillService;
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
 * 护士流水表 Controller
 *
 * @author ban
 * @date 2019/01/10
 */
@Controller
@RequestMapping(value = "/admin/bill")
public class BillController {

    private static final Logger logger = LoggerFactory.getLogger(BillController.class);

    @Autowired
    private BillService service;

    @GetMapping("v_list")
    String vlist(Model model){
        return "/admin/bill/list";
    }

    @PostMapping("list")
    @ResponseBody
    PageVo<Bill> list(@RequestBody SearchBillDto condition){
        logger.info("搜索护士流水表，参数：\n {}", condition.toString());
        IPage<Bill> page = service.findAll(condition);
        return Result.page(page);
    }

    @GetMapping("v_add")
    String add(){
        return "/admin/bill/add";
    }

    @PostMapping("add")
    @ResponseBody
    Vo add(@RequestBody @Validated AddBillDto param, BindingResult result){
        logger.info("添加护士流水表，参数：\n {}", param.toString());
        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldError();
            String errorMsg = fieldError != null ? fieldError.getDefaultMessage() : "参数异常";
            logger.error("添加护士流水表:{}", errorMsg);
            return Result.paramError(errorMsg);
        }
        Bill bean = new Bill();
        BeanUtil.copyProperties(param, bean);
        Bill repo = service.add(bean);
        return Result.ok();
    }

    @GetMapping("v_update/{id}")
    String update(@PathVariable("id") Long id, Model model){
        model.addAttribute("item", service.findById(id));
        return "/admin/bill/edit";
    }

    @PostMapping("update")
    @ResponseBody
    Vo update(@RequestBody @Validated EditBillDto param, BindingResult result){
        logger.info("更新护士流水表，参数：\n {}", param.toString());
        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldError();
            String errorMsg = fieldError != null ? fieldError.getDefaultMessage() : "参数异常";
            logger.error("更新护士流水表:{}", errorMsg);
            return Result.paramError(errorMsg);
        }
        Bill bean = new Bill();
        BeanUtil.copyProperties(param, bean);
        Bill repo = service.update(bean);
        return Result.ok();
    }

    @GetMapping("v_detail/{id}")
    String detail(@PathVariable("id") Long id, Model model){
        model.addAttribute("item", service.findById(id));
        return "/admin/bill/detail";
    }

    @GetMapping("delete/{id}")
    @ResponseBody
    Vo delete(@PathVariable("id") Long id){
        logger.info("删除护士流水表，参数：id = {}", id);
        service.delete(id);
        return Result.ok();
    }

    @PostMapping("batch_delete")
    @ResponseBody
    Vo delete(@RequestBody List<Long> ids){
        logger.info("批量删除护士流水表，参数：idList = {}", ids.toArray());
        service.batchDeleteById(ids);
        return Result.ok();
    }

}
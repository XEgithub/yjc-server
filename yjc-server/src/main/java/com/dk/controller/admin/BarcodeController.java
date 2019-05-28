package com.dk.controller.admin;

import cn.sourcespro.commons.data.vo.PageVo;
import cn.sourcespro.commons.data.vo.Result;
import cn.sourcespro.commons.data.vo.Vo;
import cn.sourcespro.commons.utils.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dk.data.dto.AddBarcodeDto;
import com.dk.data.dto.EditBarcodeDto;
import com.dk.data.dto.SearchBarcodeDto;
import com.dk.data.entity.Barcode;
import com.dk.service.BarcodeService;
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
 * 条码 Controller
 *
 * @author ban
 * @date 2019/01/10
 */
@Controller
@RequestMapping(value = "/admin/barcode")
public class BarcodeController {

    private static final Logger logger = LoggerFactory.getLogger(BarcodeController.class);

    @Autowired
    private BarcodeService service;

    @GetMapping("v_list")
    String vlist(Model model){
        return "/admin/barcode/list";
    }

    @PostMapping("list")
    @ResponseBody
    PageVo<Barcode> list(@RequestBody SearchBarcodeDto condition){
        logger.info("搜索条码，参数：\n {}", condition.toString());
        IPage<Barcode> page = service.findAll(condition);
        return Result.page(page);
    }

    @GetMapping("v_add")
    String add(){
        return "/admin/barcode/add";
    }

    @PostMapping("add")
    @ResponseBody
    Vo add(@RequestBody @Validated AddBarcodeDto param, BindingResult result){
        logger.info("添加条码，参数：\n {}", param.toString());
        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldError();
            String errorMsg = fieldError != null ? fieldError.getDefaultMessage() : "参数异常";
            logger.error("添加条码:{}", errorMsg);
            return Result.paramError(errorMsg);
        }
        Barcode bean = new Barcode();
        BeanUtil.copyProperties(param, bean);
        Barcode repo = service.add(bean);
        return Result.ok();
    }

    @GetMapping("v_update/{id}")
    String update(@PathVariable("id") Long id, Model model){
        model.addAttribute("item", service.findById(id));
        return "/admin/barcode/edit";
    }

    @PostMapping("update")
    @ResponseBody
    Vo update(@RequestBody @Validated EditBarcodeDto param, BindingResult result){
        logger.info("更新条码，参数：\n {}", param.toString());
        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldError();
            String errorMsg = fieldError != null ? fieldError.getDefaultMessage() : "参数异常";
            logger.error("更新条码:{}", errorMsg);
            return Result.paramError(errorMsg);
        }
        Barcode bean = new Barcode();
        BeanUtil.copyProperties(param, bean);
        Barcode repo = service.update(bean);
        return Result.ok();
    }

    @GetMapping("v_detail/{id}")
    String detail(@PathVariable("id") Long id, Model model){
        model.addAttribute("item", service.findById(id));
        return "/admin/barcode/detail";
    }

    @GetMapping("delete/{id}")
    @ResponseBody
    Vo delete(@PathVariable("id") Long id){
        logger.info("删除条码，参数：id = {}", id);
        service.delete(id);
        return Result.ok();
    }

    @PostMapping("batch_delete")
    @ResponseBody
    Vo delete(@RequestBody List<Long> ids){
        logger.info("批量删除条码，参数：idList = {}", ids.toArray());
        service.batchDeleteById(ids);
        return Result.ok();
    }

}
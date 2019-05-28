package com.dk.controller.admin;

import cn.sourcespro.commons.data.vo.PageVo;
import cn.sourcespro.commons.data.vo.Result;
import cn.sourcespro.commons.data.vo.Vo;
import cn.sourcespro.commons.utils.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dk.data.dto.AddWxUserExtendDto;
import com.dk.data.dto.EditWxUserExtendDto;
import com.dk.data.dto.SearchWxUserExtendDto;
import com.dk.data.entity.WxUserExtend;
import com.dk.service.WxUserExtendService;
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
 * 微信用户扩展 Controller
 *
 * @author ban
 * @date 2018/12/27
 */
@Controller
@RequestMapping(value = "/admin/wxuserextend")
public class WxUserExtendController {

    private static final Logger logger = LoggerFactory.getLogger(WxUserExtendController.class);

    @Autowired
    private WxUserExtendService service;

    @GetMapping("v_list")
    String vlist(Model model){
        return "/admin/wxuserextend/list";
    }

    @PostMapping("list")
    @ResponseBody
    PageVo<WxUserExtend> list(@RequestBody SearchWxUserExtendDto condition){
        logger.info("搜索微信用户扩展，参数：\n {}", condition.toString());
        IPage<WxUserExtend> page = service.findAll(condition);
        return Result.page(page);
    }

    @GetMapping("v_add")
    String add(){
        return "/admin/wxuserextend/add";
    }

    @PostMapping("add")
    @ResponseBody
    Vo add(@RequestBody @Validated AddWxUserExtendDto param, BindingResult result){
        logger.info("添加微信用户扩展，参数：\n {}", param.toString());
        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldError();
            String errorMsg = fieldError != null ? fieldError.getDefaultMessage() : "参数异常";
            logger.error("添加微信用户扩展:{}", errorMsg);
            return Result.paramError(errorMsg);
        }
        WxUserExtend bean = new WxUserExtend();
        BeanUtil.copyProperties(param, bean);
        WxUserExtend repo = service.add(bean);
        return Result.ok();
    }

    @GetMapping("v_update/{id}")
    String update(@PathVariable("id") Long id, Model model){
        model.addAttribute("item", service.findById(id));
        return "/admin/wxuserextend/edit";
    }

    @PostMapping("update")
    @ResponseBody
    Vo update(@RequestBody @Validated EditWxUserExtendDto param, BindingResult result){
        logger.info("更新微信用户扩展，参数：\n {}", param.toString());
        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldError();
            String errorMsg = fieldError != null ? fieldError.getDefaultMessage() : "参数异常";
            logger.error("更新微信用户扩展:{}", errorMsg);
            return Result.paramError(errorMsg);
        }
        WxUserExtend bean = new WxUserExtend();
        BeanUtil.copyProperties(param, bean);
        WxUserExtend repo = service.update(bean);
        return Result.ok();
    }

    @GetMapping("v_detail/{id}")
    String detail(@PathVariable("id") Long id, Model model){
        model.addAttribute("item", service.findById(id));
        return "/admin/wxuserextend/detail";
    }

    @GetMapping("delete/{id}")
    @ResponseBody
    Vo delete(@PathVariable("id") Long id){
        logger.info("删除微信用户扩展，参数：id = {}", id);
        service.delete(id);
        return Result.ok();
    }

    @PostMapping("batch_delete")
    @ResponseBody
    Vo delete(@RequestBody List<Long> ids){
        logger.info("批量删除微信用户扩展，参数：idList = {}", ids.toArray());
        service.batchDeleteById(ids);
        return Result.ok();
    }

}
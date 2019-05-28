package com.dk.controller.admin;

import cn.sourcespro.commons.data.vo.PageVo;
import cn.sourcespro.commons.data.vo.Result;
import cn.sourcespro.commons.data.vo.Vo;
import cn.sourcespro.commons.utils.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dk.data.dto.AddCommentTemplateDto;
import com.dk.data.dto.EditCommentTemplateDto;
import com.dk.data.dto.SearchCommentTemplateDto;
import com.dk.data.entity.CommentTemplate;
import com.dk.service.CommentTemplateService;
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
 * 评论模板 Controller
 *
 * @author ban
 * @date 2019/01/03
 */
@Controller
@RequestMapping(value = "/admin/commenttemplate")
public class CommentTemplateController {

    private static final Logger logger = LoggerFactory.getLogger(CommentTemplateController.class);

    @Autowired
    private CommentTemplateService service;

    @GetMapping("v_list")
    String vlist(Model model){
        return "/admin/commenttemplate/list";
    }

    @PostMapping("list")
    @ResponseBody
    PageVo<CommentTemplate> list(@RequestBody SearchCommentTemplateDto condition){
        logger.info("搜索评论模板，参数：\n {}", condition.toString());
        IPage<CommentTemplate> page = service.findAll(condition);
        return Result.page(page);
    }

    @GetMapping("v_add")
    String add(){
        return "/admin/commenttemplate/add";
    }

    @PostMapping("add")
    @ResponseBody
    Vo add(@RequestBody @Validated AddCommentTemplateDto param, BindingResult result){
        logger.info("添加评论模板，参数：\n {}", param.toString());
        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldError();
            String errorMsg = fieldError != null ? fieldError.getDefaultMessage() : "参数异常";
            logger.error("添加评论模板:{}", errorMsg);
            return Result.paramError(errorMsg);
        }
        CommentTemplate bean = new CommentTemplate();
        BeanUtil.copyProperties(param, bean);
        CommentTemplate repo = service.add(bean);
        return Result.ok();
    }

    @GetMapping("v_update/{id}")
    String update(@PathVariable("id") Long id, Model model){
        model.addAttribute("item", service.findById(id));
        return "/admin/commenttemplate/edit";
    }

    @PostMapping("update")
    @ResponseBody
    Vo update(@RequestBody @Validated EditCommentTemplateDto param, BindingResult result){
        logger.info("更新评论模板，参数：\n {}", param.toString());
        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldError();
            String errorMsg = fieldError != null ? fieldError.getDefaultMessage() : "参数异常";
            logger.error("更新评论模板:{}", errorMsg);
            return Result.paramError(errorMsg);
        }
        CommentTemplate bean = new CommentTemplate();
        BeanUtil.copyProperties(param, bean);
        CommentTemplate repo = service.update(bean);
        return Result.ok();
    }

    @GetMapping("v_detail/{id}")
    String detail(@PathVariable("id") Long id, Model model){
        model.addAttribute("item", service.findById(id));
        return "/admin/commenttemplate/detail";
    }

    @GetMapping("delete/{id}")
    @ResponseBody
    Vo delete(@PathVariable("id") Long id){
        logger.info("删除评论模板，参数：id = {}", id);
        service.delete(id);
        return Result.ok();
    }

    @PostMapping("batch_delete")
    @ResponseBody
    Vo delete(@RequestBody List<Long> ids){
        logger.info("批量删除评论模板，参数：idList = {}", ids.toArray());
        service.batchDeleteById(ids);
        return Result.ok();
    }

}
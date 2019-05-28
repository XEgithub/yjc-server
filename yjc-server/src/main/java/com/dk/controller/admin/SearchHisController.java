package com.dk.controller.admin;

import cn.sourcespro.commons.data.vo.PageVo;
import cn.sourcespro.commons.data.vo.Result;
import cn.sourcespro.commons.data.vo.Vo;
import cn.sourcespro.commons.utils.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dk.data.dto.AddSearchHisDto;
import com.dk.data.dto.EditSearchHisDto;
import com.dk.data.dto.SearchSearchHisDto;
import com.dk.data.entity.SearchHis;
import com.dk.service.SearchHisService;
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
 * 历史搜索 Controller
 *
 * @author ban
 * @date 2018/12/11
 */
@Controller
@RequestMapping(value = "/admin/searchhis")
public class SearchHisController {

    private static final Logger logger = LoggerFactory.getLogger(SearchHisController.class);

    @Autowired
    private SearchHisService service;

    @GetMapping("v_list")
    String vlist(Model model){
        return "/admin/searchhis/list";
    }

    @PostMapping("list")
    @ResponseBody
    PageVo<SearchHis> list(@RequestBody SearchSearchHisDto condition){
        logger.info("搜索历史搜索，参数：\n {}", condition.toString());
        IPage<SearchHis> page = service.findAll(condition);
        return Result.page(page);
    }

    @GetMapping("v_add")
    String add(){
        return "/admin/searchhis/add";
    }

    @PostMapping("add")
    @ResponseBody
    Vo add(@RequestBody @Validated AddSearchHisDto param, BindingResult result){
        logger.info("添加历史搜索，参数：\n {}", param.toString());
        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldError();
            String errorMsg = fieldError != null ? fieldError.getDefaultMessage() : "参数异常";
            logger.error("添加历史搜索:{}", errorMsg);
            return Result.paramError(errorMsg);
        }
        SearchHis bean = new SearchHis();
        BeanUtil.copyProperties(param, bean);
        SearchHis repo = service.add(bean);
        return Result.ok();
    }

    @GetMapping("v_update/{id}")
    String update(@PathVariable("id") Long id, Model model){
        model.addAttribute("item", service.findById(id));
        return "/admin/searchhis/edit";
    }

    @PostMapping("update")
    @ResponseBody
    Vo update(@RequestBody @Validated EditSearchHisDto param, BindingResult result){
        logger.info("更新历史搜索，参数：\n {}", param.toString());
        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldError();
            String errorMsg = fieldError != null ? fieldError.getDefaultMessage() : "参数异常";
            logger.error("更新历史搜索:{}", errorMsg);
            return Result.paramError(errorMsg);
        }
        SearchHis bean = new SearchHis();
        BeanUtil.copyProperties(param, bean);
        SearchHis repo = service.update(bean);
        return Result.ok();
    }

    @GetMapping("v_detail/{id}")
    String detail(@PathVariable("id") Long id, Model model){
        model.addAttribute("item", service.findById(id));
        return "/admin/searchhis/detail";
    }

    @GetMapping("delete/{id}")
    @ResponseBody
    Vo delete(@PathVariable("id") Long id){
        logger.info("删除历史搜索，参数：id = {}", id);
        service.delete(id);
        return Result.ok();
    }

    @PostMapping("batch_delete")
    @ResponseBody
    Vo delete(@RequestBody List<Long> ids){
        logger.info("批量删除历史搜索，参数：idList = {}", ids.toArray());
        service.batchDeleteById(ids);
        return Result.ok();
    }

}
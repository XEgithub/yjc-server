package com.dk.controller.admin;

import cn.sourcespro.commons.data.vo.PageVo;
import cn.sourcespro.commons.data.vo.Result;
import cn.sourcespro.commons.data.vo.Vo;
import cn.sourcespro.commons.utils.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dk.data.dto.AddSearchHotDto;
import com.dk.data.dto.EditSearchHotDto;
import com.dk.data.dto.SearchSearchHotDto;
import com.dk.data.entity.SearchHot;
import com.dk.service.SearchHotService;
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
 * 热门搜索 Controller
 *
 * @author ban
 * @date 2018/12/11
 */
@Controller
@RequestMapping(value = "/admin/searchhot")
public class SearchHotController {

    private static final Logger logger = LoggerFactory.getLogger(SearchHotController.class);

    @Autowired
    private SearchHotService service;

    @GetMapping("v_list")
    String vlist(Model model){
        return "/admin/searchhot/list";
    }

    @PostMapping("list")
    @ResponseBody
    PageVo<SearchHot> list(@RequestBody SearchSearchHotDto condition){
        logger.info("搜索热门搜索，参数：\n {}", condition.toString());
        IPage<SearchHot> page = service.findAll(condition);
        return Result.page(page);
    }

    @GetMapping("v_add")
    String add(){
        return "/admin/searchhot/add";
    }

    @PostMapping("add")
    @ResponseBody
    Vo add(@RequestBody @Validated AddSearchHotDto param, BindingResult result){
        logger.info("添加热门搜索，参数：\n {}", param.toString());
        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldError();
            String errorMsg = fieldError != null ? fieldError.getDefaultMessage() : "参数异常";
            logger.error("添加热门搜索:{}", errorMsg);
            return Result.paramError(errorMsg);
        }
        SearchHot bean = new SearchHot();
        BeanUtil.copyProperties(param, bean);
        SearchHot repo = service.add(bean);
        return Result.ok();
    }

    @GetMapping("v_update/{id}")
    String update(@PathVariable("id") Long id, Model model){
        model.addAttribute("item", service.findById(id));
        return "/admin/searchhot/edit";
    }

    @PostMapping("update")
    @ResponseBody
    Vo update(@RequestBody @Validated EditSearchHotDto param, BindingResult result){
        logger.info("更新热门搜索，参数：\n {}", param.toString());
        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldError();
            String errorMsg = fieldError != null ? fieldError.getDefaultMessage() : "参数异常";
            logger.error("更新热门搜索:{}", errorMsg);
            return Result.paramError(errorMsg);
        }
        SearchHot bean = new SearchHot();
        BeanUtil.copyProperties(param, bean);
        SearchHot repo = service.update(bean);
        return Result.ok();
    }

    @GetMapping("v_detail/{id}")
    String detail(@PathVariable("id") Long id, Model model){
        model.addAttribute("item", service.findById(id));
        return "/admin/searchhot/detail";
    }

    @GetMapping("delete/{id}")
    @ResponseBody
    Vo delete(@PathVariable("id") Long id){
        logger.info("删除热门搜索，参数：id = {}", id);
        service.delete(id);
        return Result.ok();
    }

    @PostMapping("batch_delete")
    @ResponseBody
    Vo delete(@RequestBody List<Long> ids){
        logger.info("批量删除热门搜索，参数：idList = {}", ids.toArray());
        service.batchDeleteById(ids);
        return Result.ok();
    }

}
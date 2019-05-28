package com.dk.controller.admin;

import cn.sourcespro.commons.data.vo.PageVo;
import cn.sourcespro.commons.data.vo.Result;
import cn.sourcespro.commons.data.vo.Vo;
import cn.sourcespro.commons.utils.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dk.data.dto.AddProjectGroupDto;
import com.dk.data.dto.EditProjectGroupDto;
import com.dk.data.dto.SearchProjectGroupDto;
import com.dk.data.entity.ProjectGroup;
import com.dk.service.ProjectGroupService;
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
 * 轮播图 Controller
 *
 * @author ban
 * @date 2018/12/04
 */
@Controller
@RequestMapping(value = "/admin/projectgroup")
public class ProjectGroupController {

    private static final Logger logger = LoggerFactory.getLogger(ProjectGroupController.class);

    @Autowired
    private ProjectGroupService service;

    @GetMapping("v_list")
    String vlist(Model model){
        return "/admin/projectgroup/list";
    }

    @PostMapping("list")
    @ResponseBody
    PageVo<ProjectGroup> list(@RequestBody SearchProjectGroupDto condition){
        logger.info("搜索轮播图，参数：\n {}", condition.toString());
        IPage<ProjectGroup> page = service.findAll(condition);
        return Result.page(page);
    }

    @GetMapping("v_add")
    String add(){
        return "/admin/projectgroup/add";
    }

    @PostMapping("add")
    @ResponseBody
    Vo add(@RequestBody @Validated AddProjectGroupDto param, BindingResult result){
        logger.info("添加轮播图，参数：\n {}", param.toString());
        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldError();
            String errorMsg = fieldError != null ? fieldError.getDefaultMessage() : "参数异常";
            logger.error("添加轮播图:{}", errorMsg);
            return Result.paramError(errorMsg);
        }
        ProjectGroup bean = new ProjectGroup();
        BeanUtil.copyProperties(param, bean);
        ProjectGroup repo = service.add(bean);
        return Result.ok();
    }

    @GetMapping("v_update/{id}")
    String update(@PathVariable("id") Long id, Model model){
        model.addAttribute("item", service.findById(id));
        return "/admin/projectgroup/edit";
    }

    @PostMapping("update")
    @ResponseBody
    Vo update(@RequestBody @Validated EditProjectGroupDto param, BindingResult result){
        logger.info("更新轮播图，参数：\n {}", param.toString());
        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldError();
            String errorMsg = fieldError != null ? fieldError.getDefaultMessage() : "参数异常";
            logger.error("更新轮播图:{}", errorMsg);
            return Result.paramError(errorMsg);
        }
        ProjectGroup bean = new ProjectGroup();
        BeanUtil.copyProperties(param, bean);
        ProjectGroup repo = service.update(bean);
        return Result.ok();
    }

    @GetMapping("v_detail/{id}")
    String detail(@PathVariable("id") Long id, Model model){
        model.addAttribute("item", service.findById(id));
        return "/admin/projectgroup/detail";
    }

    @GetMapping("delete/{id}")
    @ResponseBody
    Vo delete(@PathVariable("id") Long id){
        logger.info("删除轮播图，参数：id = {}", id);
        service.delete(id);
        return Result.ok();
    }

    @PostMapping("batch_delete")
    @ResponseBody
    Vo delete(@RequestBody List<Long> ids){
        logger.info("批量删除轮播图，参数：idList = {}", ids.toArray());
        service.batchDeleteById(ids);
        return Result.ok();
    }

}
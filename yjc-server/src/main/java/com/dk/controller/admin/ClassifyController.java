package com.dk.controller.admin;

import cn.sourcespro.commons.data.vo.NodeVo;
import cn.sourcespro.commons.data.vo.PageVo;
import cn.sourcespro.commons.data.vo.Result;
import cn.sourcespro.commons.data.vo.Vo;
import cn.sourcespro.commons.utils.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dk.data.dto.AddClassifyDto;
import com.dk.data.dto.EditClassifyDto;
import com.dk.data.dto.SearchClassifyDto;
import com.dk.data.entity.Classify;
import com.dk.service.ClassifyService;
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
 * @date 2018/12/10
 */
@Controller
@RequestMapping(value = "/admin/classify")
public class ClassifyController {

    private static final Logger logger = LoggerFactory.getLogger(ClassifyController.class);

    @Autowired
    private ClassifyService service;

    @GetMapping("lazy")
    @ResponseBody
    Vo lazy(Long pid){
        logger.info("懒加载查询地区，参数：\n {}", pid);
        List<Classify> list = service.findByPid(pid);
        return Result.ok(list);
    }

    @GetMapping("tree")
    @ResponseBody
    Vo tree(Long pid){
        logger.info("级联查询地区，参数：\n {}", pid);
        List<NodeVo<Long>> list = service.findNodeByPidCascaded(pid);
        return Result.ok(list);
    }

    @GetMapping("v_list")
    String vlist(Model model){
        return "/admin/classify/list";
    }

    @PostMapping("list")
    @ResponseBody
    PageVo<Classify> list(@RequestBody SearchClassifyDto condition){
        logger.info("搜索微信用户，参数：\n {}", condition.toString());
        IPage<Classify> page = service.findAll(condition);
        return Result.page(page);
    }

    @GetMapping("v_add")
    String add(){
        return "/admin/classify/add";
    }

    @PostMapping("add")
    @ResponseBody
    Vo add(@RequestBody @Validated AddClassifyDto param, BindingResult result){
        logger.info("添加微信用户，参数：\n {}", param.toString());
        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldError();
            String errorMsg = fieldError != null ? fieldError.getDefaultMessage() : "参数异常";
            logger.error("添加微信用户:{}", errorMsg);
            return Result.paramError(errorMsg);
        }
        Classify bean = new Classify();
        BeanUtil.copyProperties(param, bean);
        Classify repo = service.add(bean);
        return Result.ok(repo);
    }

    @GetMapping("v_update/{id}")
    String update(@PathVariable("id") Long id, Model model){
        Classify item = service.findById(id);
        if (item != null) {
            model.addAttribute("parent", service.findById((Long)item.getPid()));
        }
        model.addAttribute("item", item);
        return "/admin/classify/edit";
    }

    @PostMapping("update")
    @ResponseBody
    Vo update(@RequestBody @Validated EditClassifyDto param, BindingResult result){
        logger.info("更新微信用户，参数：\n {}", param.toString());
        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldError();
            String errorMsg = fieldError != null ? fieldError.getDefaultMessage() : "参数异常";
            logger.error("更新微信用户:{}", errorMsg);
            return Result.paramError(errorMsg);
        }
        Classify bean = new Classify();
        BeanUtil.copyProperties(param, bean);
        Classify repo = service.update(bean);
        return Result.ok(repo);
    }

    @GetMapping("v_detail/{id}")
    String vdetail(@PathVariable("id") Long id, Model model){
        model.addAttribute("item", service.findById(id));
        return "/admin/classify/detail";
    }

    @GetMapping("detail/{id}")
    @ResponseBody
    Vo detail(@PathVariable("id") Long id){
        return Result.ok(service.findById(id));
    }

    @GetMapping("delete/{id}")
    String delete(@PathVariable("id") Long id, int page){
        logger.info("删除微信用户，参数：id = {}", id);
        service.delete(id);
        return "redirect:/admin/classify/list?page="+page;
    }

    @PostMapping("batch_delete")
    Vo delete(@RequestBody List<Long> ids){
        logger.info("批量删除微信用户，参数：idList = {}", ids.toArray());
        service.batchDeleteById(ids);
        return Result.ok();
    }

}
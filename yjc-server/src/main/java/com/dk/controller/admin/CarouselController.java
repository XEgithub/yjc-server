package com.dk.controller.admin;

import cn.sourcespro.commons.data.vo.PageVo;
import cn.sourcespro.commons.data.vo.Result;
import cn.sourcespro.commons.data.vo.Vo;
import cn.sourcespro.commons.utils.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dk.data.dto.AddCarouselDto;
import com.dk.data.dto.EditCarouselDto;
import com.dk.data.dto.SearchCarouselDto;
import com.dk.data.entity.Carousel;
import com.dk.service.CarouselService;
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
@RequestMapping(value = "/admin/carousel")
public class CarouselController {

    private static final Logger logger = LoggerFactory.getLogger(CarouselController.class);

    @Autowired
    private CarouselService service;

    @GetMapping("v_list")
    String vlist(Model model){
        return "/admin/carousel/list";
    }

    @PostMapping("list")
    @ResponseBody
    PageVo<Carousel> list(@RequestBody SearchCarouselDto condition){
        logger.info("搜索轮播图，参数：\n {}", condition.toString());
        IPage<Carousel> page = service.findAll(condition);
        return Result.page(page);
    }

    @GetMapping("v_add")
    String add(){
        return "/admin/carousel/add";
    }

    @PostMapping("add")
    @ResponseBody
    Vo add(@RequestBody @Validated AddCarouselDto param, BindingResult result){
        logger.info("添加轮播图，参数：\n {}", param.toString());
        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldError();
            String errorMsg = fieldError != null ? fieldError.getDefaultMessage() : "参数异常";
            logger.error("添加轮播图:{}", errorMsg);
            return Result.paramError(errorMsg);
        }
        Carousel bean = new Carousel();
        BeanUtil.copyProperties(param, bean);
        Carousel repo = service.add(bean);
        return Result.ok();
    }

    @GetMapping("v_update/{id}")
    String update(@PathVariable("id") Long id, Model model){
        model.addAttribute("item", service.findById(id));
        return "/admin/carousel/edit";
    }

    @PostMapping("update")
    @ResponseBody
    Vo update(@RequestBody @Validated EditCarouselDto param, BindingResult result){
        logger.info("更新轮播图，参数：\n {}", param.toString());
        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldError();
            String errorMsg = fieldError != null ? fieldError.getDefaultMessage() : "参数异常";
            logger.error("更新轮播图:{}", errorMsg);
            return Result.paramError(errorMsg);
        }
        Carousel bean = new Carousel();
        BeanUtil.copyProperties(param, bean);
        Carousel repo = service.update(bean);
        return Result.ok();
    }

    @GetMapping("v_detail/{id}")
    String detail(@PathVariable("id") Long id, Model model){
        model.addAttribute("item", service.findById(id));
        return "/admin/carousel/detail";
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
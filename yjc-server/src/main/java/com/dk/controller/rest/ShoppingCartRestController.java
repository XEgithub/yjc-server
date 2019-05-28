package com.dk.controller.rest;

import cn.sourcespro.commons.utils.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dk.config.Constant;
import com.dk.data.dto.AddShoppingCartDto;
import com.dk.data.dto.SearchShoppingCartDto;
import com.dk.data.entity.Project;
import com.dk.data.entity.ProjectGroup;
import com.dk.data.entity.ShoppingCart;
import com.dk.response.HttpStateCode;
import com.dk.response.WrappedResponse;
import com.dk.service.ProjectGroupService;
import com.dk.service.ProjectService;
import com.dk.service.ShoppingCartService;
import com.dk.shiro.JWToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ShoppingCartRestController
 * @Description
 * @Author zyf
 * @Date 2018/12/5 17:31
 **/
@RestController
@RequestMapping("rest/shoppingCart")
public class ShoppingCartRestController {

    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    ProjectService projectService;

    @Autowired
    ProjectGroupService projectGroupService;

    /*
     * @Description 添加购物车
     * @Date 2018/12/5
     * @Param
     * @return
     * @Author zhy
     **/
    @PostMapping("add")
    public WrappedResponse add(@CookieValue(name = Constant.WX_TOKEN, required = false) String token,
                               @Validated @RequestBody AddShoppingCartDto param, BindingResult paramResult) {
        WrappedResponse result = new WrappedResponse();
        if (paramResult.hasErrors()) {
            result.setCode(HttpStateCode.PARAMETERS_NULL.getValue());
            result.setMsg(HttpStateCode.PARAMETERS_NULL.getReasonPhrase());
            return result;
        }
        if (StringUtils.isBlank(token)) {
            result.setCode(HttpStateCode.INVALID_TOKEN.getValue());
            result.setMsg(HttpStateCode.INVALID_TOKEN.getReasonPhrase());
            return result;
        }
        String user = JWToken.getUuid(token);
        param.setUser(user);
        ShoppingCart shop = shoppingCartService.findByUserAndProjectUuid(user, param.getProject());
        ShoppingCart shoppingCart = null;
        if (shop == null) {
            ShoppingCart bean = new ShoppingCart();
            BeanUtil.copyProperties(param, bean);
            shoppingCart = shoppingCartService.add(bean);
        } else {
            if (!shop.getStatus()) {
                result.setCode(HttpStateCode.ALREADY_REPORTED.getValue());
                result.setMsg(HttpStateCode.ALREADY_REPORTED.getReasonPhrase());
                return result;
            }
            shop.setStatus(false);
            shoppingCart = shoppingCartService.update(shop);
        }
        if (shoppingCart == null) {
            result.setCode(HttpStateCode.SAVE_FAILURE.getValue());
            result.setMsg(HttpStateCode.SAVE_FAILURE.getReasonPhrase());
            return result;
        }
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        result.setSuccessObject(shoppingCart);
        return result;
    }

    /*
     * @Description 购物车列表
     * @Date 2018/12/11
     * @Param
     * @return
     * @Author zhy
     **/
    @GetMapping("list")
    public WrappedResponse list(@CookieValue(name = Constant.WX_TOKEN, required = false) String token,
                                SearchShoppingCartDto condition) {
        WrappedResponse result = new WrappedResponse();
        if (StringUtils.isBlank(token)) {
            result.setCode(HttpStateCode.INVALID_TOKEN.getValue());
            result.setMsg(HttpStateCode.INVALID_TOKEN.getReasonPhrase());
            return result;
        }
        String user = JWToken.getUuid(token);
        condition.setUser(user);
        IPage<ShoppingCart> shoppingCartIPage = shoppingCartService.findAll(condition);
        List<ShoppingCart> shoppingCartList = shoppingCartIPage.getRecords();
        List<Object> projectList = new ArrayList<>();
        Project project = null;
        ProjectGroup projectGroup = null;
        for (ShoppingCart shoppingCart : shoppingCartList) {
            if ("0".equals(shoppingCart.getProjectType())) {
                project = projectService.findByUuid(shoppingCart.getProject());
                projectList.add(project);
            } else if ("1".equals(shoppingCart.getProjectType())) {
                projectGroup = projectGroupService.findByUuid(shoppingCart.getProject());
                projectList.add(projectGroup);
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("projectList", projectList);
        map.put("total", shoppingCartIPage.getTotal());
        map.put("size", shoppingCartIPage.getSize());
        map.put("current", shoppingCartIPage.getCurrent());
        map.put("pages", shoppingCartIPage.getPages());
        if (projectList == null) {
            result.setCode(HttpStateCode.NO_CONTENT.getValue());
            result.setMsg(HttpStateCode.NO_CONTENT.getReasonPhrase());
            return result;
        }
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        result.setSuccessObject(map);
        return result;
    }

    /*
     * @Description 购物车删除
     * @Date 2018/12/11
     * @Param
     * @return
     * @Author zhy
     **/
    @GetMapping("delete")
    public WrappedResponse delete(@CookieValue(name = Constant.WX_TOKEN, required = false) String token,
                                  @RequestParam(value = "uuid") String uuid) {
        WrappedResponse result = new WrappedResponse();
        if ("".equals(uuid)) {
            result.setCode(HttpStateCode.PARAMETERS_NULL.getValue());
            result.setMsg(HttpStateCode.PARAMETERS_NULL.getReasonPhrase());
            return result;
        }
        if (StringUtils.isBlank(token)) {
            result.setCode(HttpStateCode.INVALID_TOKEN.getValue());
            result.setMsg(HttpStateCode.INVALID_TOKEN.getReasonPhrase());
            return result;
        }
        String user = JWToken.getUuid(token);
        ShoppingCart shoppingCart = shoppingCartService.findByUserAndProjectUuid(user, uuid);
        if (shoppingCart == null) {
            result.setCode(HttpStateCode.OPERATION_FAILURE.getValue());
            result.setMsg(HttpStateCode.OPERATION_FAILURE.getReasonPhrase());
            return result;
        }
        int flag = shoppingCartService.deleteByUuid(shoppingCart.getUuid());
        if (flag != 1) {
            result.setCode(HttpStateCode.DELETE_FAILURE.getValue());
            result.setMsg(HttpStateCode.DELETE_FAILURE.getReasonPhrase());
            return result;
        }
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        result.setSuccessObject(shoppingCart);
        return result;
    }

    /*
     * @Description 返回购物车数量
     * @Date 2018/12/12
     * @Param
     * @return
     * @Author zhy
     **/
    @GetMapping("total")
    public WrappedResponse total(@CookieValue(name = Constant.WX_TOKEN, required = false) String token,
                                 SearchShoppingCartDto condition) {
        WrappedResponse result = new WrappedResponse();
        if (StringUtils.isBlank(token)) {
            result.setCode(HttpStateCode.INVALID_TOKEN.getValue());
            result.setMsg(HttpStateCode.INVALID_TOKEN.getReasonPhrase());
            return result;
        }
        String user = JWToken.getUuid(token);
        condition.setUser(user);
        condition.setStatus(false);
        int count = shoppingCartService.count(condition);
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        result.setSuccessObject(count);
        return result;
    }

}

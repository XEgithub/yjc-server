package com.dk.controller.rest;

import cn.sourcespro.commons.utils.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dk.config.Constant;
import com.dk.data.dto.AddCommentDto;
import com.dk.data.dto.SearchCommentDto;
import com.dk.data.dto.SearchCommentTemplateDto;
import com.dk.data.dto.SearchOrderDto;
import com.dk.data.entity.*;
import com.dk.response.HttpStateCode;
import com.dk.response.WrappedResponse;
import com.dk.service.*;
import com.dk.shiro.JWToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.*;

/**
 * @ClassName CommentRestController
 * @Description
 * @Author zyf
 * @Date 2018/12/12 17:41
 **/
@RestController
@RequestMapping("rest/comment")
public class CommentRestController {

    @Autowired
    CommentService commentService;

    @Autowired
    OrderService orderService;

    @Autowired
    ProjectService projectService;

    @Autowired
    ProjectGroupService projectGroupService;
    
    @Autowired
    CommentTemplateService commentTemplateService;

    @Autowired
    AppointmentService appointmentService;

    /*
     * @Description 新增评论
     * @Date 2018/12/12
     * @Param
     * @return
     * @Author zhy
     **/
    @PostMapping("add")
    public WrappedResponse add(@CookieValue(name = Constant.WX_TOKEN, required = false) String token,
                               @Validated @RequestBody AddCommentDto param, BindingResult paramResult) {
        WrappedResponse result = new WrappedResponse();
        if (paramResult.hasErrors()) {
            result.setCode(HttpStateCode.PARAMETERS_NULL.getValue());
            result.setMsg(HttpStateCode.PARAMETERS_NULL.getReasonPhrase());
            return result;
        }
        Order order = orderService.findByUuid(param.getOrderList());
        order.setOrderStatus("5");
        orderService.update(order);
        Comment bean = new Comment();
        Comment comment = null;
        BeanUtil.copyProperties(param, bean);
        if (StringUtils.isBlank(token)) {
            result.setCode(HttpStateCode.INVALID_TOKEN.getValue());
            result.setMsg(HttpStateCode.INVALID_TOKEN.getReasonPhrase());
            return result;
        }
        String user = JWToken.getUuid(token);
        bean.setUser(user);
        bean.setPatient(order.getPatient());
        String[] projectsArray = order.getProject().split(",");
        for (int i = 0; i < projectsArray.length; i++) {
            String[] projectArray = projectsArray[i].split("-");
            bean.setProject(projectArray[0]);
            bean.setType(projectArray[1]);
            comment = commentService.add(bean);
        }
        if (comment == null) {
            result.setCode(HttpStateCode.SAVE_FAILURE.getValue());
            result.setMsg(HttpStateCode.SAVE_FAILURE.getReasonPhrase());
            return result;
        }
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        result.setSuccessObject(comment);
        return result;
    }

    /*
     * @Description 根据项目获取评论
     * @Date 2018/12/13
     * @Param
     * @return
     * @Author zhy
     **/
    @GetMapping("projectComment")
    public WrappedResponse getCommentByProject(SearchCommentDto condition) {
        WrappedResponse result = new WrappedResponse();
        if (condition == null || condition.getProject() == null || "".equals(condition.getProject())) {
            result.setCode(HttpStateCode.PARAMETERS_NULL.getValue());
            result.setMsg(HttpStateCode.PARAMETERS_NULL.getReasonPhrase());
            return result;
        }
        IPage<Comment> commentIPage = commentService.getAll(condition);
        if (commentIPage.getRecords().size() == 0) {
            result.setCode(HttpStateCode.NO_CONTENT.getValue());
            result.setMsg(HttpStateCode.NO_CONTENT.getReasonPhrase());
            return result;
        }
        for (int i = 0; i < commentIPage.getRecords().size(); i++) {
            Comment comment = commentIPage.getRecords().get(i);
            if (comment.getImage() != null) {
                String[] images = comment.getImage().split(",");
                comment.setImages(images);
            }
            comment.setContents(comment.getContent().split(","));
        }
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        result.setSuccessObject(commentIPage);
        return result;
    }

    /*
     * @Description 用户评价列表
     * @Date 2018/12/24
     * @Param
     * @return
     * @Author zhy
     **/
    @GetMapping("list")
    public WrappedResponse list(@CookieValue(name = Constant.WX_TOKEN, required = false) String token,
                                SearchOrderDto condition) {
        WrappedResponse result = new WrappedResponse();
        if (StringUtils.isBlank(token)) {
            result.setCode(HttpStateCode.INVALID_TOKEN.getValue());
            result.setMsg(HttpStateCode.INVALID_TOKEN.getReasonPhrase());
            return result;
        }
        String user = JWToken.getUuid(token);
        Map<String, Object> resultMap = new HashMap<>();
        List<Object> records = new ArrayList<>();
        Map<String, Object> map = null;
        List<Object> projectList = null;
        condition.setUser(user);
        IPage<Order> orderIPage = orderService.getAll(condition);
        List<Order> orderList = orderIPage.getRecords();
        for (Order order : orderList) {
            map = new HashMap<>();
            map.put("order", order);
            String[] projectsArray = order.getProject().split(",");
            projectList = new ArrayList<>();
            for (int i = 0; i <projectsArray.length; i++) {
                String[] projectArray = projectsArray[i].split("-");
                if ("0".equals(projectArray[1])) {
                    Project project = projectService.findByUuid(projectArray[0]);
                    projectList.add(project);
                } else {
                    ProjectGroup projectGroup = projectGroupService.findByUuid(projectArray[0]);
                    projectList.add(projectGroup);
                }
            }
            map.put("projectList", projectList);
            Comment comment = commentService.findByUserAndOrder(user, order.getUuid());
            if (comment != null && comment.getImage() != null) {
                String[] images = comment.getImage().split(",");
                comment.setImages(images);
            }
            map.put("comment", comment);
            records.add(map);
        }
        resultMap.put("records", records);
        resultMap.put("total", orderIPage.getTotal());
        resultMap.put("size", orderIPage.getSize());
        resultMap.put("current", orderIPage.getCurrent());
        resultMap.put("pages", orderIPage.getPages());
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        result.setSuccessObject(resultMap);
        return result;
    }

    /*
     * @Description 评价删除
     * @Date 2018/12/24
     * @Param
     * @return
     * @Author zhy
     **/
    @GetMapping("delete")
    public WrappedResponse delete(@CookieValue(name = Constant.WX_TOKEN, required = false) String token,
                                  @RequestParam(value = "uuid" ,required = true) String uuid) {
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
        int flag = commentService.deleteByUserAndOrder(user, uuid);
        if (flag == 0) {
            result.setCode(HttpStateCode.OPERATION_FAILURE.getValue());
            result.setMsg(HttpStateCode.OPERATION_FAILURE.getReasonPhrase());
            return result;
        }
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        return result;
    }

    /*
     * @Description 删除图片
     * @Date 2019/1/3
     * @Param
     * @return
     * @Author zhy
     **/
    @GetMapping("img/delete")
    public WrappedResponse delete(@RequestParam(value = "route", required = true) String route) {
        WrappedResponse result = new WrappedResponse();
        if (route == null || "".equals(route)) {
            result.setCode(HttpStateCode.PARAMETERS_NULL.getValue());
            result.setMsg(HttpStateCode.PARAMETERS_NULL.getReasonPhrase());
            return result;
        }
        Boolean flag = commentService.deleteImage(route);
        if (!flag) {
            result.setCode(HttpStateCode.DELETE_FAILURE.getValue());
            result.setMsg(HttpStateCode.DELETE_FAILURE.getReasonPhrase());
            return result;
        }
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        return result;
    }

    /*
     * @Description 评论留言选项列表
     * @Date 2019/1/3
     * @Param 
     * @return 
     * @Author zhy
     **/
    @GetMapping("option")
    public WrappedResponse option() {
        WrappedResponse result = new WrappedResponse();
        SearchCommentTemplateDto condition = new SearchCommentTemplateDto();
        IPage<CommentTemplate> commentTemplateIPage = commentTemplateService.findAll(condition);
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        result.setSuccessObject(commentTemplateIPage);
        return result;
    }

}

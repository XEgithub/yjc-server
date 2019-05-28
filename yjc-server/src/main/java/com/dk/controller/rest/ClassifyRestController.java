package com.dk.controller.rest;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dk.config.Constant;
import com.dk.data.dto.*;
import com.dk.data.entity.*;
import com.dk.response.HttpStateCode;
import com.dk.response.WrappedResponse;
import com.dk.service.*;
import com.dk.shiro.JWToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * @ClassName ClassifyController
 * @Description
 * @Author zyf
 * @Date 2018/12/4 14:14
 **/
@RestController
@RequestMapping(value = "rest")
public class ClassifyRestController {

    @Autowired
    ClassifyHomeService classifyHomeService;

    @Autowired
    ProjectsHomeService projectsHomeService;

    @Autowired
    ProjectService projectService;

    @Autowired
    ProjectGroupService projectGroupService;

    @Autowired
    HospitalService hospitalService;

    @Autowired
    ClassifyService classifyService;

    @Autowired
    ProjectsClassifyService projectsClassifyService;

    @Autowired
    SearchHotService searchHotService;

    @Autowired
    SearchHisService searchHisService;

    @Autowired
    WxUserService wxUserService;

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    CommentService commentService;

    @Autowired
    WxUserExtendService wxUserExtendService;

    @Autowired
    CouponService couponService;

    /*
     * @Description 首页列表展示
     * @Date 2018/12/4
     * @Param
     * @return
     * @Author zhy
     **/
    @RequestMapping(value = "/index/list", method = RequestMethod.GET)
    public WrappedResponse home(SearchClassifyHomeDto condition) {
        WrappedResponse result = new WrappedResponse();
        List<Object> resultList = new ArrayList<>();
        condition.setLevel(1);
        condition.setField("id");
        condition.setOrder("ASC");
        List<ClassifyHome> classifyHomeList = classifyHomeService.findAll(condition).getRecords();
        SearchProjectsHomeDto projectsHomeDto = new SearchProjectsHomeDto();
        int flag = 3;
        Map<Object, Object> map = null;
        List<Object> projectList = null;
        for (int i = 0; i < classifyHomeList.size(); i++) {
            map = new HashMap<Object, Object>();
            ClassifyHome classifyHome = classifyHomeList.get(i);
            map.put("classifyHome", classifyHome);
            projectsHomeDto.setClassify(classifyHome.getId().toString());
            projectsHomeDto.setPageSize(flag);
            List<ProjectsHome> projectsHomeList = projectsHomeService.findAll(projectsHomeDto).getRecords();
            projectList = new ArrayList<>();
            for (int j = 0; j < projectsHomeList.size(); j++) {
                ProjectsHome projectsHome = projectsHomeList.get(j);
                if ("project".equals(projectsHome.getType())) {
                    Project project = projectService.findByUuid(projectsHome.getProject());
                    projectList.add(project);
                } else if ("group".equals(projectsHome.getType())) {
                    ProjectGroup projectGroup = projectGroupService.findByUuid(projectsHome.getProject());
                    projectList.add(projectGroup);
                }
            }
            map.put("projectList", projectList);
            resultList.add(map);
            if (flag == 3) {
                flag = 4;
            } else {
                flag = 3;
            }
        }
        result.setSuccessObject(resultList);
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        return result;
    }

    /*
     * @Description 首页更多
     * @Date 2018/12/4
     * @Param
     * @return
     * @Author zhy
     **/
    @RequestMapping(value = "/index/more", method = RequestMethod.GET)
    public WrappedResponse more(SearchProjectsHomeDto projectsHomeDto) {
        WrappedResponse result = new WrappedResponse();
        if (projectsHomeDto == null || projectsHomeDto.getClassify() == null || projectsHomeDto.getClassify() == "") {
            result.setCode(HttpStateCode.PARAMETERS_NULL.getValue());
            result.setMsg(HttpStateCode.PARAMETERS_NULL.getReasonPhrase());
            return result;
        }
        if ("0".equals(projectsHomeDto.getType())) {
            IPage<Project> projectIPage = projectsHomeService.findByClassify(projectsHomeDto);
            if (ObjectUtils.isEmpty(projectIPage.getRecords())) {
                result.setCode(HttpStateCode.NO_CONTENT.getValue());
                result.setMsg(HttpStateCode.NO_CONTENT.getReasonPhrase());
                return result;
            }
            result.setSuccessObject(projectIPage);
        }else {
            IPage<ProjectGroup> projectGroupIPage = projectsHomeService.findByClassifyGroup(projectsHomeDto);
            if (ObjectUtils.isEmpty(projectGroupIPage.getRecords())) {
                result.setCode(HttpStateCode.NO_CONTENT.getValue());
                result.setMsg(HttpStateCode.NO_CONTENT.getReasonPhrase());
                return result;
            }
            result.setSuccessObject(projectGroupIPage);
        }
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        return result;
    }

    /*
     * @Description 搜索关键字自动补全
     * @Date 2018/12/11
     * @Param
     * @return
     * @Author zhy
     **/
    @GetMapping("search/autoComplete")
    public WrappedResponse getProjectByKeyword(@RequestParam(value = "keyWord", required = true) String keyWord) {
        WrappedResponse result = new WrappedResponse();
        if ("".equals(keyWord)) {
            result.setCode(HttpStateCode.PARAMETERS_NULL.getValue());
            result.setMsg(HttpStateCode.PARAMETERS_NULL.getReasonPhrase());
            return result;
        }
        List<String> keyWordList = new ArrayList<>();
        List<Hospital> hospitalList = hospitalService.findByLikeName(keyWord);
        if (hospitalList != null) {
            for (Hospital hospital : hospitalList) {
                keyWordList.add(hospital.getName());
            }
        }
        List<Project> projectList = projectService.findByLikeName(keyWord);
        if (projectList != null) {
            for (Project project : projectList) {
                keyWordList.add(project.getName());
            }
        }
        List<ProjectGroup> projectGroupList = projectGroupService.findByLikeName(keyWord);
        if (projectGroupList != null) {
            for (ProjectGroup projectGroup : projectGroupList) {
                keyWordList.add(projectGroup.getName());
            }
        }
        if (keyWordList == null) {
            result.setCode(HttpStateCode.PARAMETERS_NULL.getValue());
            result.setMsg(HttpStateCode.PARAMETERS_NULL.getReasonPhrase());
        }
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        result.setSuccessObject(keyWordList);
        return result;
    }

    /*
     * @Description 搜索
     * @Date 2018/12/11
     * @Param 
     * @return 
     * @Author zhy
     **/
    @GetMapping("search")
    public WrappedResponse getProjectByKeyword(@CookieValue(name = Constant.WX_TOKEN, required = false) String token,
                                               SearchProjectDto condition) {
        WrappedResponse result = new WrappedResponse();
        if (condition == null || condition.getKeyword() == null || condition.getKeyword() == "") {
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
        int flag = searchHisService.addHis(user, condition.getKeyword());
        if (flag == 0) {
            result.setCode(HttpStateCode.INTERNAL_SERVER_ERROR.getValue());
            result.setMsg(HttpStateCode.INTERNAL_SERVER_ERROR.getReasonPhrase());
            return result;
        }
        IPage<Project> projectIPage = null;
        IPage<ProjectGroup> projectGroupIPage = null;
        List<Hospital> hospitalList = hospitalService.findByLikeName(condition.getKeyword());
        if (hospitalList.size() != 0 && condition.getHospital() == null) {
            Hospital hospital = hospitalList.get(0);
            condition.setKeyword("");
            condition.setHospital(hospital.getUuid());
            addSearchHot(hospital.getName());
            if ("0".equals(condition.getType())) {
                projectIPage = projectService.findByCondition(condition);
                if (projectIPage.getRecords().size() != 0) {
                    result.setSuccessObject(projectIPage);
                }
            } else {
                projectGroupIPage = projectGroupService.findByCondition(condition);
                if (projectGroupIPage.getRecords().size() != 0) {
                    result.setSuccessObject(projectGroupIPage);
                }
            }
        } else {
            if ("0".equals(condition.getType())) {
                projectIPage = projectService.findByCondition(condition);
                if (projectIPage.getRecords().size() != 0) {
                    addSearchHot(projectIPage.getRecords().get(0).getName());
                    result.setSuccessObject(projectIPage);
                }
            } else {
                projectGroupIPage = projectGroupService.findByCondition(condition);
                if (projectGroupIPage.getRecords().size() != 0) {
                    addSearchHot(projectGroupIPage.getRecords().get(0).getName());
                    result.setSuccessObject(projectGroupIPage);
                }
            }
        }
        if (result.getSuccessObject() == null) {
            result.setCode(HttpStateCode.NO_CONTENT.getValue());
            result.setMsg(HttpStateCode.NO_CONTENT.getReasonPhrase());
            return result;
        }
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        return result;
    }

    /*
     * @Description 分类列表
     * @Date 2018/12/10
     * @Param
     * @return
     * @Author zhy
     **/
    @RequestMapping(value = "/classify/list", method = RequestMethod.GET)
    public WrappedResponse classify() {
        WrappedResponse result = new WrappedResponse();
        List<Classify> classifyList = classifyService.findByLevel(1);
        if (classifyList.isEmpty()) {
            result.setCode(HttpStateCode.NO_CONTENT.getValue());
            result.setMsg(HttpStateCode.NO_CONTENT.getReasonPhrase());
            return result;
        }
        Map<String, List<Classify>> resultMap = new HashMap<>();
        for (Classify classify : classifyList) {
            resultMap.put(classify.getName(), classifyService.findByPid(classify.getId()));
        }
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        result.setSuccessObject(resultMap);
        return result;
    }

    /*
     * @Description 分类列表更多
     * @Date 2018/12/10
     * @Param
     * @return
     * @Author zhy
     **/
    @GetMapping("classify/projects")
    public WrappedResponse classifyMore(SearchProjectsClassifyDto ProjectsClassifyDto) {
        WrappedResponse result = new WrappedResponse();
        if (ProjectsClassifyDto == null || ProjectsClassifyDto.getClassify() == null || ProjectsClassifyDto.getClassify() == "") {
            result.setCode(HttpStateCode.PARAMETERS_NULL.getValue());
            result.setMsg(HttpStateCode.PARAMETERS_NULL.getReasonPhrase());
            return result;
        }
        if ("0".equals(ProjectsClassifyDto.getType())) {
            IPage<Project> projectIPage = projectsClassifyService.findByClassify(ProjectsClassifyDto);
            if (ObjectUtils.isEmpty(projectIPage.getRecords())) {
                result.setCode(HttpStateCode.NO_CONTENT.getValue());
                result.setMsg(HttpStateCode.NO_CONTENT.getReasonPhrase());
                return result;
            }
            result.setSuccessObject(projectIPage);
        }else {
            IPage<ProjectGroup> projectGroupIPage = projectsClassifyService.findByClassifyGroup(ProjectsClassifyDto);
            if (ObjectUtils.isEmpty(projectGroupIPage.getRecords())) {
                result.setCode(HttpStateCode.NO_CONTENT.getValue());
                result.setMsg(HttpStateCode.NO_CONTENT.getReasonPhrase());
                return result;
            }
            result.setSuccessObject(projectGroupIPage);
        }
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        return result;
    }

    /*
     * @Description 添加热搜
     * @Date 2018/12/12
     * @Param
     * @return
     * @Author zhy
     **/
    public void addSearchHot(String SearchName) {
        List<SearchHot> searchHotList = searchHotService.findByLikeSearchName(SearchName);
        if (!searchHotList.isEmpty()) {
            SearchHot searchHot = searchHotList.get(0);
            Long count = searchHot.getTime();
            count += 1;
            searchHot.setTime(count);
            searchHotService.update(searchHot);
        } else {
            SearchHot searchHot = new SearchHot();
            searchHot.setSearchName(SearchName);
            searchHotService.add(searchHot);
        }
    }

    /*
     * @Description 返回用户个人信息, 预约数, 评价条数, 优惠券数
     * @Date 2018/12/27
     * @Param
     * @return
     * @Author zhy
     **/
    @GetMapping("person")
    public WrappedResponse person(@CookieValue(name = Constant.WX_TOKEN, required = false) String token) {
        WrappedResponse result = new WrappedResponse();
        if (StringUtils.isBlank(token)) {
            result.setCode(HttpStateCode.INVALID_TOKEN.getValue());
            result.setMsg(HttpStateCode.INVALID_TOKEN.getReasonPhrase());
            return result;
        }
        String wxUserUuid = JWToken.getUuid(token);
        Map<String, Object> resultMap = new HashMap<>();
        WxUser wxUser = wxUserService.getByUuid(wxUserUuid);
        resultMap.put("user", wxUser);
        SearchAppointmentDto appointmentDto = new SearchAppointmentDto();
        appointmentDto.setUser(wxUserUuid);
        int appointmentCount = appointmentService.count(appointmentDto);
        resultMap.put("appointmentCount", appointmentCount);
        int commentCount = commentService.countByUser(wxUserUuid);
        resultMap.put("commentCount", commentCount);
        SearchCouponDto couponDto = new SearchCouponDto();
        couponDto.setUser(wxUserUuid);
        couponDto.setStatus(Byte.valueOf("0"));
        int couponCount = couponService.count(couponDto);
        resultMap.put("couponCount", couponCount);
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        result.setSuccessObject(resultMap);
        return result;
    }

    /*
     * @Description 用户个人积分页面
     * @Date 2019/1/2
     * @Param 
     * @return 
     * @Author zhy
     **/
    @GetMapping("integral")
    public WrappedResponse integral(@CookieValue(name = Constant.WX_TOKEN, required = false) String token,
                                    SearchCouponDto condition) {
        WrappedResponse result = new WrappedResponse();
        if (StringUtils.isBlank(token)) {
            result.setCode(HttpStateCode.INVALID_TOKEN.getValue());
            result.setMsg(HttpStateCode.INVALID_TOKEN.getReasonPhrase());
            return result;
        }
        String wxUserUuid = JWToken.getUuid(token);
        Map<String, Object> resultMap = new HashMap<>();
        WxUser wxUser = wxUserService.getByUuid(wxUserUuid);
        resultMap.put("integral", wxUser.getIntegral());
        condition.setType(true);
        IPage<Coupon> couponIPage = couponService.findAll(condition);
        resultMap.put("couponIPage", couponIPage);
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        result.setSuccessObject(resultMap);
        return result;
    }

    /*
     * @Description 上传图片接口
     * @Date 2019/1/3
     * @Param
     * @return
     * @Author zhy
     **/
    @PostMapping("img/upload/{route}")
    public WrappedResponse upload(@PathVariable("route") String route,
                                  @RequestParam(value = "imgFiles[0]", required = true) MultipartFile image,
                                  @RequestParam(value = "imgFiles[1]", required = false) MultipartFile image1,
                                  @RequestParam(value = "imgFiles[2]", required = false) MultipartFile image2) {
        WrappedResponse result = new WrappedResponse();
        if (image == null) {
            result.setCode(HttpStateCode.PARAMETERS_NULL.getValue());
            result.setMsg(HttpStateCode.PARAMETERS_NULL.getReasonPhrase());
            return result;
        }
        MultipartFile[] imageFiles = {image, image1, image2};
        String imageUrl = commentService.addImage(route, imageFiles);
        if (StringUtils.isBlank(imageUrl)) {
            result.setCode(HttpStateCode.SAVE_FAILURE.getValue());
            result.setMsg(HttpStateCode.SAVE_FAILURE.getReasonPhrase());
        }
        String[] imageUrls = imageUrl.split(",");
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        result.setSuccessObject(imageUrls);
        return result;
    }


}

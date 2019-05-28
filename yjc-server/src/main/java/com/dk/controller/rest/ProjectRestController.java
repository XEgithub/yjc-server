package com.dk.controller.rest;

import com.dk.config.Constant;
import com.dk.data.dto.ProjectDto;
import com.dk.data.entity.Concern;
import com.dk.data.entity.Project;
import com.dk.data.entity.ProjectGroup;
import com.dk.response.HttpStateCode;
import com.dk.response.WrappedResponse;
import com.dk.service.ConcernService;
import com.dk.service.ProjectGroupService;
import com.dk.service.ProjectService;
import com.dk.shiro.JWToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName ProjectRestController
 * @Description
 * @Author zyf
 * @Date 2018/12/5 13:15
 **/
@RestController
@RequestMapping("rest/project")
public class ProjectRestController {

    @Autowired
    ProjectService projectService;

    @Autowired
    ProjectGroupService projectGroupService;

    @Autowired
    ConcernService concernService;

    /*
     * @Description 获取项目详情
     * @Date 2018/12/5
     * @Param
     * @return
     * @Author zhy
     **/
    @GetMapping("detail")
    public WrappedResponse detail(@CookieValue(name = Constant.WX_TOKEN, required = false) String token,
                                  @RequestParam(value = "uuid", required = true) String uuid,
                                  @RequestParam(value = "type", required = true) String type) {
        WrappedResponse result = new WrappedResponse();
        if (uuid == null || uuid == "" || !"0".equals(type) && !"1".equals(type)) {
            result.setCode(HttpStateCode.REQUESTED_RANGE_NOT_SATISFIABLE.getValue());
            result.setMsg(HttpStateCode.REQUESTED_RANGE_NOT_SATISFIABLE.getReasonPhrase());
            return result;
        }
        Concern condition = new Concern();
        if (StringUtils.isBlank(token)) {
            result.setCode(HttpStateCode.INVALID_TOKEN.getValue());
            result.setMsg(HttpStateCode.INVALID_TOKEN.getReasonPhrase());
            return result;
        }
        String user = JWToken.getUuid(token);
        condition.setUser(user);
        condition.setProject(uuid);
        condition.setType(type);
        condition.setStatus(Byte.valueOf("0"));
        Concern concern = concernService.findByCondition(condition);
        Boolean flag = false;
        if (concern != null) {
            flag = true;
        }
        if ("0".equals(type)) {
            Project project = projectService.findByUuid(uuid);
            if (project == null) {
                result.setCode(HttpStateCode.NO_CONTENT.getValue());
                result.setMsg(HttpStateCode.NO_CONTENT.getReasonPhrase());
                return result;
            }
            project.setConcern(flag);
            result.setSuccessObject(project);
        } else {
            ProjectGroup projectGroup = projectGroupService.findByUuid(uuid);
            if (projectGroup == null) {
                result.setCode(HttpStateCode.NO_CONTENT.getValue());
                result.setMsg(HttpStateCode.NO_CONTENT.getReasonPhrase());
                return result;
            }
            projectGroup.setConcern(flag);
            result.setSuccessObject(projectGroup);
        }
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        return result;
    }

    /*
     * @Description 根据项目uuid和项目类型获取注意事项
     * @Date 2018/12/14
     * @Param
     * @return
     * @Author zhy
     **/
    @PostMapping("getNotes")
    public WrappedResponse getNotes(@RequestBody ProjectDto param) {
        WrappedResponse result = new WrappedResponse();
        if (param.getUuids().length == 0 || !"0".equals(param.getType()) && !"1".equals(param.getType())) {
            result.setCode(HttpStateCode.PARAMETERS_NULL.getValue());
            result.setMsg(HttpStateCode.PARAMETERS_NULL.getReasonPhrase());
            return result;
        }
        List<String> notes = new ArrayList<>();
        List<String> uuidList = new ArrayList<>();
        Collections.addAll(uuidList, param.getUuids());
        String[] note = null;
        if ("0".equals(param.getType())) {
            List<Project> projects = projectService.batchQueryByUuids(uuidList);
            if (projects == null) {
                result.setCode(HttpStateCode.NO_CONTENT.getValue());
                result.setMsg(HttpStateCode.NO_CONTENT.getReasonPhrase());
                return result;
            }
            for (Project project : projects) {
                note = project.getNotes().split(",");
                for (int i = 0; i < note.length; i++) {
                    if (!notes.contains(note[i])) {
                        notes.add(note[i]);
                    }
                }
            }
        } else if ("1".equals(param.getType())) {
            List<ProjectGroup> projectGroups = projectGroupService.batchQueryByUuids(uuidList);
            if (projectGroups == null) {
                result.setCode(HttpStateCode.NO_CONTENT.getValue());
                result.setMsg(HttpStateCode.NO_CONTENT.getReasonPhrase());
                return result;
            }
            for (ProjectGroup projectGroup : projectGroups) {
                note = projectGroup.getNotes().split(",");
                for (int i = 0; i < note.length; i++) {
                    if (!notes.contains(note[i])) {
                        notes.add(note[i]);
                    }
                }
            }
        }
        result.setCode(HttpStateCode.OK.getValue());
        result.setMsg(HttpStateCode.OK.getReasonPhrase());
        result.setSuccessObject(notes);
        return result;
    }


}

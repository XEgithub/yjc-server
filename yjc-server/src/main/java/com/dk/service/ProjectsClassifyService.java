package com.dk.service;

import com.dk.data.dto.SearchProjectsClassifyDto;
import com.dk.data.entity.Project;
import com.dk.data.entity.ProjectGroup;
import com.dk.data.entity.ProjectsClassify;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 微信用户 Service
 *
 * @author ban
 * @date 2018/12/10
 */
public interface ProjectsClassifyService {

    ProjectsClassify add(ProjectsClassify bean);

    ProjectsClassify update(ProjectsClassify bean);

    ProjectsClassify findById(Long id);

    ProjectsClassify findByUuid(String uuid);

    IPage<ProjectsClassify> findAll(SearchProjectsClassifyDto condition);

    int count(SearchProjectsClassifyDto condition);

    int batchInsert(List<ProjectsClassify> list);

    List<ProjectsClassify> batchQueryByIds(List<Long> ids);

    List<ProjectsClassify> batchQueryByUuids(List<String> uuids);

    int delete(Long id);

    int delete(String uuid);

    int delete(ProjectsClassify bean);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

    IPage<Project> findByClassify(SearchProjectsClassifyDto projectsClassifyDto);

    IPage<ProjectGroup> findByClassifyGroup(SearchProjectsClassifyDto projectsClassifyDto);
}

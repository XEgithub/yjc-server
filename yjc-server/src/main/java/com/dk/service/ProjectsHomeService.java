package com.dk.service;

import com.dk.data.dto.SearchProjectsHomeDto;
import com.dk.data.entity.Project;
import com.dk.data.entity.ProjectGroup;
import com.dk.data.entity.ProjectsHome;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 轮播图 Service
 *
 * @author ban
 * @date 2018/12/04
 */
public interface ProjectsHomeService {

    ProjectsHome add(ProjectsHome bean);

    ProjectsHome update(ProjectsHome bean);

    ProjectsHome findById(Long id);

    ProjectsHome findByUuid(String uuid);

    IPage<ProjectsHome> findAll(SearchProjectsHomeDto condition);

    IPage<Project> findByClassify(SearchProjectsHomeDto condition);

    IPage<ProjectGroup> findByClassifyGroup(SearchProjectsHomeDto condition);

    int count(SearchProjectsHomeDto condition);

    int batchInsert(List<ProjectsHome> list);

    List<ProjectsHome> batchQueryByIds(List<Long> ids);

    List<ProjectsHome> batchQueryByUuids(List<String> uuids);

    int delete(Long id);

    int delete(String uuid);

    int delete(ProjectsHome bean);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

}

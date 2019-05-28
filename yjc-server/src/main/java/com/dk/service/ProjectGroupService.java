package com.dk.service;

import com.dk.data.dto.SearchProjectDto;
import com.dk.data.dto.SearchProjectGroupDto;
import com.dk.data.entity.ProjectGroup;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 轮播图 Service
 *
 * @author ban
 * @date 2018/12/04
 */
public interface ProjectGroupService {

    ProjectGroup add(ProjectGroup bean);

    ProjectGroup update(ProjectGroup bean);

    ProjectGroup findById(Long id);

    ProjectGroup findByUuid(String uuid);

    IPage<ProjectGroup> findAll(SearchProjectGroupDto condition);

    int count(SearchProjectGroupDto condition);

    int batchInsert(List<ProjectGroup> list);

    List<ProjectGroup> batchQueryByIds(List<Long> ids);

    List<ProjectGroup> batchQueryByUuids(List<String> uuids);

    int delete(Long id);

    int delete(String uuid);

    int delete(ProjectGroup bean);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

    IPage<ProjectGroup> findByCondition(SearchProjectDto condition);

    List<ProjectGroup> findByLikeName(String keyWord);
}

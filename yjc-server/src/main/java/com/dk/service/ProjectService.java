package com.dk.service;

import com.dk.data.dto.SearchProjectDto;
import com.dk.data.entity.Project;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 轮播图 Service
 *
 * @author ban
 * @date 2018/12/04
 */
public interface ProjectService {

    Project add(Project bean);

    Project update(Project bean);

    Project findById(Long id);

    Project findByUuid(String uuid);

    IPage<Project> findAll(SearchProjectDto condition);

    int count(SearchProjectDto condition);

    int batchInsert(List<Project> list);

    List<Project> batchQueryByIds(List<Long> ids);

    List<Project> batchQueryByUuids(List<String> uuids);

    int delete(Long id);

    int delete(String uuid);

    int delete(Project bean);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

    IPage<Project> findByCondition(SearchProjectDto condition);

    List<Project> findByLikeName(String keyWord);
}

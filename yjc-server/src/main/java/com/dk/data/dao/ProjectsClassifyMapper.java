package com.dk.data.dao;

import com.dk.data.dto.SearchProjectsClassifyDto;
import com.dk.data.entity.Project;
import com.dk.data.entity.ProjectGroup;
import com.dk.data.entity.ProjectsClassify;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 微信用户 Mapper
 *
 * @author ban
 * @date 2018/12/10
 */
@Mapper
public interface ProjectsClassifyMapper extends BaseMapper<ProjectsClassify> {
    
    int deleteByPrimaryKey(Long id);

    IPage<ProjectsClassify> findAll(Page page, @Param("condition") SearchProjectsClassifyDto condition);

    int count(@Param("condition") SearchProjectsClassifyDto condition);

    int batchInsert(List<ProjectsClassify> list);

    List<ProjectsClassify> batchQueryByIds(List<Long> ids);

    List<ProjectsClassify> batchQueryByUuids(List<String> uuids);

    ProjectsClassify findByUuid(String uuid);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

    int deleteByUuid(String uuid);

    IPage<Project> findByClassify(Page page, @Param("condition") SearchProjectsClassifyDto condition);

    IPage<ProjectGroup> findByClassifyGroup(Page page, @Param("condition") SearchProjectsClassifyDto condition);

    long countByClassify(@Param("condition") SearchProjectsClassifyDto condition);

    long countByclassifyGroup(@Param("condition") SearchProjectsClassifyDto condition);
}
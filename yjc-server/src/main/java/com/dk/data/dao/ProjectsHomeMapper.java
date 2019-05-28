package com.dk.data.dao;

import com.dk.data.dto.SearchProjectsHomeDto;
import com.dk.data.entity.Project;
import com.dk.data.entity.ProjectGroup;
import com.dk.data.entity.ProjectsHome;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 轮播图 Mapper
 *
 * @author ban
 * @date 2018/12/04
 */
@Mapper
public interface ProjectsHomeMapper extends BaseMapper<ProjectsHome> {
    
    int deleteByPrimaryKey(Long id);

    IPage<ProjectsHome> findAll(Page page, @Param("condition") SearchProjectsHomeDto condition);

    IPage<Project> findByClassify(Page page, @Param("condition") SearchProjectsHomeDto condition);

    IPage<ProjectGroup> findByClassifyGroup(Page page, @Param("condition") SearchProjectsHomeDto condition);

    int count(@Param("condition") SearchProjectsHomeDto condition);

    int batchInsert(List<ProjectsHome> list);

    List<ProjectsHome> batchQueryByIds(List<Long> ids);

    List<ProjectsHome> batchQueryByUuids(List<String> uuids);

    ProjectsHome findByUuid(String uuid);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

    int deleteByUuid(String uuid);

    int countByClassify(@Param("condition") SearchProjectsHomeDto condition);
}
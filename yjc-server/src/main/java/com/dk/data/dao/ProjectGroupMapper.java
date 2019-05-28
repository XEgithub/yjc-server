package com.dk.data.dao;

import com.dk.data.dto.SearchProjectDto;
import com.dk.data.dto.SearchProjectGroupDto;
import com.dk.data.entity.ProjectGroup;
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
public interface ProjectGroupMapper extends BaseMapper<ProjectGroup> {
    
    int deleteByPrimaryKey(Long id);

    IPage<ProjectGroup> findAll(Page page, @Param("condition") SearchProjectGroupDto condition);

    int count(@Param("condition") SearchProjectGroupDto condition);

    int batchInsert(List<ProjectGroup> list);

    List<ProjectGroup> batchQueryByIds(List<Long> ids);

    List<ProjectGroup> batchQueryByUuids(List<String> uuids);

    ProjectGroup findByUuid(String uuid);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

    int deleteByUuid(String uuid);

    IPage<ProjectGroup> findByCondition(Page page, @Param("condition") SearchProjectDto condition);

    long countByCondition(@Param("condition") SearchProjectDto condition);

    List<ProjectGroup> findByLikeName(String keyWord);
}
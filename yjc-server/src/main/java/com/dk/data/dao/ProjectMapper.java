package com.dk.data.dao;

import com.dk.data.dto.SearchProjectDto;
import com.dk.data.entity.Project;
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
public interface ProjectMapper extends BaseMapper<Project> {
    
    int deleteByPrimaryKey(Long id);

    IPage<Project> findAll(Page page, @Param("condition") SearchProjectDto condition);

    int count(@Param("condition") SearchProjectDto condition);

    int batchInsert(List<Project> list);

    List<Project> batchQueryByIds(List<Long> ids);

    List<Project> batchQueryByUuids(List<String> uuids);

    Project findByUuid(String uuid);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

    int deleteByUuid(String uuid);

/*    List<Project> selectByUuids(String[] uuids);*/

    IPage<Project> findByCondition(Page page, @Param("condition") SearchProjectDto condition);

    long countByCondition(@Param("condition") SearchProjectDto condition);

    List<Project> findByLikeName(String keyWord);
}
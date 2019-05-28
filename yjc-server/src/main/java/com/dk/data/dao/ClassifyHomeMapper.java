package com.dk.data.dao;

import com.dk.data.dto.SearchClassifyHomeDto;
import com.dk.data.entity.ClassifyHome;
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
public interface ClassifyHomeMapper extends BaseMapper<ClassifyHome> {
    
    int deleteByPrimaryKey(Long id);

    IPage<ClassifyHome> findAll(Page page, @Param("condition") SearchClassifyHomeDto condition);

    int count(@Param("condition") SearchClassifyHomeDto condition);

    int batchInsert(List<ClassifyHome> list);

    List<ClassifyHome> batchQueryByIds(List<Long> ids);

    List<ClassifyHome> batchQueryByUuids(List<String> uuids);

    ClassifyHome findByUuid(String uuid);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

    int deleteByUuid(String uuid);

}
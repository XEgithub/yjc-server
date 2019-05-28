package com.dk.data.dao;

import com.dk.data.dto.SearchConcernDto;
import com.dk.data.entity.Concern;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 关注 Mapper
 *
 * @author ban
 * @date 2018/12/12
 */
@Mapper
public interface ConcernMapper extends BaseMapper<Concern> {
    
    int deleteByPrimaryKey(Long id);

    IPage<Concern> findAll(Page page, @Param("condition") SearchConcernDto condition);

    int count(@Param("condition") SearchConcernDto condition);

    int batchInsert(List<Concern> list);

    List<Concern> batchQueryByIds(List<Long> ids);

    List<Concern> batchQueryByUuids(List<String> uuids);

    Concern findByUuid(String uuid);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

    int deleteByUuid(String uuid);

    Concern findByCondition(@Param("condition") Concern condition);
}
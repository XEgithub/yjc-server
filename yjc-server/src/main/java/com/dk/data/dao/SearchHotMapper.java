package com.dk.data.dao;

import com.dk.data.dto.SearchSearchHotDto;
import com.dk.data.entity.SearchHot;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 热门搜索 Mapper
 *
 * @author ban
 * @date 2018/12/11
 */
@Mapper
public interface SearchHotMapper extends BaseMapper<SearchHot> {
    
    int deleteByPrimaryKey(Long id);

    IPage<SearchHot> findAll(Page page, @Param("condition") SearchSearchHotDto condition);

    int count(@Param("condition") SearchSearchHotDto condition);

    int batchInsert(List<SearchHot> list);

    List<SearchHot> batchQueryByIds(List<Long> ids);

    List<SearchHot> batchQueryByUuids(List<String> uuids);

    SearchHot findByUuid(String uuid);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

    int deleteByUuid(String uuid);

    List<SearchHot> findByLikeSearchName(String searchName);

}
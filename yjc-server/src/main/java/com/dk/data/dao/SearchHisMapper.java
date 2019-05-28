package com.dk.data.dao;

import com.dk.data.dto.SearchSearchHisDto;
import com.dk.data.entity.SearchHis;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 历史搜索 Mapper
 *
 * @author ban
 * @date 2018/12/11
 */
@Mapper
public interface SearchHisMapper extends BaseMapper<SearchHis> {
    
    int deleteByPrimaryKey(Long id);

    IPage<SearchHis> findAll(Page page, @Param("condition") SearchSearchHisDto condition);

    int count(@Param("condition") SearchSearchHisDto condition);

    int batchInsert(List<SearchHis> list);

    List<SearchHis> batchQueryByIds(List<Long> ids);

    List<SearchHis> batchQueryByUuids(List<String> uuids);

    SearchHis findByUuid(String uuid);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

    int deleteByUuid(String uuid);

    SearchHis findById(Long id);

    int updateByUuid(@Param("condition") SearchHis dbSearchHis);

    int updateStatusByUserUuid(String user);

    SearchHis findByUserAndSearchName(@Param("user") String user, @Param("keyword") String keyword);
}
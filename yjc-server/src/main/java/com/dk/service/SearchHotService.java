package com.dk.service;

import com.dk.data.dto.SearchSearchHotDto;
import com.dk.data.entity.SearchHot;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 热门搜索 Service
 *
 * @author ban
 * @date 2018/12/11
 */
public interface SearchHotService {

    SearchHot add(SearchHot bean);

    SearchHot update(SearchHot bean);

    SearchHot findById(Long id);

    SearchHot findByUuid(String uuid);

    IPage<SearchHot> findAll(SearchSearchHotDto condition);

    int count(SearchSearchHotDto condition);

    int batchInsert(List<SearchHot> list);

    List<SearchHot> batchQueryByIds(List<Long> ids);

    List<SearchHot> batchQueryByUuids(List<String> uuids);

    int delete(Long id);

    int delete(String uuid);

    int delete(SearchHot bean);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

    List<SearchHot> findByLikeSearchName(String searchName);
}

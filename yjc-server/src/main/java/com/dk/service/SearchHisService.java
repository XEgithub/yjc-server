package com.dk.service;

import com.dk.data.dto.SearchSearchHisDto;
import com.dk.data.entity.SearchHis;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 历史搜索 Service
 *
 * @author ban
 * @date 2018/12/11
 */
public interface SearchHisService {

    SearchHis add(SearchHis bean);

    SearchHis update(SearchHis bean);

    SearchHis findById(Long id);

    SearchHis findByUuid(String uuid);

    IPage<SearchHis> findAll(SearchSearchHisDto condition);

    int count(SearchSearchHisDto condition);

    int batchInsert(List<SearchHis> list);

    List<SearchHis> batchQueryByIds(List<Long> ids);

    List<SearchHis> batchQueryByUuids(List<String> uuids);

    int delete(Long id);

    int delete(String uuid);

    int delete(SearchHis bean);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

    int clearHis(String user);

    int addHis(String user, String keyword);
}

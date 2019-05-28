package com.dk.service;

import com.dk.data.dto.SearchClassifyHomeDto;
import com.dk.data.entity.ClassifyHome;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 轮播图 Service
 *
 * @author ban
 * @date 2018/12/04
 */
public interface ClassifyHomeService {

    ClassifyHome add(ClassifyHome bean);

    ClassifyHome update(ClassifyHome bean);

    ClassifyHome findById(Long id);

    ClassifyHome findByUuid(String uuid);

    IPage<ClassifyHome> findAll(SearchClassifyHomeDto condition);

    int count(SearchClassifyHomeDto condition);

    int batchInsert(List<ClassifyHome> list);

    List<ClassifyHome> batchQueryByIds(List<Long> ids);

    List<ClassifyHome> batchQueryByUuids(List<String> uuids);

    int delete(Long id);

    int delete(String uuid);

    int delete(ClassifyHome bean);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

}

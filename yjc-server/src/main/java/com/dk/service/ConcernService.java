package com.dk.service;

import com.dk.data.dto.SearchConcernDto;
import com.dk.data.entity.Concern;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 关注 Service
 *
 * @author ban
 * @date 2018/12/12
 */
public interface ConcernService {

    Concern add(Concern bean);

    Concern update(Concern bean);

    Concern findById(Long id);

    Concern findByUuid(String uuid);

    IPage<Concern> findAll(SearchConcernDto condition);

    int count(SearchConcernDto condition);

    int batchInsert(List<Concern> list);

    List<Concern> batchQueryByIds(List<Long> ids);

    List<Concern> batchQueryByUuids(List<String> uuids);

    int delete(Long id);

    int delete(String uuid);

    int delete(Concern bean);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

    Concern findByCondition(Concern condition);
}

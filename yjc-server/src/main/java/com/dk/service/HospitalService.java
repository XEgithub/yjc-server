package com.dk.service;

import com.dk.data.dto.SearchHospitalDto;
import com.dk.data.entity.Hospital;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 轮播图 Service
 *
 * @author ban
 * @date 2018/12/04
 */
public interface HospitalService {

    Hospital add(Hospital bean);

    Hospital update(Hospital bean);

    Hospital findById(Long id);

    Hospital findByUuid(String uuid);

    IPage<Hospital> findAll(SearchHospitalDto condition);

    int count(SearchHospitalDto condition);

    int batchInsert(List<Hospital> list);

    List<Hospital> batchQueryByIds(List<Long> ids);

    List<Hospital> batchQueryByUuids(List<String> uuids);

    int delete(Long id);

    int delete(String uuid);

    int delete(Hospital bean);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

    List<Hospital> findByLikeName(String keyword);
}

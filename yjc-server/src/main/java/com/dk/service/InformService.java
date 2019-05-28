package com.dk.service;

import com.dk.data.dto.SearchInformDto;
import com.dk.data.entity.Inform;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 微信用户 Service
 *
 * @author ban
 * @date 2018/12/11
 */
public interface InformService {

    Inform add(Inform bean);

    Inform update(Inform bean);

    Inform findById(Long id);

    Inform findByUuid(String uuid);

    IPage<Inform> findAll(SearchInformDto condition);

    int count(SearchInformDto condition);

    int batchInsert(List<Inform> list);

    List<Inform> batchQueryByIds(List<Long> ids);

    List<Inform> batchQueryByUuids(List<String> uuids);

    int delete(Long id);

    int delete(String uuid);

    int delete(Inform bean);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

    int readByUuid(String uuid);
}

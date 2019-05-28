package com.dk.service;

import com.dk.data.dto.SearchRemarkDto;
import com.dk.data.entity.Remark;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 订单 Service
 *
 * @author ban
 * @date 2018/12/06
 */
public interface RemarkService {

    Remark add(Remark bean);

    Remark update(Remark bean);

    Remark findById(Long id);

    Remark findByUuid(String uuid);

    IPage<Remark> findAll(SearchRemarkDto condition);

    int count(SearchRemarkDto condition);

    int batchInsert(List<Remark> list);

    List<Remark> batchQueryByIds(List<Long> ids);

    List<Remark> batchQueryByUuids(List<String> uuids);

    int delete(Long id);

    int delete(String uuid);

    int delete(Remark bean);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

    List<Remark> getAll();
}

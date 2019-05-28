package com.dk.service;

import com.dk.data.dto.SearchNurseDto;
import com.dk.data.entity.Nurse;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dk.data.entity.WxUserExtend;

import java.util.List;

/**
 * 护士 Service
 *
 * @author ban
 * @date 2019/01/10
 */
public interface NurseService {

    Nurse add(Nurse bean);

    Nurse update(Nurse bean);

    Nurse findById(Long id);

    Nurse findByUuid(String uuid);

    IPage<Nurse> findAll(SearchNurseDto condition);

    int count(SearchNurseDto condition);

    int batchInsert(List<Nurse> list);

    List<Nurse> batchQueryByIds(List<Long> ids);

    List<Nurse> batchQueryByUuids(List<String> uuids);

    int delete(Long id);

    int delete(String uuid);

    int delete(Nurse bean);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

    Nurse findByUser(String user);
}

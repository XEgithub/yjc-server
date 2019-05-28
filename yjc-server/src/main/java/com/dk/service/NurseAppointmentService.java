package com.dk.service;

import com.dk.data.dto.SearchNurseAppointmentDto;
import com.dk.data.entity.NurseAppointment;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 护士预约关系表 Service
 *
 * @author ban
 * @date 2019/01/10
 */
public interface NurseAppointmentService {

    NurseAppointment add(NurseAppointment bean);

    NurseAppointment update(NurseAppointment bean);

    NurseAppointment findById(Long id);

    NurseAppointment findByUuid(String uuid);

    IPage<NurseAppointment> findAll(SearchNurseAppointmentDto condition);

    int count(SearchNurseAppointmentDto condition);

    int batchInsert(List<NurseAppointment> list);

    List<NurseAppointment> batchQueryByIds(List<Long> ids);

    List<NurseAppointment> batchQueryByUuids(List<String> uuids);

    int delete(Long id);

    int delete(String uuid);

    int delete(NurseAppointment bean);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

    NurseAppointment findByNurseAndUuid(String nurse, String uuid);

    List<NurseAppointment> findByAppointmentAndStatus(String uuid, Byte status);
}

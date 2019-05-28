package com.dk.service;

import com.dk.data.dto.SearchPatientDto;
import com.dk.data.entity.Patient;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 患者 Service
 *
 * @author ban
 * @date 2018/12/05
 */
public interface PatientService {

    Patient add(Patient bean);

    Patient update(Patient bean);

    Patient findById(Long id);

    Patient findByUuid(String uuid);

    IPage<Patient> findAll(SearchPatientDto condition);

    int count(SearchPatientDto condition);

    int batchInsert(List<Patient> list);

    List<Patient> batchQueryByIds(List<Long> ids);

    List<Patient> batchQueryByUuids(List<String> uuids);

    int delete(Long id);

    int delete(String uuid);

    int delete(Patient bean);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

    Patient findByOrderUuid(String uuid);
}

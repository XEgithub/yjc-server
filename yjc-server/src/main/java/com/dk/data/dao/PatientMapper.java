package com.dk.data.dao;

import com.dk.data.dto.SearchPatientDto;
import com.dk.data.entity.Patient;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 患者 Mapper
 *
 * @author ban
 * @date 2018/12/05
 */
@Mapper
public interface PatientMapper extends BaseMapper<Patient> {
    
    int deleteByPrimaryKey(Long id);

    IPage<Patient> findAll(Page page, @Param("condition") SearchPatientDto condition);

    int count(@Param("condition") SearchPatientDto condition);

    int batchInsert(List<Patient> list);

    List<Patient> batchQueryByIds(List<Long> ids);

    List<Patient> batchQueryByUuids(List<String> uuids);

    Patient findByUuid(String uuid);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

    int deleteByUuid(String uuid);

    Patient findByOrderUuid(String uuid);
}
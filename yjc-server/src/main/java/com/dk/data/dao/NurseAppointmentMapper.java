package com.dk.data.dao;

import com.dk.data.dto.SearchNurseAppointmentDto;
import com.dk.data.entity.Appointment;
import com.dk.data.entity.NurseAppointment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 护士预约关系表 Mapper
 *
 * @author ban
 * @date 2019/01/10
 */
@Mapper
public interface NurseAppointmentMapper extends BaseMapper<NurseAppointment> {
    
    int deleteByPrimaryKey(Long id);

    IPage<NurseAppointment> findAll(Page page, @Param("condition") SearchNurseAppointmentDto condition);

    int count(@Param("condition") SearchNurseAppointmentDto condition);

    int batchInsert(List<NurseAppointment> list);

    List<NurseAppointment> batchQueryByIds(List<Long> ids);

    List<NurseAppointment> batchQueryByUuids(List<String> uuids);

    NurseAppointment findByUuid(String uuid);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

    int deleteByUuid(String uuid);

    NurseAppointment findByAppointment(String uuid);

    NurseAppointment findByStatusAndAppointment(@Param("status") Byte status, @Param("appointment") String appointment);

    NurseAppointment findByNurseAndUuid(@Param("nurse")String nurse, @Param("uuid")String uuid);

    List<NurseAppointment> findByAppointmentAndStatus(@Param("uuid")String uuid, @Param("status")Byte status);
}
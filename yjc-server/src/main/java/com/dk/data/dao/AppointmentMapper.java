package com.dk.data.dao;

import com.dk.data.dto.SearchAppointmentDto;
import com.dk.data.entity.Appointment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 预约 Mapper
 *
 * @author ban
 * @date 2018/12/12
 */
@Mapper
public interface AppointmentMapper extends BaseMapper<Appointment> {
    
    int deleteByPrimaryKey(Long id);

    IPage<Appointment> findAll(Page page, @Param("condition") SearchAppointmentDto condition);

    int count(@Param("condition") SearchAppointmentDto condition);

    int batchInsert(List<Appointment> list);

    List<Appointment> batchQueryByIds(List<Long> ids);

    List<Appointment> batchQueryByUuids(List<String> uuids);

    Appointment findByUuid(String uuid);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

    int deleteByUuid(String uuid);

    IPage<Appointment> findAllByUser(Page page, @Param("condition") SearchAppointmentDto condition);

    long countByUser(@Param("condition") SearchAppointmentDto condition);

    Appointment findByOrderList(String orderList);

    List<Appointment> findByStatus(Byte status);
}
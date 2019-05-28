package com.dk.service;

import com.dk.data.dto.AddAppointmentDto;
import com.dk.data.dto.SearchAppointmentDto;
import com.dk.data.entity.Appointment;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;
import java.util.Map;

/**
 * 预约 Service
 *
 * @author ban
 * @date 2018/12/12
 */
public interface AppointmentService {

    Appointment add(Appointment bean);

    Appointment update(Appointment bean);

    Appointment findById(Long id);

    Appointment findByUuid(String uuid);

    IPage<Appointment> findAll(SearchAppointmentDto condition);

    int count(SearchAppointmentDto condition);

    int batchInsert(List<Appointment> list);

    List<Appointment> batchQueryByIds(List<Long> ids);

    List<Appointment> batchQueryByUuids(List<String> uuids);

    int delete(Long id);

    int delete(String uuid);

    int delete(Appointment bean);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

    IPage<Appointment> findAllByUser(SearchAppointmentDto condition);

    Appointment findByOrderList(String orderList);

    Boolean isExtraPrice(String uuid);

    void autoAllocation();
}

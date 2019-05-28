package com.dk.service;

import com.dk.data.dto.SearchOrderDto;
import com.dk.data.entity.Order;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 订单 Service
 *
 * @author ban
 * @date 2018/12/05
 */
public interface OrderService {

    Order add(Order bean);

    Order update(Order bean);

    Order findById(Long id);

    Order findByUuid(String uuid);

    IPage<Order> findAll(SearchOrderDto condition);

    int count(SearchOrderDto condition);

    int batchInsert(List<Order> list);

    List<Order> batchQueryByIds(List<Long> ids);

    List<Order> batchQueryByUuids(List<String> uuids);

    int delete(Long id);

    int delete(String uuid);

    int delete(Order bean);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

    Order findByUserAndUuid(String user, String uuid);

    IPage<Order> getAll(SearchOrderDto condition);

    Order findByAppointment(String uuid);

    int countByPatient(String uuid);

    IPage<Order> getAllByAppointment(SearchOrderDto condition);

}

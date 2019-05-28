package com.dk.data.dao;

import com.dk.data.dto.SearchOrderDto;
import com.dk.data.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单 Mapper
 *
 * @author ban
 * @date 2018/12/05
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    
    int deleteByPrimaryKey(Long id);

    IPage<Order> findAll(Page page, @Param("condition") SearchOrderDto condition);

    int count(@Param("condition") SearchOrderDto condition);

    int batchInsert(List<Order> list);

    List<Order> batchQueryByIds(List<Long> ids);

    List<Order> batchQueryByUuids(List<String> uuids);

    Order findByUuid(String uuid);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

    int deleteByUuid(String uuid);

    Order findByUserAndUuid(@Param("user") String user, @Param("uuid") String uuid);

    IPage<Order> getAll(Page page, @Param("condition") SearchOrderDto condition);

    Order findByAppointment(@Param("uuid") String uuid);

    int countByPatient(@Param("uuid") String uuid);

    IPage<Order> getAllByAppointment(Page page, @Param("condition")SearchOrderDto condition);

    int countByAppointment(@Param("condition") SearchOrderDto condition);

}
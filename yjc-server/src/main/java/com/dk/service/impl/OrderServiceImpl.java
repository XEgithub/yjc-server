package com.dk.service.impl;

import cn.sourcespro.commons.service.IdWoker;
import cn.sourcespro.commons.utils.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dk.data.dao.OrderMapper;
import com.dk.data.dto.SearchOrderDto;
import com.dk.data.entity.Order;
import com.dk.service.OrderService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dk.utils.OrderUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单 ServiceImpl
 *
 * @author ban
 * @date 2018/12/05
 */
@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderMapper mapper;
    @Autowired
    private IdWoker idWoker;

    @Override
    public Order add(Order bean) {

        initBasicVals(bean);

        int result = mapper.insert(bean);
        if (result > 0) {
            return bean;
        }
        return null;
    }

    @Override
    public Order update(Order bean) {
        Long id = bean.getId();
        if (id == null) {
            return null;
        }
        Order dbOrder = mapper.selectById(id);
        if (dbOrder == null) {
            return null;
        }
        dbOrder.setUpdateTime(new Date());
        BeanUtil.copyProperties(bean, dbOrder);
        int result = mapper.updateById(dbOrder);
        if (result > 0){
            return dbOrder;
        }
        return null;
    }

    @Override
    public Order findById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public Order findByUuid(String uuid) {
        return mapper.findByUuid(uuid);
    }

    @Override
    public IPage<Order> findAll(SearchOrderDto condition) {
        Page page = condition.getPage();
        page.setTotal(mapper.count(condition));
        return mapper.findAll(page, condition);
    }

    @Override
    public int count(SearchOrderDto condition) {
        return mapper.count(condition);
    }

    @Override
    public int batchInsert(List<Order> list) {
        for (Order bean : list) {
            initBasicVals(bean);
        }
        return mapper.batchInsert(list);
    }

    @Override
    public List<Order> batchQueryByIds(List<Long> ids) {
        return mapper.batchQueryByIds(ids);
    }

    @Override
    public List<Order> batchQueryByUuids(List<String> uuids) {
        return mapper.batchQueryByUuids(uuids);
    }

    @Override
    public int delete(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int delete(String uuid) {
        return mapper.deleteByUuid(uuid);
    }

    @Override
    public int delete(Order bean) {
        return delete(bean.getId());
    }

    @Override
    public int batchDeleteById(List<Long> ids) {
        if (CollectionUtils.isNotEmpty(ids)) {
            return mapper.batchDeleteById(ids);
        }
        return 0;
    }

    @Override
    public int batchDeleteByUuid(List<String> uuids) {
        if (CollectionUtils.isNotEmpty(uuids)) {
            return mapper.batchDeleteByUuid(uuids);
        }
        return 0;
    }

    @Override
    public Order findByUserAndUuid(String user, String uuid) {
        return mapper.findByUserAndUuid(user, uuid);
    }

    @Override
    public IPage<Order> getAll(SearchOrderDto condition) {
        Page page = condition.getPage();
        page.setTotal(mapper.count(condition));
        return mapper.getAll(page, condition);
    }

    @Override
    public Order findByAppointment(String uuid) {
        return mapper.findByAppointment(uuid);
    }

    @Override
    public int countByPatient(String uuid) {
        return mapper.countByPatient(uuid);
    }

    @Override
    public IPage<Order> getAllByAppointment(SearchOrderDto condition) {
        Page page = condition.getPage();
        page.setTotal(mapper.countByAppointment(condition));
        return mapper.getAllByAppointment(page, condition);
    }

    private void initBasicVals(Order bean){
        bean.setUuid(idWoker.nextStringId());
        bean.setOrderNo(OrderUtils.getOrderNo());
        bean.setCreateTime(new Date());
        bean.setUpdateTime(new Date());
        bean.setDeleted(false);
        bean.setRebookPrice(new BigDecimal(0));
        bean.setReschedulePrice(new BigDecimal(0));
        bean.setRescheduleCount(0);
        bean.setOrderStatus("1");
        bean.setOperator(bean.getUser());
    }
}
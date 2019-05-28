package com.dk.service.impl;

import cn.sourcespro.commons.service.IdWoker;
import cn.sourcespro.commons.utils.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dk.data.dao.NurseAppointmentMapper;
import com.dk.data.dto.SearchNurseAppointmentDto;
import com.dk.data.entity.NurseAppointment;
import com.dk.service.NurseAppointmentService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 护士预约关系表 ServiceImpl
 *
 * @author ban
 * @date 2019/01/10
 */
@Service
public class NurseAppointmentServiceImpl implements NurseAppointmentService {

    private static final Logger logger = LoggerFactory.getLogger(NurseAppointmentServiceImpl.class);

    @Autowired
    private NurseAppointmentMapper mapper;
    @Autowired
    private IdWoker idWoker;

    @Override
    public NurseAppointment add(NurseAppointment bean) {

        initBasicVals(bean);

        int result = mapper.insert(bean);
        if (result > 0) {
            return bean;
        }
        return null;
    }

    @Override
    public NurseAppointment update(NurseAppointment bean) {
        Long id = bean.getId();
        if (id == null) {
            return null;
        }
        NurseAppointment dbNurseAppointment = mapper.selectById(id);
        if (dbNurseAppointment == null) {
            return null;
        }
        dbNurseAppointment.setUpdateTime(new Date());
        BeanUtil.copyProperties(bean, dbNurseAppointment);
        int result = mapper.updateById(dbNurseAppointment);
        if (result > 0){
            return dbNurseAppointment;
        }
        return null;
    }

    @Override
    public NurseAppointment findById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public NurseAppointment findByUuid(String uuid) {
        return mapper.findByUuid(uuid);
    }

    @Override
    public IPage<NurseAppointment> findAll(SearchNurseAppointmentDto condition) {
        Page page = condition.getPage();
        page.setTotal(mapper.count(condition));
        return mapper.findAll(page, condition);
    }

    @Override
    public int count(SearchNurseAppointmentDto condition) {
        return mapper.count(condition);
    }

    @Override
    public int batchInsert(List<NurseAppointment> list) {
        for (NurseAppointment bean : list) {
            initBasicVals(bean);
        }
        return mapper.batchInsert(list);
    }

    @Override
    public List<NurseAppointment> batchQueryByIds(List<Long> ids) {
        return mapper.batchQueryByIds(ids);
    }

    @Override
    public List<NurseAppointment> batchQueryByUuids(List<String> uuids) {
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
    public int delete(NurseAppointment bean) {
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
    public NurseAppointment findByNurseAndUuid(String nurse, String uuid) {
        return mapper.findByNurseAndUuid(nurse, uuid);
    }

    @Override
    public List<NurseAppointment> findByAppointmentAndStatus(String uuid, Byte status) {
        return mapper.findByAppointmentAndStatus(uuid, status);
    }

    private void initBasicVals(NurseAppointment bean){
        bean.setUuid(idWoker.nextStringId());
        bean.setCreateTime(new Date());
        bean.setUpdateTime(new Date());
        bean.setDeleted(false);
    }
}
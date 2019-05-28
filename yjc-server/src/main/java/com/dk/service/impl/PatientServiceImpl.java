package com.dk.service.impl;

import cn.sourcespro.commons.service.IdWoker;
import cn.sourcespro.commons.utils.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dk.data.dao.PatientMapper;
import com.dk.data.dto.SearchPatientDto;
import com.dk.data.entity.Patient;
import com.dk.service.PatientService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 患者 ServiceImpl
 *
 * @author ban
 * @date 2018/12/05
 */
@Service
public class PatientServiceImpl implements PatientService {

    private static final Logger logger = LoggerFactory.getLogger(PatientServiceImpl.class);

    @Autowired
    private PatientMapper mapper;
    @Autowired
    private IdWoker idWoker;

    @Override
    public Patient add(Patient bean) {

        initBasicVals(bean);

        int result = mapper.insert(bean);
        if (result > 0) {
            return bean;
        }
        return null;
    }

    @Override
    public Patient update(Patient bean) {
        String uuid = bean.getUuid();
        if (uuid == null || "".equals(uuid)) {
            return null;
        }
        Patient dbPatient = mapper.findByUuid(uuid);
        if (dbPatient == null) {
            return null;
        }
        dbPatient.setUpdateTime(new Date());
        BeanUtil.copyProperties(bean, dbPatient);
        int result = mapper.updateById(dbPatient);
        if (result > 0){
            return dbPatient;
        }
        return null;
    }

    @Override
    public Patient findById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public Patient findByUuid(String uuid) {
        return mapper.findByUuid(uuid);
    }

    @Override
    public IPage<Patient> findAll(SearchPatientDto condition) {
        Page page = condition.getPage();
        page.setTotal(mapper.count(condition));
        return mapper.findAll(page, condition);
    }

    @Override
    public int count(SearchPatientDto condition) {
        return mapper.count(condition);
    }

    @Override
    public int batchInsert(List<Patient> list) {
        for (Patient bean : list) {
            initBasicVals(bean);
        }
        return mapper.batchInsert(list);
    }

    @Override
    public List<Patient> batchQueryByIds(List<Long> ids) {
        return mapper.batchQueryByIds(ids);
    }

    @Override
    public List<Patient> batchQueryByUuids(List<String> uuids) {
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
    public int delete(Patient bean) {
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
    public Patient findByOrderUuid(String uuid) {
        return mapper.findByOrderUuid(uuid);
    }

    private void initBasicVals(Patient bean){
        bean.setUuid(idWoker.nextStringId());
        bean.setCreateTime(new Date());
        bean.setUpdateTime(new Date());
        bean.setDeleted(false);
    }
}
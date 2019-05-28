package com.dk.service.impl;

import cn.sourcespro.commons.service.IdWoker;
import cn.sourcespro.commons.utils.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dk.data.dao.HospitalMapper;
import com.dk.data.dto.SearchHospitalDto;
import com.dk.data.entity.Hospital;
import com.dk.service.HospitalService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 轮播图 ServiceImpl
 *
 * @author ban
 * @date 2018/12/04
 */
@Service
public class HospitalServiceImpl implements HospitalService {

    private static final Logger logger = LoggerFactory.getLogger(HospitalServiceImpl.class);

    @Autowired
    private HospitalMapper mapper;
    @Autowired
    private IdWoker idWoker;

    @Override
    public Hospital add(Hospital bean) {

        initBasicVals(bean);

        int result = mapper.insert(bean);
        if (result > 0) {
            return bean;
        }
        return null;
    }

    @Override
    public Hospital update(Hospital bean) {
        Long id = bean.getId();
        if (id == null) {
            return null;
        }
        Hospital dbHospital = mapper.selectById(id);
        if (dbHospital == null) {
            return null;
        }
        dbHospital.setUpdateTime(new Date());
        BeanUtil.copyProperties(bean, dbHospital);
        int result = mapper.updateById(dbHospital);
        if (result > 0){
            return dbHospital;
        }
        return null;
    }

    @Override
    public Hospital findById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public Hospital findByUuid(String uuid) {
        return mapper.findByUuid(uuid);
    }

    @Override
    public IPage<Hospital> findAll(SearchHospitalDto condition) {
        Page page = condition.getPage();
        page.setTotal(mapper.count(condition));
        return mapper.findAll(page, condition);
    }

    @Override
    public int count(SearchHospitalDto condition) {
        return mapper.count(condition);
    }

    @Override
    public int batchInsert(List<Hospital> list) {
        for (Hospital bean : list) {
            initBasicVals(bean);
        }
        return mapper.batchInsert(list);
    }

    @Override
    public List<Hospital> batchQueryByIds(List<Long> ids) {
        return mapper.batchQueryByIds(ids);
    }

    @Override
    public List<Hospital> batchQueryByUuids(List<String> uuids) {
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
    public int delete(Hospital bean) {
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
    public List<Hospital> findByLikeName(String keyword) {
        return mapper.findByLikeName(keyword);
    }

    private void initBasicVals(Hospital bean){
        bean.setUuid(idWoker.nextStringId());
        bean.setCreateTime(new Date());
        bean.setUpdateTime(new Date());
        bean.setDeleted(false);
    }
}
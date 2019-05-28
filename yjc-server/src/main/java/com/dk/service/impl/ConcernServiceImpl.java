package com.dk.service.impl;

import cn.sourcespro.commons.service.IdWoker;
import cn.sourcespro.commons.utils.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dk.data.dao.ConcernMapper;
import com.dk.data.dto.SearchConcernDto;
import com.dk.data.entity.Concern;
import com.dk.service.ConcernService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 关注 ServiceImpl
 *
 * @author ban
 * @date 2018/12/12
 */
@Service
public class ConcernServiceImpl implements ConcernService {

    private static final Logger logger = LoggerFactory.getLogger(ConcernServiceImpl.class);

    @Autowired
    private ConcernMapper mapper;
    @Autowired
    private IdWoker idWoker;

    @Override
    public Concern add(Concern bean) {

        initBasicVals(bean);

        int result = mapper.insert(bean);
        if (result > 0) {
            return bean;
        }
        return null;
    }

    @Override
    public Concern update(Concern bean) {
        Long id = bean.getId();
        if (id == null) {
            return null;
        }
        Concern dbConcern = mapper.selectById(id);
        if (dbConcern == null) {
            return null;
        }
        dbConcern.setUpdateTime(new Date());
        BeanUtil.copyProperties(bean, dbConcern);
        int result = mapper.updateById(dbConcern);
        if (result > 0){
            return dbConcern;
        }
        return null;
    }

    @Override
    public Concern findById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public Concern findByUuid(String uuid) {
        return mapper.findByUuid(uuid);
    }

    @Override
    public IPage<Concern> findAll(SearchConcernDto condition) {
        Page page = condition.getPage();
        page.setTotal(mapper.count(condition));
        return mapper.findAll(page, condition);
    }

    @Override
    public int count(SearchConcernDto condition) {
        return mapper.count(condition);
    }

    @Override
    public int batchInsert(List<Concern> list) {
        for (Concern bean : list) {
            initBasicVals(bean);
        }
        return mapper.batchInsert(list);
    }

    @Override
    public List<Concern> batchQueryByIds(List<Long> ids) {
        return mapper.batchQueryByIds(ids);
    }

    @Override
    public List<Concern> batchQueryByUuids(List<String> uuids) {
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
    public int delete(Concern bean) {
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
    public Concern findByCondition(Concern condition) {
        return mapper.findByCondition(condition);
    }

    private void initBasicVals(Concern bean){
        bean.setUuid(idWoker.nextStringId());
        bean.setConcernTime(new Date());
        bean.setCreateTime(new Date());
        bean.setUpdateTime(new Date());
        bean.setStatus(Byte.valueOf("0"));
        bean.setDeleted(false);
    }
}
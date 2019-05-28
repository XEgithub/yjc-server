package com.dk.service.impl;

import cn.sourcespro.commons.service.IdWoker;
import cn.sourcespro.commons.utils.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dk.data.dao.InformMapper;
import com.dk.data.dto.SearchInformDto;
import com.dk.data.entity.Inform;
import com.dk.service.InformService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 微信用户 ServiceImpl
 *
 * @author ban
 * @date 2018/12/11
 */
@Service
public class InformServiceImpl implements InformService {

    private static final Logger logger = LoggerFactory.getLogger(InformServiceImpl.class);

    @Autowired
    private InformMapper mapper;
    @Autowired
    private IdWoker idWoker;

    @Override
    public Inform add(Inform bean) {

        initBasicVals(bean);

        int result = mapper.insert(bean);
        if (result > 0) {
            return bean;
        }
        return null;
    }

    @Override
    public Inform update(Inform bean) {
        Long id = bean.getId();
        if (id == null) {
            return null;
        }
        Inform dbInform = mapper.selectById(id);
        if (dbInform == null) {
            return null;
        }
        dbInform.setUpdateTime(new Date());
        BeanUtil.copyProperties(bean, dbInform);
        int result = mapper.updateById(dbInform);
        if (result > 0){
            return dbInform;
        }
        return null;
    }

    @Override
    public Inform findById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public Inform findByUuid(String uuid) {
        return mapper.findByUuid(uuid);
    }

    @Override
    public IPage<Inform> findAll(SearchInformDto condition) {
        Page page = condition.getPage();
        page.setTotal(mapper.count(condition));
        return mapper.findAll(page, condition);
    }

    @Override
    public int count(SearchInformDto condition) {
        return mapper.count(condition);
    }

    @Override
    public int batchInsert(List<Inform> list) {
        for (Inform bean : list) {
            initBasicVals(bean);
        }
        return mapper.batchInsert(list);
    }

    @Override
    public List<Inform> batchQueryByIds(List<Long> ids) {
        return mapper.batchQueryByIds(ids);
    }

    @Override
    public List<Inform> batchQueryByUuids(List<String> uuids) {
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
    public int delete(Inform bean) {
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
    public int readByUuid(String uuid) {
        return mapper.readByUuid(uuid);
    }

    private void initBasicVals(Inform bean){
        bean.setUuid(idWoker.nextStringId());
        bean.setCreateTime(new Date());
        bean.setUpdateTime(new Date());
        bean.setDeleted(false);
    }
}
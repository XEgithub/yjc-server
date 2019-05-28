package com.dk.service.impl;

import cn.sourcespro.commons.service.IdWoker;
import cn.sourcespro.commons.utils.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dk.data.dao.ClassifyHomeMapper;
import com.dk.data.dto.SearchClassifyHomeDto;
import com.dk.data.entity.ClassifyHome;
import com.dk.service.ClassifyHomeService;
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
public class ClassifyHomeServiceImpl implements ClassifyHomeService {

    private static final Logger logger = LoggerFactory.getLogger(ClassifyHomeServiceImpl.class);

    @Autowired
    private ClassifyHomeMapper mapper;
    @Autowired
    private IdWoker idWoker;

    @Override
    public ClassifyHome add(ClassifyHome bean) {

        initBasicVals(bean);

        int result = mapper.insert(bean);
        if (result > 0) {
            return bean;
        }
        return null;
    }

    @Override
    public ClassifyHome update(ClassifyHome bean) {
        Long id = bean.getId();
        if (id == null) {
            return null;
        }
        ClassifyHome dbClassifyHome = mapper.selectById(id);
        if (dbClassifyHome == null) {
            return null;
        }
        dbClassifyHome.setUpdateTime(new Date());
        BeanUtil.copyProperties(bean, dbClassifyHome);
        int result = mapper.updateById(dbClassifyHome);
        if (result > 0){
            return dbClassifyHome;
        }
        return null;
    }

    @Override
    public ClassifyHome findById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public ClassifyHome findByUuid(String uuid) {
        return mapper.findByUuid(uuid);
    }

    @Override
    public IPage<ClassifyHome> findAll(SearchClassifyHomeDto condition) {
        Page page = condition.getPage();
        page.setTotal(mapper.count(condition));
        return mapper.findAll(page, condition);
    }

    @Override
    public int count(SearchClassifyHomeDto condition) {
        return mapper.count(condition);
    }

    @Override
    public int batchInsert(List<ClassifyHome> list) {
        for (ClassifyHome bean : list) {
            initBasicVals(bean);
        }
        return mapper.batchInsert(list);
    }

    @Override
    public List<ClassifyHome> batchQueryByIds(List<Long> ids) {
        return mapper.batchQueryByIds(ids);
    }

    @Override
    public List<ClassifyHome> batchQueryByUuids(List<String> uuids) {
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
    public int delete(ClassifyHome bean) {
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

    private void initBasicVals(ClassifyHome bean){
        bean.setUuid(idWoker.nextStringId());
        bean.setCreateTime(new Date());
        bean.setUpdateTime(new Date());
        bean.setDeleted(false);
    }
}
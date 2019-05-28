package com.dk.service.impl;

import cn.sourcespro.commons.service.IdWoker;
import cn.sourcespro.commons.utils.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dk.data.dao.RemarkMapper;
import com.dk.data.dto.SearchRemarkDto;
import com.dk.data.entity.Remark;
import com.dk.service.RemarkService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 订单 ServiceImpl
 *
 * @author ban
 * @date 2018/12/06
 */
@Service
public class RemarkServiceImpl implements RemarkService {

    private static final Logger logger = LoggerFactory.getLogger(RemarkServiceImpl.class);

    @Autowired
    private RemarkMapper mapper;
    @Autowired
    private IdWoker idWoker;

    @Override
    public Remark add(Remark bean) {

        initBasicVals(bean);

        int result = mapper.insert(bean);
        if (result > 0) {
            return bean;
        }
        return null;
    }

    @Override
    public Remark update(Remark bean) {
        Long id = bean.getId();
        if (id == null) {
            return null;
        }
        Remark dbRemark = mapper.selectById(id);
        if (dbRemark == null) {
            return null;
        }
        dbRemark.setUpdateTime(new Date());
        BeanUtil.copyProperties(bean, dbRemark);
        int result = mapper.updateById(dbRemark);
        if (result > 0){
            return dbRemark;
        }
        return null;
    }

    @Override
    public Remark findById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public Remark findByUuid(String uuid) {
        return mapper.findByUuid(uuid);
    }

    @Override
    public IPage<Remark> findAll(SearchRemarkDto condition) {
        Page page = condition.getPage();
        page.setTotal(mapper.count(condition));
        return mapper.findAll(page, condition);
    }

    @Override
    public int count(SearchRemarkDto condition) {
        return mapper.count(condition);
    }

    @Override
    public int batchInsert(List<Remark> list) {
        for (Remark bean : list) {
            initBasicVals(bean);
        }
        return mapper.batchInsert(list);
    }

    @Override
    public List<Remark> batchQueryByIds(List<Long> ids) {
        return mapper.batchQueryByIds(ids);
    }

    @Override
    public List<Remark> batchQueryByUuids(List<String> uuids) {
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
    public int delete(Remark bean) {
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
    public List<Remark> getAll() {
        return mapper.getAll();
    }

    private void initBasicVals(Remark bean){
        bean.setUuid(idWoker.nextStringId());
        bean.setCreateTime(new Date());
        bean.setUpdateTime(new Date());
        bean.setDeleted(false);
    }
}
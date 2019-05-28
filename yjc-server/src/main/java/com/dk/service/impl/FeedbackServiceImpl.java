package com.dk.service.impl;

import cn.sourcespro.commons.service.IdWoker;
import cn.sourcespro.commons.utils.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dk.data.dao.FeedbackMapper;
import com.dk.data.dto.SearchFeedbackDto;
import com.dk.data.entity.Feedback;
import com.dk.service.FeedbackService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 反馈 ServiceImpl
 *
 * @author ban
 * @date 2019/01/10
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {

    private static final Logger logger = LoggerFactory.getLogger(FeedbackServiceImpl.class);

    @Autowired
    private FeedbackMapper mapper;
    @Autowired
    private IdWoker idWoker;

    @Override
    public Feedback add(Feedback bean) {

        initBasicVals(bean);

        int result = mapper.insert(bean);
        if (result > 0) {
            return bean;
        }
        return null;
    }

    @Override
    public Feedback update(Feedback bean) {
        Long id = bean.getId();
        if (id == null) {
            return null;
        }
        Feedback dbFeedback = mapper.selectById(id);
        if (dbFeedback == null) {
            return null;
        }
        dbFeedback.setUpdateTime(new Date());
        BeanUtil.copyProperties(bean, dbFeedback);
        int result = mapper.updateById(dbFeedback);
        if (result > 0){
            return dbFeedback;
        }
        return null;
    }

    @Override
    public Feedback findById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public Feedback findByUuid(String uuid) {
        return mapper.findByUuid(uuid);
    }

    @Override
    public IPage<Feedback> findAll(SearchFeedbackDto condition) {
        Page page = condition.getPage();
        page.setTotal(mapper.count(condition));
        return mapper.findAll(page, condition);
    }

    @Override
    public int count(SearchFeedbackDto condition) {
        return mapper.count(condition);
    }

    @Override
    public int batchInsert(List<Feedback> list) {
        for (Feedback bean : list) {
            initBasicVals(bean);
        }
        return mapper.batchInsert(list);
    }

    @Override
    public List<Feedback> batchQueryByIds(List<Long> ids) {
        return mapper.batchQueryByIds(ids);
    }

    @Override
    public List<Feedback> batchQueryByUuids(List<String> uuids) {
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
    public int delete(Feedback bean) {
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

    private void initBasicVals(Feedback bean){
        bean.setUuid(idWoker.nextStringId());
        bean.setCreateTime(new Date());
        bean.setUpdateTime(new Date());
        bean.setDeleted(false);
    }
}
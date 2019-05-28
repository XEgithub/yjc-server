package com.dk.service.impl;

import cn.sourcespro.commons.service.IdWoker;
import cn.sourcespro.commons.utils.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dk.data.dao.CommentTemplateMapper;
import com.dk.data.dto.SearchCommentTemplateDto;
import com.dk.data.entity.CommentTemplate;
import com.dk.service.CommentTemplateService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 评论模板 ServiceImpl
 *
 * @author ban
 * @date 2019/01/03
 */
@Service
public class CommentTemplateServiceImpl implements CommentTemplateService {

    private static final Logger logger = LoggerFactory.getLogger(CommentTemplateServiceImpl.class);

    @Autowired
    private CommentTemplateMapper mapper;
    @Autowired
    private IdWoker idWoker;

    @Override
    public CommentTemplate add(CommentTemplate bean) {

        initBasicVals(bean);

        int result = mapper.insert(bean);
        if (result > 0) {
            return bean;
        }
        return null;
    }

    @Override
    public CommentTemplate update(CommentTemplate bean) {
        Long id = bean.getId();
        if (id == null) {
            return null;
        }
        CommentTemplate dbCommentTemplate = mapper.selectById(id);
        if (dbCommentTemplate == null) {
            return null;
        }
        dbCommentTemplate.setUpdateTime(new Date());
        BeanUtil.copyProperties(bean, dbCommentTemplate);
        int result = mapper.updateById(dbCommentTemplate);
        if (result > 0){
            return dbCommentTemplate;
        }
        return null;
    }

    @Override
    public CommentTemplate findById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public CommentTemplate findByUuid(String uuid) {
        return mapper.findByUuid(uuid);
    }

    @Override
    public IPage<CommentTemplate> findAll(SearchCommentTemplateDto condition) {
        Page page = condition.getPage();
        page.setTotal(mapper.count(condition));
        return mapper.findAll(page, condition);
    }

    @Override
    public int count(SearchCommentTemplateDto condition) {
        return mapper.count(condition);
    }

    @Override
    public int batchInsert(List<CommentTemplate> list) {
        for (CommentTemplate bean : list) {
            initBasicVals(bean);
        }
        return mapper.batchInsert(list);
    }

    @Override
    public List<CommentTemplate> batchQueryByIds(List<Long> ids) {
        return mapper.batchQueryByIds(ids);
    }

    @Override
    public List<CommentTemplate> batchQueryByUuids(List<String> uuids) {
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
    public int delete(CommentTemplate bean) {
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

    private void initBasicVals(CommentTemplate bean){
        bean.setUuid(idWoker.nextStringId());
        bean.setCreateTime(new Date());
        bean.setUpdateTime(new Date());
        bean.setDeleted(false);
    }
}
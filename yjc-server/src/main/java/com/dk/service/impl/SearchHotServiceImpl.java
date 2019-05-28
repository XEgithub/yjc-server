package com.dk.service.impl;

import cn.sourcespro.commons.service.IdWoker;
import cn.sourcespro.commons.utils.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dk.data.dao.SearchHotMapper;
import com.dk.data.dto.SearchSearchHotDto;
import com.dk.data.entity.SearchHot;
import com.dk.service.SearchHotService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 热门搜索 ServiceImpl
 *
 * @author ban
 * @date 2018/12/11
 */
@Service
public class SearchHotServiceImpl implements SearchHotService {

    private static final Logger logger = LoggerFactory.getLogger(SearchHotServiceImpl.class);

    @Autowired
    private SearchHotMapper mapper;
    @Autowired
    private IdWoker idWoker;

    @Override
    public SearchHot add(SearchHot bean) {

        initBasicVals(bean);

        int result = mapper.insert(bean);
        if (result > 0) {
            return bean;
        }
        return null;
    }

    @Override
    public SearchHot update(SearchHot bean) {
        Long id = bean.getId();
        if (id == null) {
            return null;
        }
        SearchHot dbSearchHot = mapper.selectById(id);
        if (dbSearchHot == null) {
            return null;
        }
        dbSearchHot.setUpdateTime(new Date());
        BeanUtil.copyProperties(bean, dbSearchHot);
        int result = mapper.updateById(dbSearchHot);
        if (result > 0){
            return dbSearchHot;
        }
        return null;
    }

    @Override
    public SearchHot findById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public SearchHot findByUuid(String uuid) {
        return mapper.findByUuid(uuid);
    }

    @Override
    public IPage<SearchHot> findAll(SearchSearchHotDto condition) {
        Page page = condition.getPage();
        page.setTotal(mapper.count(condition));
        return mapper.findAll(page, condition);
    }

    @Override
    public int count(SearchSearchHotDto condition) {
        return mapper.count(condition);
    }

    @Override
    public int batchInsert(List<SearchHot> list) {
        for (SearchHot bean : list) {
            initBasicVals(bean);
        }
        return mapper.batchInsert(list);
    }

    @Override
    public List<SearchHot> batchQueryByIds(List<Long> ids) {
        return mapper.batchQueryByIds(ids);
    }

    @Override
    public List<SearchHot> batchQueryByUuids(List<String> uuids) {
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
    public int delete(SearchHot bean) {
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
    public List<SearchHot> findByLikeSearchName(String searchName) {
        return mapper.findByLikeSearchName(searchName);
    }

    private void initBasicVals(SearchHot bean){
        bean.setUuid(idWoker.nextStringId());
        bean.setTime((long)1);
        bean.setCreateTime(new Date());
        bean.setUpdateTime(new Date());
        bean.setDeleted(false);
    }
}
package com.dk.service.impl;

import cn.sourcespro.commons.service.IdWoker;
import cn.sourcespro.commons.utils.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dk.data.dao.SearchHisMapper;
import com.dk.data.dto.SearchSearchHisDto;
import com.dk.data.entity.SearchHis;
import com.dk.service.SearchHisService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 历史搜索 ServiceImpl
 *
 * @author ban
 * @date 2018/12/11
 */
@Service
public class SearchHisServiceImpl implements SearchHisService {

    private static final Logger logger = LoggerFactory.getLogger(SearchHisServiceImpl.class);

    @Autowired
    private SearchHisMapper mapper;
    @Autowired
    private IdWoker idWoker;

    @Override
    public SearchHis add(SearchHis bean) {

        initBasicVals(bean);

        int result = mapper.insert(bean);
        if (result > 0) {
            return bean;
        }
        return null;
    }

    @Override
    public SearchHis update(SearchHis bean) {
        Long id = bean.getId();
        if (id == null) {
            return null;
        }
        SearchHis dbSearchHis = mapper.selectById(id);
        if (dbSearchHis == null) {
            return null;
        }
        dbSearchHis.setUpdateTime(new Date());
        BeanUtil.copyProperties(bean, dbSearchHis);
        int result = mapper.updateById(dbSearchHis);
        if (result > 0){
            return dbSearchHis;
        }
        return null;
    }

    @Override
    public SearchHis findById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public SearchHis findByUuid(String uuid) {
        return mapper.findByUuid(uuid);
    }

    @Override
    public IPage<SearchHis> findAll(SearchSearchHisDto condition) {
        Page page = condition.getPage();
        page.setTotal(mapper.count(condition));
        return mapper.findAll(page, condition);
    }

    @Override
    public int count(SearchSearchHisDto condition) {
        return mapper.count(condition);
    }

    @Override
    public int batchInsert(List<SearchHis> list) {
        for (SearchHis bean : list) {
            initBasicVals(bean);
        }
        return mapper.batchInsert(list);
    }

    @Override
    public List<SearchHis> batchQueryByIds(List<Long> ids) {
        return mapper.batchQueryByIds(ids);
    }

    @Override
    public List<SearchHis> batchQueryByUuids(List<String> uuids) {
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
    public int delete(SearchHis bean) {
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
    public int clearHis(String user) {
        return mapper.updateStatusByUserUuid(user);
    }

    @Override
    public int addHis(String user, String keyword) {
        SearchHis searchHis = mapper.findByUserAndSearchName(user, keyword);
        if (searchHis != null) {
            Long count = searchHis.getTime();
            count += 1;
            searchHis.setTime(count);
            searchHis.setStatus(false);
            searchHis.setUpdateTime(new Date());
            int result = mapper.updateById(searchHis);
            return result;
        } else {
            searchHis = new SearchHis();
            searchHis.setUser(user);
            searchHis.setSearchName(keyword);
            initBasicVals(searchHis);
            int result = mapper.insert(searchHis);
            return result;
        }
    }

    private void initBasicVals(SearchHis bean){
        bean.setUuid(idWoker.nextStringId());
        bean.setTime((long) 1);
        bean.setStatus(false);
        bean.setCreateTime(new Date());
        bean.setUpdateTime(new Date());
        bean.setDeleted(false);
    }
}
package com.dk.service.impl;

import cn.sourcespro.commons.service.IdWoker;
import cn.sourcespro.commons.utils.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dk.data.dao.ProjectGroupMapper;
import com.dk.data.dto.SearchProjectDto;
import com.dk.data.dto.SearchProjectGroupDto;
import com.dk.data.entity.ProjectGroup;
import com.dk.service.ProjectGroupService;
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
public class ProjectGroupServiceImpl implements ProjectGroupService {

    private static final Logger logger = LoggerFactory.getLogger(ProjectGroupServiceImpl.class);

    @Autowired
    private ProjectGroupMapper mapper;
    @Autowired
    private IdWoker idWoker;

    @Override
    public ProjectGroup add(ProjectGroup bean) {

        initBasicVals(bean);

        int result = mapper.insert(bean);
        if (result > 0) {
            return bean;
        }
        return null;
    }

    @Override
    public ProjectGroup update(ProjectGroup bean) {
        Long id = bean.getId();
        if (id == null) {
            return null;
        }
        ProjectGroup dbProjectGroup = mapper.selectById(id);
        if (dbProjectGroup == null) {
            return null;
        }
        dbProjectGroup.setUpdateTime(new Date());
        BeanUtil.copyProperties(bean, dbProjectGroup);
        int result = mapper.updateById(dbProjectGroup);
        if (result > 0){
            return dbProjectGroup;
        }
        return null;
    }

    @Override
    public ProjectGroup findById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public ProjectGroup findByUuid(String uuid) {
        return mapper.findByUuid(uuid);
    }

    @Override
    public IPage<ProjectGroup> findAll(SearchProjectGroupDto condition) {
        Page page = condition.getPage();
        page.setTotal(mapper.count(condition));
        return mapper.findAll(page, condition);
    }

    @Override
    public int count(SearchProjectGroupDto condition) {
        return mapper.count(condition);
    }

    @Override
    public int batchInsert(List<ProjectGroup> list) {
        for (ProjectGroup bean : list) {
            initBasicVals(bean);
        }
        return mapper.batchInsert(list);
    }

    @Override
    public List<ProjectGroup> batchQueryByIds(List<Long> ids) {
        return mapper.batchQueryByIds(ids);
    }

    @Override
    public List<ProjectGroup> batchQueryByUuids(List<String> uuids) {
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
    public int delete(ProjectGroup bean) {
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
    public IPage<ProjectGroup> findByCondition(SearchProjectDto condition) {
        Page page = condition.getPage();
        page.setTotal(mapper.countByCondition(condition));
        return mapper.findByCondition(page, condition);
    }

    @Override
    public List<ProjectGroup> findByLikeName(String keyWord) {
        return mapper.findByLikeName(keyWord);
    }

    private void initBasicVals(ProjectGroup bean){
        bean.setUuid(idWoker.nextStringId());
        bean.setCreateTime(new Date());
        bean.setUpdateTime(new Date());
        bean.setDeleted(false);
    }
}
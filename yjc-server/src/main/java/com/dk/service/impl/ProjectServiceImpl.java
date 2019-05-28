package com.dk.service.impl;

import cn.sourcespro.commons.service.IdWoker;
import cn.sourcespro.commons.utils.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dk.data.dao.ProjectMapper;
import com.dk.data.dto.SearchProjectDto;
import com.dk.data.entity.Project;
import com.dk.service.ProjectService;
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
public class ProjectServiceImpl implements ProjectService {

    private static final Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);

    @Autowired
    private ProjectMapper mapper;
    @Autowired
    private IdWoker idWoker;

    @Override
    public Project add(Project bean) {

        initBasicVals(bean);

        int result = mapper.insert(bean);
        if (result > 0) {
            return bean;
        }
        return null;
    }

    @Override
    public Project update(Project bean) {
        Long id = bean.getId();
        if (id == null) {
            return null;
        }
        Project dbProject = mapper.selectById(id);
        if (dbProject == null) {
            return null;
        }
        dbProject.setUpdateTime(new Date());
        BeanUtil.copyProperties(bean, dbProject);
        int result = mapper.updateById(dbProject);
        if (result > 0){
            return dbProject;
        }
        return null;
    }

    @Override
    public Project findById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public Project findByUuid(String uuid) {
        return mapper.findByUuid(uuid);
    }

    @Override
    public IPage<Project> findAll(SearchProjectDto condition) {
        Page page = condition.getPage();
        page.setTotal(mapper.count(condition));
        return mapper.findAll(page, condition);
    }

    @Override
    public int count(SearchProjectDto condition) {
        return mapper.count(condition);
    }

    @Override
    public int batchInsert(List<Project> list) {
        for (Project bean : list) {
            initBasicVals(bean);
        }
        return mapper.batchInsert(list);
    }

    @Override
    public List<Project> batchQueryByIds(List<Long> ids) {
        return mapper.batchQueryByIds(ids);
    }

    @Override
    public List<Project> batchQueryByUuids(List<String> uuids) {
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
    public int delete(Project bean) {
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
    public IPage<Project> findByCondition(SearchProjectDto condition) {
        Page page = condition.getPage();
        page.setTotal(mapper.countByCondition(condition));
        return mapper.findByCondition(page, condition);
    }

    @Override
    public List<Project> findByLikeName(String keyWord) {
        return mapper.findByLikeName(keyWord);
    }

    private void initBasicVals(Project bean){
        bean.setUuid(idWoker.nextStringId());
        bean.setCreateTime(new Date());
        bean.setUpdateTime(new Date());
        bean.setDeleted(false);
    }
}
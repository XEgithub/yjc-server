package com.dk.service.impl;

import cn.sourcespro.commons.service.IdWoker;
import cn.sourcespro.commons.utils.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dk.data.dao.ProjectsHomeMapper;
import com.dk.data.dto.SearchProjectsHomeDto;
import com.dk.data.entity.Project;
import com.dk.data.entity.ProjectGroup;
import com.dk.data.entity.ProjectsHome;
import com.dk.service.ProjectsHomeService;
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
public class ProjectsHomeServiceImpl implements ProjectsHomeService {

    private static final Logger logger = LoggerFactory.getLogger(ProjectsHomeServiceImpl.class);

    @Autowired
    private ProjectsHomeMapper mapper;
    @Autowired
    private IdWoker idWoker;

    @Override
    public ProjectsHome add(ProjectsHome bean) {

        initBasicVals(bean);

        int result = mapper.insert(bean);
        if (result > 0) {
            return bean;
        }
        return null;
    }

    @Override
    public ProjectsHome update(ProjectsHome bean) {
        Long id = bean.getId();
        if (id == null) {
            return null;
        }
        ProjectsHome dbProjectsHome = mapper.selectById(id);
        if (dbProjectsHome == null) {
            return null;
        }
        dbProjectsHome.setUpdateTime(new Date());
        BeanUtil.copyProperties(bean, dbProjectsHome);
        int result = mapper.updateById(dbProjectsHome);
        if (result > 0){
            return dbProjectsHome;
        }
        return null;
    }

    @Override
    public ProjectsHome findById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public ProjectsHome findByUuid(String uuid) {
        return mapper.findByUuid(uuid);
    }

    @Override
    public IPage<ProjectsHome> findAll(SearchProjectsHomeDto condition) {
        Page page = condition.getPage();
        page.setTotal(mapper.count(condition));
        return mapper.findAll(page, condition);
    }

    @Override
    public IPage<Project> findByClassify(SearchProjectsHomeDto condition) {
        Page page = condition.getPage();
        page.setTotal(mapper.countByClassify(condition));
        return mapper.findByClassify(page, condition);
    }

    @Override
    public IPage<ProjectGroup> findByClassifyGroup(SearchProjectsHomeDto condition) {
        Page page = condition.getPage();
        page.setTotal(mapper.count(condition));
        return mapper.findByClassifyGroup(page, condition);
    }

    @Override
    public int count(SearchProjectsHomeDto condition) {
        return mapper.count(condition);
    }

    @Override
    public int batchInsert(List<ProjectsHome> list) {
        for (ProjectsHome bean : list) {
            initBasicVals(bean);
        }
        return mapper.batchInsert(list);
    }

    @Override
    public List<ProjectsHome> batchQueryByIds(List<Long> ids) {
        return mapper.batchQueryByIds(ids);
    }

    @Override
    public List<ProjectsHome> batchQueryByUuids(List<String> uuids) {
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
    public int delete(ProjectsHome bean) {
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

    private void initBasicVals(ProjectsHome bean){
        bean.setUuid(idWoker.nextStringId());
        bean.setCreateTime(new Date());
        bean.setUpdateTime(new Date());
        bean.setDeleted(false);
    }
}
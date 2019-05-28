package com.dk.service.impl;

import cn.sourcespro.commons.conver.AbstractTreeNodeConvert;
import cn.sourcespro.commons.data.vo.NodeVo;
import cn.sourcespro.commons.service.IdWoker;
import cn.sourcespro.commons.utils.BeanUtil;
import cn.sourcespro.commons.utils.TreeUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dk.data.dao.ClassifyMapper;
import com.dk.data.dto.SearchClassifyDto;
import com.dk.data.entity.Classify;
import com.dk.service.ClassifyService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 微信用户 ServiceImpl
 *
 * @author ban
 * @date 2018/12/10
 */
@Service
public class ClassifyServiceImpl implements ClassifyService {

    private static final Logger logger = LoggerFactory.getLogger(ClassifyServiceImpl.class);

    @Autowired
    private ClassifyMapper mapper;
    @Autowired
    private IdWoker idWoker;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Classify add(Classify bean) {

        initBasicVals(bean);

        int result = mapper.insert(bean);
        if (result > 0) {
            return bean;
        }
        return null;
    }

    @Override
    public Classify update(Classify bean) {
        Long id = bean.getId();
        if (id == null) {
            return null;
        }
        Classify dbClassify = mapper.selectById(id);
        if (dbClassify == null) {
            return null;
        }
        dbClassify.setUpdateTime(new Date());
        BeanUtil.copyProperties(bean, dbClassify);
        int result = mapper.updateById(dbClassify);
        if (result > 0){
            return dbClassify;
        }
        return null;
    }

    @Override
    public Classify findById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public Classify findByUuid(String uuid) {
        return mapper.findByUuid(uuid);
    }

    @Override
    public IPage<Classify> findAll(SearchClassifyDto condition) {
        Page page = condition.getPage();
        page.setTotal(mapper.count(condition));
        return mapper.findAll(page, condition);
    }

    @Override
    public int count(SearchClassifyDto condition) {
        return mapper.count(condition);
    }

    @Override
    public List<Classify> findByPid(Long pid) {
        return mapper.findByPid(pid);
    }

    @Override
    public List<Classify> findByLevel(int level) {
        return mapper.findByLevel(level);
    }

    @Override
    public List<Classify> findByPidCascaded(Long pid) {
        Classify parent = mapper.selectById(pid);
        if (parent == null) {
            return Collections.emptyList();
        }
        List<Classify> treeList = mapper.findByLftAndRgt(parent.getLft(), parent.getRgt());
        if (CollectionUtils.isEmpty(treeList)) {
            return Collections.emptyList();
        }
        return TreeUtil.converListToTree(treeList);
    }

    @Override
    public List<NodeVo<Long>> findNodeByPidCascaded(Long pid) {
        Classify parent = mapper.selectById(pid);
        if (parent == null) {
            return Collections.emptyList();
        }
        List<Classify> treeList = mapper.findByLftAndRgt(parent.getLft(), parent.getRgt());
        if (CollectionUtils.isEmpty(treeList)) {
            return Collections.emptyList();
        }
        return TreeUtil.converListToTree(treeList, (AbstractTreeNodeConvert<NodeVo, Classify>) item -> {
            NodeVo<Long> nodeVo = new NodeVo<>();
            nodeVo.setId(item.getId());
            nodeVo.setName(item.getName());
            nodeVo.setLeaf(item.getLeaf());
            nodeVo.setLevel(item.getLevel());
            return nodeVo;
        });
    }

    @Override
    public int batchInsert(List<Classify> list) {
        for (Classify bean : list) {
            initBasicVals(bean);
        }
        return mapper.batchInsert(list);
    }

    @Override
    public List<Classify> batchQueryByIds(List<Long> ids) {
        return mapper.batchQueryByIds(ids);
    }

    @Override
    public List<Classify> batchQueryByUuids(List<String> uuids) {
        return mapper.batchQueryByUuids(uuids);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delete(Long id) {
        return mapper.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delete(String uuid) {
        return mapper.deleteByUuid(uuid);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delete(Classify bean) {
        return delete(bean.getId());
    }

    @Override
    public int batchDeleteById(List<Long> ids) {
        if (CollectionUtils.isNotEmpty(ids)) {
            for (Long id : ids) {
                delete(id);
            }
            return ids.size();
        }
        return 0;
    }

    @Override
    public int batchDeleteByUuid(List<String> uuids) {
        if (CollectionUtils.isNotEmpty(uuids)) {
            for (String uuid : uuids) {
                delete(uuid);
            }
            return uuids.size();
        }
        return 0;
    }

    private void initBasicVals(Classify bean){
        bean.setUuid(idWoker.nextStringId());
        bean.setCreateTime(new Date());
        bean.setUpdateTime(new Date());
        bean.setDeleted(false);
    }
}
package com.dk.service.impl;

import cn.sourcespro.commons.conver.AbstractTreeNodeConvert;
import cn.sourcespro.commons.data.vo.NodeVo;
import cn.sourcespro.commons.service.IdWoker;
import cn.sourcespro.commons.utils.BeanUtil;
import cn.sourcespro.commons.utils.TreeUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dk.data.dao.MenuMapper;
import com.dk.data.dto.SearchMenuDto;
import com.dk.data.entity.Menu;
import com.dk.service.MenuService;
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
 * 菜单 ServiceImpl
 *
 * @author ban
 * @date 2018/11/28
 */
@Service
public class MenuServiceImpl implements MenuService {

    private static final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);

    @Autowired
    private MenuMapper mapper;
    @Autowired
    private IdWoker idWoker;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Menu add(Menu bean) {

        initBasicVals(bean);

        int result = mapper.insert(bean);
        if (result > 0) {
            return bean;
        }
        return null;
    }

    @Override
    public Menu update(Menu bean) {
        Long id = bean.getId();
        if (id == null) {
            return null;
        }
        Menu dbMenu = mapper.selectById(id);
        if (dbMenu == null) {
            return null;
        }
        dbMenu.setUpdateTime(new Date());
        BeanUtil.copyProperties(bean, dbMenu);
        int result = mapper.updateById(dbMenu);
        if (result > 0){
            return dbMenu;
        }
        return null;
    }

    @Override
    public Menu findById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public Menu findByUuid(String uuid) {
        return mapper.findByUuid(uuid);
    }

    @Override
    public IPage<Menu> findAll(SearchMenuDto condition) {
        Page page = condition.getPage();
        page.setTotal(mapper.count(condition));
        return mapper.findAll(page, condition);
    }

    @Override
    public int count(SearchMenuDto condition) {
        return mapper.count(condition);
    }

    @Override
    public List<Menu> findByPid(Long pid) {
        return mapper.findByPid(pid);
    }

    @Override
    public List<Menu> findByLevel(int level) {
        return mapper.findByLevel(level);
    }

    @Override
    public List<Menu> findByPidCascaded(Long pid) {
        Menu parent = mapper.selectById(pid);
        if (parent == null) {
            return Collections.emptyList();
        }
        List<Menu> treeList = mapper.findByLftAndRgt(parent.getLft(), parent.getRgt());
        if (CollectionUtils.isEmpty(treeList)) {
            return Collections.emptyList();
        }
        return TreeUtil.converListToTree(treeList);
    }

    @Override
    public List<NodeVo<Long>> findNodeByPidCascaded(Long pid) {
        Menu parent = mapper.selectById(pid);
        if (parent == null) {
            return Collections.emptyList();
        }
        List<Menu> treeList = mapper.findByLftAndRgt(parent.getLft(), parent.getRgt());
        if (CollectionUtils.isEmpty(treeList)) {
            return Collections.emptyList();
        }
        return TreeUtil.converListToTree(treeList, (AbstractTreeNodeConvert<NodeVo, Menu>) item -> {
            NodeVo<Long> nodeVo = new NodeVo<>();
            nodeVo.setId(item.getId());
            nodeVo.setName(item.getName());
            nodeVo.setLeaf(item.getLeaf());
            nodeVo.setLevel(item.getLevel());
            return nodeVo;
        });
    }

    @Override
    public int batchInsert(List<Menu> list) {
        for (Menu bean : list) {
            initBasicVals(bean);
        }
        return mapper.batchInsert(list);
    }

    @Override
    public List<Menu> batchQueryByIds(List<Long> ids) {
        return mapper.batchQueryByIds(ids);
    }

    @Override
    public List<Menu> batchQueryByUuids(List<String> uuids) {
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
    public int delete(Menu bean) {
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

    private void initBasicVals(Menu bean){
        bean.setUuid(idWoker.nextStringId());
        bean.setCreateTime(new Date());
        bean.setUpdateTime(new Date());
        bean.setDeleted(false);
    }
}
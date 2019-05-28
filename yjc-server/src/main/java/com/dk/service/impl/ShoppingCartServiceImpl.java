package com.dk.service.impl;

import cn.sourcespro.commons.service.IdWoker;
import cn.sourcespro.commons.utils.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dk.data.dao.ShoppingCartMapper;
import com.dk.data.dto.SearchShoppingCartDto;
import com.dk.data.entity.ShoppingCart;
import com.dk.service.ShoppingCartService;
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
 * @date 2018/12/05
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private static final Logger logger = LoggerFactory.getLogger(ShoppingCartServiceImpl.class);

    @Autowired
    private ShoppingCartMapper mapper;

    @Autowired
    private IdWoker idWoker;

    @Override
    public ShoppingCart add(ShoppingCart bean) {

        initBasicVals(bean);

        int result = mapper.insert(bean);
        if (result > 0) {
            return bean;
        }
        return null;
    }

    @Override
    public ShoppingCart update(ShoppingCart bean) {
        Long id = bean.getId();
        if (id == null) {
            return null;
        }
        ShoppingCart dbShoppingCart = mapper.selectById(id);
        if (dbShoppingCart == null) {
            return null;
        }
        dbShoppingCart.setUpdateTime(new Date());
        BeanUtil.copyProperties(bean, dbShoppingCart);
        int result = mapper.updateById(dbShoppingCart);
        if (result > 0){
            return dbShoppingCart;
        }
        return null;
    }

    @Override
    public ShoppingCart findById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public ShoppingCart findByUuid(String uuid) {
        return mapper.findByUuid(uuid);
    }

    @Override
    public IPage<ShoppingCart> findAll(SearchShoppingCartDto condition) {
        Page page = condition.getPage();
        page.setTotal(mapper.count(condition));
        return mapper.findAll(page, condition);
    }

    @Override
    public int count(SearchShoppingCartDto condition) {
        return mapper.count(condition);
    }

    @Override
    public int batchInsert(List<ShoppingCart> list) {
        for (ShoppingCart bean : list) {
            initBasicVals(bean);
        }
        return mapper.batchInsert(list);
    }

    @Override
    public List<ShoppingCart> batchQueryByIds(List<Long> ids) {
        return mapper.batchQueryByIds(ids);
    }

    @Override
    public List<ShoppingCart> batchQueryByUuids(List<String> uuids) {
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
    public int delete(ShoppingCart bean) {
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
    public ShoppingCart findByUserAndProjectUuid(String user, String uuid) {
        return mapper.findByUserAndProjectUuid(user, uuid);
    }

    @Override
    public int deleteByUuid(String uuid) {
        return mapper.deleteByUuid(uuid);
    }

    @Override
    public ShoppingCart findByUserAndUuid(String user, String uuid) {
        return mapper.findByUserAndUuid(user, uuid);
    }

    private void initBasicVals(ShoppingCart bean){
        bean.setUuid(idWoker.nextStringId());
        bean.setCreateTime(new Date());
        bean.setUpdateTime(new Date());
        bean.setStatus(false);
        bean.setDeleted(false);
    }
}
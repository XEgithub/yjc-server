package com.dk.service.impl;

import cn.sourcespro.commons.service.IdWoker;
import cn.sourcespro.commons.utils.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dk.data.dao.WxUserExtendMapper;
import com.dk.data.dao.WxUserMapper;
import com.dk.data.dto.SearchWxUserExtendDto;
import com.dk.data.entity.User;
import com.dk.data.entity.WxUser;
import com.dk.data.entity.WxUserExtend;
import com.dk.service.WxUserExtendService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 微信用户扩展 ServiceImpl
 *
 * @author ban
 * @date 2018/12/27
 */
@Service
public class WxUserExtendServiceImpl implements WxUserExtendService {

    private static final Logger logger = LoggerFactory.getLogger(WxUserExtendServiceImpl.class);

    @Autowired
    private WxUserExtendMapper mapper;

    @Autowired
    private IdWoker idWoker;

    @Override
    public WxUserExtend add(WxUserExtend bean) {

        initBasicVals(bean);

        int result = mapper.insert(bean);
        if (result > 0) {
            return bean;
        }
        return null;
    }

    @Override
    public WxUserExtend update(WxUserExtend bean) {
        Long id = bean.getId();
        if (id == null) {
            return null;
        }
        WxUserExtend dbWxUserExtend = mapper.selectById(id);
        if (dbWxUserExtend == null) {
            return null;
        }
        dbWxUserExtend.setUpdateTime(new Date());
        BeanUtil.copyProperties(bean, dbWxUserExtend);
        int result = mapper.updateById(dbWxUserExtend);
        if (result > 0){
            return dbWxUserExtend;
        }
        return null;
    }

    @Override
    public WxUserExtend findById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public WxUserExtend findByUuid(String uuid) {
        return mapper.findByUuid(uuid);
    }

    @Override
    public IPage<WxUserExtend> findAll(SearchWxUserExtendDto condition) {
        Page page = condition.getPage();
        page.setTotal(mapper.count(condition));
        return mapper.findAll(page, condition);
    }

    @Override
    public int count(SearchWxUserExtendDto condition) {
        return mapper.count(condition);
    }

    @Override
    public int batchInsert(List<WxUserExtend> list) {
        for (WxUserExtend bean : list) {
            initBasicVals(bean);
        }
        return mapper.batchInsert(list);
    }

    @Override
    public List<WxUserExtend> batchQueryByIds(List<Long> ids) {
        return mapper.batchQueryByIds(ids);
    }

    @Override
    public List<WxUserExtend> batchQueryByUuids(List<String> uuids) {
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
    public int delete(WxUserExtend bean) {
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
    public WxUserExtend findByUser(String user) {
        WxUserExtend wxUserExtend = mapper.findByWxUserUuid(user);
        if (wxUserExtend == null) {
            WxUserExtend bean = new WxUserExtend();
            bean.setUuid(idWoker.nextStringId());
            bean.setIntegral(Long.valueOf(0));
            bean.setCreateTime(new Date());
            bean.setUpdateTime(new Date());
            bean.setDeleted(false);
            bean.setWxUser(user);
            bean.setIdentity("1");
            mapper.insert(bean);
            return mapper.findByWxUserUuid(user);
        }
        return wxUserExtend;
    }

    private void initBasicVals(WxUserExtend bean){
        bean.setUuid(idWoker.nextStringId());
        bean.setIntegral(Long.valueOf(0));
        bean.setCreateTime(new Date());
        bean.setUpdateTime(new Date());
        bean.setDeleted(false);
    }
}
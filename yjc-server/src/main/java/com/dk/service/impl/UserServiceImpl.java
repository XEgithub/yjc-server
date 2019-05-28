package com.dk.service.impl;

import cn.sourcespro.commons.service.IdWoker;
import cn.sourcespro.commons.utils.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dk.data.dao.UserMapper;
import com.dk.data.dto.SearchUserDto;
import com.dk.data.entity.User;
import com.dk.service.UserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 用户 ServiceImpl
 *
 * @author ban
 * @date 2018/11/26
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper mapper;
    @Autowired
    private IdWoker idWoker;

    @Override
    public User add(User bean) {

        initBasicVals(bean);

        int result = mapper.insert(bean);
        if (result > 0) {
            return bean;
        }
        return null;
    }

    @Override
    public User update(User bean) {
        Long id = bean.getId();
        if (id == null) {
            return null;
        }
        User dbUser = mapper.selectById(id);
        if (dbUser == null) {
            return null;
        }
        dbUser.setUpdateTime(new Date());
        BeanUtil.copyProperties(bean, dbUser);
        int result = mapper.updateById(dbUser);
        if (result > 0){
            return dbUser;
        }
        return null;
    }

    @Override
    public User findById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public User findByUuid(String uuid) {
        return mapper.findByUuid(uuid);
    }

    @Override
    public User findByUsername(String username) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", username);
        return mapper.selectOne(queryWrapper);
    }

    @Override
    public IPage<User> findAll(SearchUserDto condition) {
        Page page = condition.getPage();
        page.setTotal(mapper.count(condition));
        return mapper.findAll(page, condition);
    }

    @Override
    public int count(SearchUserDto condition) {
        return mapper.count(condition);
    }

    @Override
    public int batchInsert(List<User> list) {
        for (User bean : list) {
            initBasicVals(bean);
        }
        return mapper.batchInsert(list);
    }

    @Override
    public List<User> batchQueryByIds(List<Long> ids) {
        return mapper.batchQueryByIds(ids);
    }

    @Override
    public List<User> batchQueryByUuids(List<String> uuids) {
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
    public int delete(User bean) {
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

    private void initBasicVals(User bean){
        bean.setUuid(idWoker.nextStringId());
        bean.setCreateTime(new Date());
        bean.setUpdateTime(new Date());
        bean.setDeleted(false);
    }
}
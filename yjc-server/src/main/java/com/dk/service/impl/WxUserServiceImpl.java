package com.dk.service.impl;

import cn.sourcespro.commons.service.IdWoker;
import cn.sourcespro.commons.utils.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dk.config.Constant;
import com.dk.data.dao.WxUserExtendMapper;
import com.dk.data.dao.WxUserMapper;
import com.dk.data.dto.SearchWxUserDto;
import com.dk.data.entity.WxUser;
import com.dk.data.entity.WxUserExtend;
import com.dk.service.WxUserExtendService;
import com.dk.service.WxUserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 微信用户 ServiceImpl
 *
 * @author ban
 * @date 2018/12/07
 */
@Service
public class WxUserServiceImpl implements WxUserService {

    private static final Logger logger = LoggerFactory.getLogger(WxUserServiceImpl.class);

    @Autowired
    private WxUserMapper mapper;

    @Autowired
    private WxUserExtendMapper wxUserExtendMapper;

    @Autowired
    private IdWoker idWoker;

    @Override
    public WxUser add(WxUser bean) {

        initBasicVals(bean);

        int result = mapper.insert(bean);
        if (result > 0) {
            return bean;
        }
        return null;
    }

    @Override
    public WxUser update(WxUser bean) {
        Long id = bean.getId();
        if (id == null) {
            return null;
        }
        WxUser dbWxUser = mapper.selectById(id);
        if (dbWxUser == null) {
            return null;
        }
        dbWxUser.setUpdateTime(new Date());
        BeanUtil.copyProperties(bean, dbWxUser);
        int result = mapper.updateById(dbWxUser);
        if (result > 0){
            return dbWxUser;
        }
        return null;
    }

    @Override
    public WxUser findById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public WxUser findByUuid(String uuid) {
        return mapper.findByUuid(uuid);
    }

    @Override
    public IPage<WxUser> findAll(SearchWxUserDto condition) {
        Page page = condition.getPage();
        page.setTotal(mapper.count(condition));
        return mapper.findAll(page, condition);
    }

    @Override
    public int count(SearchWxUserDto condition) {
        return mapper.count(condition);
    }

    @Override
    public int batchInsert(List<WxUser> list) {
        for (WxUser bean : list) {
            initBasicVals(bean);
        }
        return mapper.batchInsert(list);
    }

    @Override
    public List<WxUser> batchQueryByIds(List<Long> ids) {
        return mapper.batchQueryByIds(ids);
    }

    @Override
    public List<WxUser> batchQueryByUuids(List<String> uuids) {
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
    public int delete(WxUser bean) {
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
    public WxUser findByOpenid(WxMpUser wxMpUser) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("openid", wxMpUser.getOpenId());
        WxUser wxUser = mapper.selectOne(queryWrapper);
        if (wxUser == null) {
            wxUser = new WxUser();
            initBasicVals(wxUser);
            wxUser.setHeadimgurl(wxMpUser.getHeadImgUrl());
            wxUser.setNickname(wxMpUser.getNickname());
            wxUser.setOpenid(wxMpUser.getOpenId());
            wxUser.setSex(wxMpUser.getSex());
            wxUser.setCity(wxMpUser.getCity());
            if (wxMpUser.getSubscribeTime() != null) {
                wxUser.setSubscribeTime(wxMpUser.getSubscribeTime().toString());
            }
            wxUser.setSubscribe(Constant.UN_Subscribe);
            if (wxMpUser.getSubscribe() != null) {
                wxUser.setSubscribe(Constant.NORMAL_STATUS);
            }
            mapper.insert(wxUser);
        } else {
            if (!wxUser.equals(wxMpUser)) {
                BeanUtil.copyProperties(wxMpUser, wxUser);
                mapper.updateById(wxUser);
            }
        }
        return wxUser;
    }

    @Override
    public WxUser findByOpenid(String openid) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("openid", openid);
        WxUser wxUser = mapper.selectOne(queryWrapper);
        return wxUser;
    }

    @Override
    public WxUser getByUuid(String uuid) {
        WxUserExtend wxUserExtend = wxUserExtendMapper.findByWxUserUuid(uuid);
        if (wxUserExtend == null) {
            WxUserExtend bean = new WxUserExtend();
            bean.setUuid(idWoker.nextStringId());
            bean.setIntegral(Long.valueOf(0));
            bean.setCreateTime(new Date());
            bean.setUpdateTime(new Date());
            bean.setDeleted(false);
            bean.setWxUser(uuid);
            bean.setIdentity("0");
            wxUserExtendMapper.insert(bean);
        }
        return mapper.getByUuid(uuid);
    }

    private void initBasicVals(WxUser bean){
        bean.setUuid(idWoker.nextStringId());
        bean.setCreateTime(new Date());
        bean.setUpdateTime(new Date());
        bean.setDeleted(false);
    }

}
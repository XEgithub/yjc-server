package com.dk.service.impl;

import cn.sourcespro.commons.service.IdWoker;
import cn.sourcespro.commons.utils.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dk.data.dao.CouponMapper;
import com.dk.data.dto.SearchCouponDto;
import com.dk.data.entity.Coupon;
import com.dk.service.CouponService;
import com.baomidou.mybatisplus.core.metadata.IPage;
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
 * @date 2018/12/10
 */
@Service
public class CouponServiceImpl implements CouponService {

    private static final Logger logger = LoggerFactory.getLogger(CouponServiceImpl.class);

    @Autowired
    private CouponMapper mapper;
    @Autowired
    private IdWoker idWoker;

    @Override
    public Coupon add(Coupon bean) {

        initBasicVals(bean);

        int result = mapper.insert(bean);
        if (result > 0) {
            return bean;
        }
        return null;
    }

    @Override
    public Coupon update(Coupon bean) {
        Long id = bean.getId();
        if (id == null) {
            return null;
        }
        Coupon dbCoupon = mapper.selectById(id);
        if (dbCoupon == null) {
            return null;
        }
        dbCoupon.setUpdateTime(new Date());
        BeanUtil.copyProperties(bean, dbCoupon);
        int result = mapper.updateById(dbCoupon);
        if (result > 0){
            return dbCoupon;
        }
        return null;
    }

    @Override
    public Coupon findById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public Coupon findByUuid(String uuid) {
        return mapper.findByUuid(uuid);
    }

    @Override
    public IPage<Coupon> findAll(SearchCouponDto condition) {
        Page page = condition.getPage();
        page.setTotal(mapper.count(condition));
        return mapper.findAll(page, condition);
    }

    @Override
    public int count(SearchCouponDto condition) {
        return mapper.count(condition);
    }

    @Override
    public int batchInsert(List<Coupon> list) {
        for (Coupon bean : list) {
            initBasicVals(bean);
        }
        return mapper.batchInsert(list);
    }

    @Override
    public List<Coupon> batchQueryByIds(List<Long> ids) {
        return mapper.batchQueryByIds(ids);
    }

    @Override
    public List<Coupon> batchQueryByUuids(List<String> uuids) {
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
    public int delete(Coupon bean) {
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
    public List<Coupon> findUsableAll(SearchCouponDto condition) {
        return mapper.findUsableAll(condition);
    }

    private void initBasicVals(Coupon bean){
        bean.setUuid(idWoker.nextStringId());
        bean.setCreateTime(new Date());
        bean.setUpdateTime(new Date());
        bean.setDeleted(false);
    }
}
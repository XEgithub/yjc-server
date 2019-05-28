package com.dk.service;

import com.dk.data.dto.SearchCouponDto;
import com.dk.data.entity.Coupon;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 微信用户 Service
 *
 * @author ban
 * @date 2018/12/10
 */
public interface CouponService {

    Coupon add(Coupon bean);

    Coupon update(Coupon bean);

    Coupon findById(Long id);

    Coupon findByUuid(String uuid);

    IPage<Coupon> findAll(SearchCouponDto condition);

    int count(SearchCouponDto condition);

    int batchInsert(List<Coupon> list);

    List<Coupon> batchQueryByIds(List<Long> ids);

    List<Coupon> batchQueryByUuids(List<String> uuids);

    int delete(Long id);

    int delete(String uuid);

    int delete(Coupon bean);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

    List<Coupon> findUsableAll(SearchCouponDto condition);
}

package com.dk.data.dao;

import com.dk.data.dto.SearchCouponDto;
import com.dk.data.entity.Coupon;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 微信用户 Mapper
 *
 * @author ban
 * @date 2018/12/10
 */
@Mapper
public interface CouponMapper extends BaseMapper<Coupon> {
    
    int deleteByPrimaryKey(Long id);

    IPage<Coupon> findAll(Page page, @Param("condition") SearchCouponDto condition);

    int count(@Param("condition") SearchCouponDto condition);

    int batchInsert(List<Coupon> list);

    List<Coupon> batchQueryByIds(List<Long> ids);

    List<Coupon> batchQueryByUuids(List<String> uuids);

    Coupon findByUuid(String uuid);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

    int deleteByUuid(String uuid);

    List<Coupon> findUsableAll(@Param("condition") SearchCouponDto condition);
}
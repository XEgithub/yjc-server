package com.dk.service;

import com.dk.data.dto.SearchShoppingCartDto;
import com.dk.data.entity.ShoppingCart;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 轮播图 Service
 *
 * @author ban
 * @date 2018/12/05
 */
public interface ShoppingCartService {

    ShoppingCart add(ShoppingCart bean);

    ShoppingCart update(ShoppingCart bean);

    ShoppingCart findById(Long id);

    ShoppingCart findByUuid(String uuid);

    IPage<ShoppingCart> findAll(SearchShoppingCartDto condition);

    int count(SearchShoppingCartDto condition);

    int batchInsert(List<ShoppingCart> list);

    List<ShoppingCart> batchQueryByIds(List<Long> ids);

    List<ShoppingCart> batchQueryByUuids(List<String> uuids);

    int delete(Long id);

    int delete(String uuid);

    int delete(ShoppingCart bean);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

    ShoppingCart findByUserAndProjectUuid(String user, String uuid);

    int deleteByUuid(String uuid);

    ShoppingCart findByUserAndUuid(String user, String uuid);
}

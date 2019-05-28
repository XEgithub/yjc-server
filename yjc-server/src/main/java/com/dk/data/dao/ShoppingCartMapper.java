package com.dk.data.dao;

import com.dk.data.dto.SearchShoppingCartDto;
import com.dk.data.entity.ShoppingCart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 轮播图 Mapper
 *
 * @author ban
 * @date 2018/12/05
 */
@Mapper
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {
    
    int deleteByPrimaryKey(Long id);

    IPage<ShoppingCart> findAll(Page page, @Param("condition") SearchShoppingCartDto condition);

    int count(@Param("condition") SearchShoppingCartDto condition);

    int batchInsert(List<ShoppingCart> list);

    List<ShoppingCart> batchQueryByIds(List<Long> ids);

    List<ShoppingCart> batchQueryByUuids(List<String> uuids);

    ShoppingCart findByUuid(String uuid);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

    int deleteByUuid(String uuid);

    ShoppingCart findByUserAndProjectUuid(@Param("user") String user, @Param("uuid") String uuid);

    ShoppingCart findByUserAndUuid(@Param("user") String user, @Param("uuid") String uuid);
}
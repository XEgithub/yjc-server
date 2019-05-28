package com.dk.data.dao;

import cn.sourcespro.commons.annotation.LRTreeDelete;
import com.dk.data.dto.SearchMenuDto;
import com.dk.data.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单 Mapper
 *
 * @author ban
 * @date 2018/11/28
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    IPage<Menu> findAll(Page page, @Param("condition") SearchMenuDto condition);

    int count(@Param("condition") SearchMenuDto condition);

    List<Menu> findByPid(Long pid);

    List<Menu> findByLevel(int level);

    List<Menu> findByLftAndRgt(@Param("lft") Integer lft, @Param("rgt") Integer rgt);

    int batchInsert(List<Menu> list);

    List<Menu> batchQueryByIds(List<Long> ids);

    List<Menu> batchQueryByUuids(List<String> uuids);

    Menu findByUuid(String uuid);

    @LRTreeDelete
    int deleteById(Long id);

    @LRTreeDelete
    int deleteByUuid(String uuid);

}
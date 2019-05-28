package com.dk.data.dao;

import cn.sourcespro.commons.annotation.LRTreeDelete;
import com.dk.data.dto.SearchClassifyDto;
import com.dk.data.entity.Classify;
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
public interface ClassifyMapper extends BaseMapper<Classify> {

    IPage<Classify> findAll(Page page, @Param("condition") SearchClassifyDto condition);

    int count(@Param("condition") SearchClassifyDto condition);

    List<Classify> findByPid(Long pid);

    List<Classify> findByLevel(int level);

    List<Classify> findByLftAndRgt(@Param("lft") Integer lft, @Param("rgt") Integer rgt);

    int batchInsert(List<Classify> list);

    List<Classify> batchQueryByIds(List<Long> ids);

    List<Classify> batchQueryByUuids(List<String> uuids);

    Classify findByUuid(String uuid);

    @LRTreeDelete
    int deleteById(Long id);

    @LRTreeDelete
    int deleteByUuid(String uuid);

}
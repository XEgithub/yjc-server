package com.dk.data.dao;

import com.dk.data.dto.SearchCarouselDto;
import com.dk.data.entity.Carousel;
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
 * @date 2018/12/04
 */
@Mapper
public interface CarouselMapper extends BaseMapper<Carousel> {
    
    int deleteByPrimaryKey(Long id);

    IPage<Carousel> findAll(Page page, @Param("condition") SearchCarouselDto condition);

    int count(@Param("condition") SearchCarouselDto condition);

    int batchInsert(List<Carousel> list);

    List<Carousel> batchQueryByIds(List<Long> ids);

    List<Carousel> batchQueryByUuids(List<String> uuids);

    Carousel findByUuid(String uuid);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

    int deleteByUuid(String uuid);

}
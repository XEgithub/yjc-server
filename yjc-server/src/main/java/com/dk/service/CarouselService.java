package com.dk.service;

import com.dk.data.dto.SearchCarouselDto;
import com.dk.data.entity.Carousel;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 轮播图 Service
 *
 * @author ban
 * @date 2018/12/04
 */
public interface CarouselService {

    Carousel add(Carousel bean);

    Carousel update(Carousel bean);

    Carousel findById(Long id);

    Carousel findByUuid(String uuid);

    IPage<Carousel> findAll(SearchCarouselDto condition);

    int count(SearchCarouselDto condition);

    int batchInsert(List<Carousel> list);

    List<Carousel> batchQueryByIds(List<Long> ids);

    List<Carousel> batchQueryByUuids(List<String> uuids);

    int delete(Long id);

    int delete(String uuid);

    int delete(Carousel bean);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

}

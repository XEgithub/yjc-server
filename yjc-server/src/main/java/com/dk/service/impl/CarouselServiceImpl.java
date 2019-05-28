package com.dk.service.impl;

import cn.sourcespro.commons.service.IdWoker;
import cn.sourcespro.commons.utils.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dk.data.dao.CarouselMapper;
import com.dk.data.dto.SearchCarouselDto;
import com.dk.data.entity.Carousel;
import com.dk.service.CarouselService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 轮播图 ServiceImpl
 *
 * @author ban
 * @date 2018/12/04
 */
@Service
public class CarouselServiceImpl implements CarouselService {

    private static final Logger logger = LoggerFactory.getLogger(CarouselServiceImpl.class);

    @Autowired
    private CarouselMapper mapper;
    @Autowired
    private IdWoker idWoker;

    @Override
    public Carousel add(Carousel bean) {

        initBasicVals(bean);

        int result = mapper.insert(bean);
        if (result > 0) {
            return bean;
        }
        return null;
    }

    @Override
    public Carousel update(Carousel bean) {
        Long id = bean.getId();
        if (id == null) {
            return null;
        }
        Carousel dbCarousel = mapper.selectById(id);
        if (dbCarousel == null) {
            return null;
        }
        dbCarousel.setUpdateTime(new Date());
        BeanUtil.copyProperties(bean, dbCarousel);
        int result = mapper.updateById(dbCarousel);
        if (result > 0){
            return dbCarousel;
        }
        return null;
    }

    @Override
    public Carousel findById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public Carousel findByUuid(String uuid) {
        return mapper.findByUuid(uuid);
    }

    @Override
    public IPage<Carousel> findAll(SearchCarouselDto condition) {
        Page page = condition.getPage();
        page.setTotal(mapper.count(condition));
        return mapper.findAll(page, condition);
    }

    @Override
    public int count(SearchCarouselDto condition) {
        return mapper.count(condition);
    }

    @Override
    public int batchInsert(List<Carousel> list) {
        for (Carousel bean : list) {
            initBasicVals(bean);
        }
        return mapper.batchInsert(list);
    }

    @Override
    public List<Carousel> batchQueryByIds(List<Long> ids) {
        return mapper.batchQueryByIds(ids);
    }

    @Override
    public List<Carousel> batchQueryByUuids(List<String> uuids) {
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
    public int delete(Carousel bean) {
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

    private void initBasicVals(Carousel bean){
        bean.setUuid(idWoker.nextStringId());
        bean.setCreateTime(new Date());
        bean.setUpdateTime(new Date());
        bean.setDeleted(false);
    }
}
package com.dk.service.impl;

import cn.sourcespro.commons.service.IdWoker;
import cn.sourcespro.commons.utils.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dk.data.dao.BarcodeMapper;
import com.dk.data.dto.SearchBarcodeDto;
import com.dk.data.entity.Barcode;
import com.dk.service.BarcodeService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 条码 ServiceImpl
 *
 * @author ban
 * @date 2019/01/10
 */
@Service
public class BarcodeServiceImpl implements BarcodeService {

    private static final Logger logger = LoggerFactory.getLogger(BarcodeServiceImpl.class);

    @Autowired
    private BarcodeMapper mapper;
    @Autowired
    private IdWoker idWoker;

    @Override
    public Barcode add(Barcode bean) {

        initBasicVals(bean);

        int result = mapper.insert(bean);
        if (result > 0) {
            return bean;
        }
        return null;
    }

    @Override
    public Barcode update(Barcode bean) {
        Long id = bean.getId();
        if (id == null) {
            return null;
        }
        Barcode dbBarcode = mapper.selectById(id);
        if (dbBarcode == null) {
            return null;
        }
        dbBarcode.setUpdateTime(new Date());
        BeanUtil.copyProperties(bean, dbBarcode);
        int result = mapper.updateById(dbBarcode);
        if (result > 0){
            return dbBarcode;
        }
        return null;
    }

    @Override
    public Barcode findById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public Barcode findByUuid(String uuid) {
        return mapper.findByUuid(uuid);
    }

    @Override
    public IPage<Barcode> findAll(SearchBarcodeDto condition) {
        Page page = condition.getPage();
        page.setTotal(mapper.count(condition));
        return mapper.findAll(page, condition);
    }

    @Override
    public int count(SearchBarcodeDto condition) {
        return mapper.count(condition);
    }

    @Override
    public int batchInsert(List<Barcode> list) {
        for (Barcode bean : list) {
            initBasicVals(bean);
        }
        return mapper.batchInsert(list);
    }

    @Override
    public List<Barcode> batchQueryByIds(List<Long> ids) {
        return mapper.batchQueryByIds(ids);
    }

    @Override
    public List<Barcode> batchQueryByUuids(List<String> uuids) {
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
    public int delete(Barcode bean) {
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
    public List<Barcode> findByAppointment(String uuid) {
        return mapper.findByAppointment(uuid);
    }

    private void initBasicVals(Barcode bean){
        bean.setUuid(idWoker.nextStringId());
        bean.setCreateTime(new Date());
        bean.setUpdateTime(new Date());
        bean.setDeleted(false);
    }
}
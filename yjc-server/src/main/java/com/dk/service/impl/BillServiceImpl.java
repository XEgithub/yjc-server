package com.dk.service.impl;

import cn.sourcespro.commons.service.IdWoker;
import cn.sourcespro.commons.utils.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dk.data.dao.BillMapper;
import com.dk.data.dto.SearchBillDto;
import com.dk.data.entity.Bill;
import com.dk.service.BillService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 护士流水表 ServiceImpl
 *
 * @author ban
 * @date 2019/01/10
 */
@Service
public class BillServiceImpl implements BillService {

    private static final Logger logger = LoggerFactory.getLogger(BillServiceImpl.class);

    @Autowired
    private BillMapper mapper;
    @Autowired
    private IdWoker idWoker;

    @Override
    public Bill add(Bill bean) {

        initBasicVals(bean);

        int result = mapper.insert(bean);
        if (result > 0) {
            return bean;
        }
        return null;
    }

    @Override
    public Bill update(Bill bean) {
        Long id = bean.getId();
        if (id == null) {
            return null;
        }
        Bill dbBill = mapper.selectById(id);
        if (dbBill == null) {
            return null;
        }
        dbBill.setUpdateTime(new Date());
        BeanUtil.copyProperties(bean, dbBill);
        int result = mapper.updateById(dbBill);
        if (result > 0){
            return dbBill;
        }
        return null;
    }

    @Override
    public Bill findById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public Bill findByUuid(String uuid) {
        return mapper.findByUuid(uuid);
    }

    @Override
    public IPage<Bill> findAll(SearchBillDto condition) {
        Page page = condition.getPage();
        page.setTotal(mapper.count(condition));
        return mapper.findAll(page, condition);
    }

    @Override
    public int count(SearchBillDto condition) {
        return mapper.count(condition);
    }

    @Override
    public int batchInsert(List<Bill> list) {
        for (Bill bean : list) {
            initBasicVals(bean);
        }
        return mapper.batchInsert(list);
    }

    @Override
    public List<Bill> batchQueryByIds(List<Long> ids) {
        return mapper.batchQueryByIds(ids);
    }

    @Override
    public List<Bill> batchQueryByUuids(List<String> uuids) {
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
    public int delete(Bill bean) {
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
    public List<Bill> findByNurse(String nurse) {
        return mapper.findByNurse(nurse);
    }

    private void initBasicVals(Bill bean){
        bean.setUuid(idWoker.nextStringId());
        bean.setCreateTime(new Date());
        bean.setUpdateTime(new Date());
        bean.setDeleted(false);
    }
}
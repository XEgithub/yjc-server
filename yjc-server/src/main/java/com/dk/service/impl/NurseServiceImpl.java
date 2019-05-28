package com.dk.service.impl;

import cn.sourcespro.commons.service.IdWoker;
import cn.sourcespro.commons.utils.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dk.data.dao.NurseMapper;
import com.dk.data.dto.SearchNurseDto;
import com.dk.data.entity.Nurse;
import com.dk.data.entity.WxUserExtend;
import com.dk.service.NurseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 护士 ServiceImpl
 *
 * @author ban
 * @date 2019/01/10
 */
@Service
public class NurseServiceImpl implements NurseService {

    private static final Logger logger = LoggerFactory.getLogger(NurseServiceImpl.class);

    @Autowired
    private NurseMapper mapper;
    @Autowired
    private IdWoker idWoker;

    @Override
    public Nurse add(Nurse bean) {

        initBasicVals(bean);

        int result = mapper.insert(bean);
        if (result > 0) {
            return bean;
        }
        return null;
    }

    @Override
    public Nurse update(Nurse bean) {
        Long id = bean.getId();
        if (id == null) {
            return null;
        }
        Nurse dbNurse = mapper.selectById(id);
        if (dbNurse == null) {
            return null;
        }
        dbNurse.setUpdateTime(new Date());
        BeanUtil.copyProperties(bean, dbNurse);
        int result = mapper.updateById(dbNurse);
        if (result > 0){
            return dbNurse;
        }
        return null;
    }

    @Override
    public Nurse findById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public Nurse findByUuid(String uuid) {
        return mapper.findByUuid(uuid);
    }

    @Override
    public IPage<Nurse> findAll(SearchNurseDto condition) {
        Page page = condition.getPage();
        page.setTotal(mapper.count(condition));
        return mapper.findAll(page, condition);
    }

    @Override
    public int count(SearchNurseDto condition) {
        return mapper.count(condition);
    }

    @Override
    public int batchInsert(List<Nurse> list) {
        for (Nurse bean : list) {
            initBasicVals(bean);
        }
        return mapper.batchInsert(list);
    }

    @Override
    public List<Nurse> batchQueryByIds(List<Long> ids) {
        return mapper.batchQueryByIds(ids);
    }

    @Override
    public List<Nurse> batchQueryByUuids(List<String> uuids) {
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
    public int delete(Nurse bean) {
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
    public Nurse findByUser(String user) {
        Nurse nurse = mapper.findByUser(user);
        if (nurse == null) {
            nurse = new Nurse();
            initBasicVals(nurse);
            nurse.setWxUser(user);
            nurse.setServiceStatus(true);
            mapper.insert(nurse);
        }
        return nurse;
    }

    private void initBasicVals(Nurse bean){
        bean.setUuid(idWoker.nextStringId());
        bean.setCreateTime(new Date());
        bean.setUpdateTime(new Date());
        bean.setDeleted(false);
        bean.setStatus(Byte.valueOf("0"));
    }
}
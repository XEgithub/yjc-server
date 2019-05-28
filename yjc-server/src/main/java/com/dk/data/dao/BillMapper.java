package com.dk.data.dao;

import com.dk.data.dto.SearchBillDto;
import com.dk.data.entity.Bill;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 护士流水表 Mapper
 *
 * @author ban
 * @date 2019/01/10
 */
@Mapper
public interface BillMapper extends BaseMapper<Bill> {
    
    int deleteByPrimaryKey(Long id);

    IPage<Bill> findAll(Page page, @Param("condition") SearchBillDto condition);

    int count(@Param("condition") SearchBillDto condition);

    int batchInsert(List<Bill> list);

    List<Bill> batchQueryByIds(List<Long> ids);

    List<Bill> batchQueryByUuids(List<String> uuids);

    Bill findByUuid(String uuid);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

    int deleteByUuid(String uuid);

    List<Bill> findByNurse(String nurse);
}
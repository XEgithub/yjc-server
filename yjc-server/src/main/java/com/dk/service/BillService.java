package com.dk.service;

import com.dk.data.dto.SearchBillDto;
import com.dk.data.entity.Bill;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 护士流水表 Service
 *
 * @author ban
 * @date 2019/01/10
 */
public interface BillService {

    Bill add(Bill bean);

    Bill update(Bill bean);

    Bill findById(Long id);

    Bill findByUuid(String uuid);

    IPage<Bill> findAll(SearchBillDto condition);

    int count(SearchBillDto condition);

    int batchInsert(List<Bill> list);

    List<Bill> batchQueryByIds(List<Long> ids);

    List<Bill> batchQueryByUuids(List<String> uuids);

    int delete(Long id);

    int delete(String uuid);

    int delete(Bill bean);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

    List<Bill> findByNurse(String nurse);
}

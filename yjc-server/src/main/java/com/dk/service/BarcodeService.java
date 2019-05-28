package com.dk.service;

import com.dk.data.dto.SearchBarcodeDto;
import com.dk.data.entity.Barcode;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 条码 Service
 *
 * @author ban
 * @date 2019/01/10
 */
public interface BarcodeService {

    Barcode add(Barcode bean);

    Barcode update(Barcode bean);

    Barcode findById(Long id);

    Barcode findByUuid(String uuid);

    IPage<Barcode> findAll(SearchBarcodeDto condition);

    int count(SearchBarcodeDto condition);

    int batchInsert(List<Barcode> list);

    List<Barcode> batchQueryByIds(List<Long> ids);

    List<Barcode> batchQueryByUuids(List<String> uuids);

    int delete(Long id);

    int delete(String uuid);

    int delete(Barcode bean);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

    List<Barcode> findByAppointment(String uuid);
}

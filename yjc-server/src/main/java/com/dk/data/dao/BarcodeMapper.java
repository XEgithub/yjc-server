package com.dk.data.dao;

import com.dk.data.dto.SearchBarcodeDto;
import com.dk.data.entity.Barcode;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 条码 Mapper
 *
 * @author ban
 * @date 2019/01/10
 */
@Mapper
public interface BarcodeMapper extends BaseMapper<Barcode> {
    
    int deleteByPrimaryKey(Long id);

    IPage<Barcode> findAll(Page page, @Param("condition") SearchBarcodeDto condition);

    int count(@Param("condition") SearchBarcodeDto condition);

    int batchInsert(List<Barcode> list);

    List<Barcode> batchQueryByIds(List<Long> ids);

    List<Barcode> batchQueryByUuids(List<String> uuids);

    Barcode findByUuid(String uuid);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

    int deleteByUuid(String uuid);

    List<Barcode> findByAppointment(String uuid);
}
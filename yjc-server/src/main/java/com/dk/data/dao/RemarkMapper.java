package com.dk.data.dao;

import com.dk.data.dto.SearchRemarkDto;
import com.dk.data.entity.Remark;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单 Mapper
 *
 * @author ban
 * @date 2018/12/06
 */
@Mapper
public interface RemarkMapper extends BaseMapper<Remark> {
    
    int deleteByPrimaryKey(Long id);

    IPage<Remark> findAll(Page page, @Param("condition") SearchRemarkDto condition);

    int count(@Param("condition") SearchRemarkDto condition);

    int batchInsert(List<Remark> list);

    List<Remark> batchQueryByIds(List<Long> ids);

    List<Remark> batchQueryByUuids(List<String> uuids);

    Remark findByUuid(String uuid);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

    int deleteByUuid(String uuid);

    List<Remark> getAll();
}
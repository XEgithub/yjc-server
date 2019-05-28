package com.dk.data.dao;

import com.dk.data.dto.SearchHospitalDto;
import com.dk.data.entity.Hospital;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 轮播图 Mapper
 *
 * @author ban
 * @date 2018/12/04
 */
@Mapper
public interface HospitalMapper extends BaseMapper<Hospital> {
    
    int deleteByPrimaryKey(Long id);

    IPage<Hospital> findAll(Page page, @Param("condition") SearchHospitalDto condition);

    int count(@Param("condition") SearchHospitalDto condition);

    int batchInsert(List<Hospital> list);

    List<Hospital> batchQueryByIds(List<Long> ids);

    List<Hospital> batchQueryByUuids(List<String> uuids);

    Hospital findByUuid(String uuid);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

    int deleteByUuid(String uuid);

    List<Hospital> findByLikeName(String keyword);
}
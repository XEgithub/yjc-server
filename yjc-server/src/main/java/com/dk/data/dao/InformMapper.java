package com.dk.data.dao;

import com.dk.data.dto.SearchInformDto;
import com.dk.data.entity.Inform;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 微信用户 Mapper
 *
 * @author ban
 * @date 2018/12/11
 */
@Mapper
public interface InformMapper extends BaseMapper<Inform> {
    
    int deleteByPrimaryKey(Long id);

    IPage<Inform> findAll(Page page, @Param("condition") SearchInformDto condition);

    int count(@Param("condition") SearchInformDto condition);

    int batchInsert(List<Inform> list);

    List<Inform> batchQueryByIds(List<Long> ids);

    List<Inform> batchQueryByUuids(List<String> uuids);

    Inform findByUuid(String uuid);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

    int deleteByUuid(String uuid);

    int readByUuid(String uuid);
}
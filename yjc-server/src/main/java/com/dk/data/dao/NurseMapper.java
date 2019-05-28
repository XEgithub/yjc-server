package com.dk.data.dao;

import com.dk.data.dto.SearchNurseDto;
import com.dk.data.entity.Nurse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dk.data.entity.WxUserExtend;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 护士 Mapper
 *
 * @author ban
 * @date 2019/01/10
 */
@Mapper
public interface NurseMapper extends BaseMapper<Nurse> {
    
    int deleteByPrimaryKey(Long id);

    IPage<Nurse> findAll(Page page, @Param("condition") SearchNurseDto condition);

    int count(@Param("condition") SearchNurseDto condition);

    int batchInsert(List<Nurse> list);

    List<Nurse> batchQueryByIds(List<Long> ids);

    List<Nurse> batchQueryByUuids(List<String> uuids);

    Nurse findByUuid(String uuid);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

    int deleteByUuid(String uuid);

    Nurse findByUser(String user);

    List<Nurse> findByStatus(Byte valueOf);
}
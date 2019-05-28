package com.dk.data.dao;

import com.dk.data.dto.SearchWxUserExtendDto;
import com.dk.data.entity.WxUserExtend;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 微信用户扩展 Mapper
 *
 * @author ban
 * @date 2018/12/27
 */
@Mapper
public interface WxUserExtendMapper extends BaseMapper<WxUserExtend> {
    
    int deleteByPrimaryKey(Long id);

    IPage<WxUserExtend> findAll(Page page, @Param("condition") SearchWxUserExtendDto condition);

    int count(@Param("condition") SearchWxUserExtendDto condition);

    int batchInsert(List<WxUserExtend> list);

    List<WxUserExtend> batchQueryByIds(List<Long> ids);

    List<WxUserExtend> batchQueryByUuids(List<String> uuids);

    WxUserExtend findByUuid(String uuid);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

    int deleteByUuid(String uuid);

    WxUserExtend findByWxUserUuid(@Param("uuid") String wxUserUuid);

}
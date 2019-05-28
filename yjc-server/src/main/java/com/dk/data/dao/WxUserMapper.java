package com.dk.data.dao;

import com.dk.data.dto.SearchWxUserDto;
import com.dk.data.entity.WxUser;
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
 * @date 2018/12/07
 */
@Mapper
public interface WxUserMapper extends BaseMapper<WxUser> {
    
    int deleteByPrimaryKey(Long id);

    IPage<WxUser> findAll(Page page, @Param("condition") SearchWxUserDto condition);

    int count(@Param("condition") SearchWxUserDto condition);

    int batchInsert(List<WxUser> list);

    List<WxUser> batchQueryByIds(List<Long> ids);

    List<WxUser> batchQueryByUuids(List<String> uuids);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

    int deleteByUuid(String uuid);

    WxUser findByUuid(String wxUserUuid);

    WxUser getByUuid(String uuid);
}
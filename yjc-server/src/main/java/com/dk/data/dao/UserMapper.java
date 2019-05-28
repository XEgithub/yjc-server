package com.dk.data.dao;

import com.dk.data.dto.SearchUserDto;
import com.dk.data.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户 Mapper
 *
 * @author ban
 * @date 2018/11/26
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    
    int deleteByPrimaryKey(Long id);

    IPage<User> findAll(Page page, @Param("condition") SearchUserDto condition);

    int count(@Param("condition") SearchUserDto condition);

    int batchInsert(List<User> list);

    List<User> batchQueryByIds(List<Long> ids);

    List<User> batchQueryByUuids(List<String> uuids);

    User findByUuid(String uuid);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

    int deleteByUuid(String uuid);

}
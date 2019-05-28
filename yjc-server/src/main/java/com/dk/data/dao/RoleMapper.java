package com.dk.data.dao;

import com.dk.data.dto.SearchRoleDto;
import com.dk.data.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色 Mapper
 *
 * @author ban
 * @date 2018/11/28
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    
    int deleteByPrimaryKey(Long id);

    IPage<Role> findAll(Page page, @Param("condition") SearchRoleDto condition);

    int count(@Param("condition") SearchRoleDto condition);

    int batchInsert(List<Role> list);

    List<Role> batchQueryByIds(List<Long> ids);

    List<Role> batchQueryByUuids(List<String> uuids);

    Role findByUuid(String uuid);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

    int deleteByUuid(String uuid);

}
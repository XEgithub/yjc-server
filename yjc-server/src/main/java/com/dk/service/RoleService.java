package com.dk.service;

import com.dk.data.dto.SearchRoleDto;
import com.dk.data.entity.Role;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 角色 Service
 *
 * @author ban
 * @date 2018/11/28
 */
public interface RoleService {

    Role add(Role bean);

    Role update(Role bean);

    Role findById(Long id);

    Role findByUuid(String uuid);

    IPage<Role> findAll(SearchRoleDto condition);

    int count(SearchRoleDto condition);

    int batchInsert(List<Role> list);

    List<Role> batchQueryByIds(List<Long> ids);

    List<Role> batchQueryByUuids(List<String> uuids);

    int delete(Long id);

    int delete(String uuid);

    int delete(Role bean);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

}

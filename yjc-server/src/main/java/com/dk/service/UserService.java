package com.dk.service;

import com.dk.data.dto.SearchUserDto;
import com.dk.data.entity.User;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 用户 Service
 *
 * @author ban
 * @date 2018/11/26
 */
public interface UserService {

    User add(User bean);

    User update(User bean);

    User findById(Long id);

    User findByUuid(String uuid);

    User findByUsername(String username);

    IPage<User> findAll(SearchUserDto condition);

    int count(SearchUserDto condition);

    int batchInsert(List<User> list);

    List<User> batchQueryByIds(List<Long> ids);

    List<User> batchQueryByUuids(List<String> uuids);

    int delete(Long id);

    int delete(String uuid);

    int delete(User bean);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

}

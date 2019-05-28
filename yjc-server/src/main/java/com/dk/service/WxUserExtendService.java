package com.dk.service;

import com.dk.data.dto.SearchWxUserExtendDto;
import com.dk.data.entity.User;
import com.dk.data.entity.WxUserExtend;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 微信用户扩展 Service
 *
 * @author ban
 * @date 2018/12/27
 */
public interface WxUserExtendService {

    WxUserExtend add(WxUserExtend bean);

    WxUserExtend update(WxUserExtend bean);

    WxUserExtend findById(Long id);

    WxUserExtend findByUuid(String uuid);

    IPage<WxUserExtend> findAll(SearchWxUserExtendDto condition);

    int count(SearchWxUserExtendDto condition);

    int batchInsert(List<WxUserExtend> list);

    List<WxUserExtend> batchQueryByIds(List<Long> ids);

    List<WxUserExtend> batchQueryByUuids(List<String> uuids);

    int delete(Long id);

    int delete(String uuid);

    int delete(WxUserExtend bean);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

    WxUserExtend findByUser(String user);
}

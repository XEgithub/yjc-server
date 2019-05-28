package com.dk.service;

import com.dk.data.dto.SearchWxUserDto;
import com.dk.data.entity.WxUser;
import com.baomidou.mybatisplus.core.metadata.IPage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

import java.util.List;

/**
 * 微信用户 Service
 *
 * @author ban
 * @date 2018/12/07
 */
public interface WxUserService {

    WxUser add(WxUser bean);

    WxUser update(WxUser bean);

    WxUser findById(Long id);

    WxUser findByUuid(String uuid);

    IPage<WxUser> findAll(SearchWxUserDto condition);

    int count(SearchWxUserDto condition);

    int batchInsert(List<WxUser> list);

    List<WxUser> batchQueryByIds(List<Long> ids);

    List<WxUser> batchQueryByUuids(List<String> uuids);

    int delete(Long id);

    int delete(String uuid);

    int delete(WxUser bean);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

    WxUser findByOpenid(WxMpUser wxMpUser);

    WxUser findByOpenid(String openid);

    WxUser getByUuid(String wxUserUuid);
}

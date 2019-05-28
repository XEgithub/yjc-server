package com.dk.service;

import cn.sourcespro.commons.data.vo.NodeVo;
import com.dk.data.dto.SearchClassifyDto;
import com.dk.data.entity.Classify;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 微信用户 Service
 *
 * @author ban
 * @date 2018/12/10
 */
public interface ClassifyService {

    Classify add(Classify bean);

    Classify update(Classify bean);

    Classify findById(Long id);

    Classify findByUuid(String uuid);

    IPage<Classify> findAll(SearchClassifyDto condition);

    int count(SearchClassifyDto condition);

    List<Classify> findByPid(Long pid);

    List<Classify> findByLevel(int level);

    List<Classify> findByPidCascaded(Long pid);

    List<NodeVo<Long>> findNodeByPidCascaded(Long pid);

    int batchInsert(List<Classify> list);

    List<Classify> batchQueryByIds(List<Long> ids);

    List<Classify> batchQueryByUuids(List<String> uuids);

    int delete(Long id);

    int delete(String uuid);

    int delete(Classify bean);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

}

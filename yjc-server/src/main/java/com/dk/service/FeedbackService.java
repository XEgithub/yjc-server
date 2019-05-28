package com.dk.service;

import com.dk.data.dto.SearchFeedbackDto;
import com.dk.data.entity.Feedback;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 反馈 Service
 *
 * @author ban
 * @date 2019/01/10
 */
public interface FeedbackService {

    Feedback add(Feedback bean);

    Feedback update(Feedback bean);

    Feedback findById(Long id);

    Feedback findByUuid(String uuid);

    IPage<Feedback> findAll(SearchFeedbackDto condition);

    int count(SearchFeedbackDto condition);

    int batchInsert(List<Feedback> list);

    List<Feedback> batchQueryByIds(List<Long> ids);

    List<Feedback> batchQueryByUuids(List<String> uuids);

    int delete(Long id);

    int delete(String uuid);

    int delete(Feedback bean);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

}

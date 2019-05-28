package com.dk.data.dao;

import com.dk.data.dto.SearchFeedbackDto;
import com.dk.data.entity.Feedback;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 反馈 Mapper
 *
 * @author ban
 * @date 2019/01/10
 */
@Mapper
public interface FeedbackMapper extends BaseMapper<Feedback> {
    
    int deleteByPrimaryKey(Long id);

    IPage<Feedback> findAll(Page page, @Param("condition") SearchFeedbackDto condition);

    int count(@Param("condition") SearchFeedbackDto condition);

    int batchInsert(List<Feedback> list);

    List<Feedback> batchQueryByIds(List<Long> ids);

    List<Feedback> batchQueryByUuids(List<String> uuids);

    Feedback findByUuid(String uuid);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

    int deleteByUuid(String uuid);

}
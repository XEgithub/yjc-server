package com.dk.data.dao;

import com.dk.data.dto.SearchCommentDto;
import com.dk.data.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 评论 Mapper
 *
 * @author ban
 * @date 2018/12/12
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
    
    int deleteByPrimaryKey(Long id);

    IPage<Comment> findAll(Page page, @Param("condition") SearchCommentDto condition);

    int count(@Param("condition") SearchCommentDto condition);

    int batchInsert(List<Comment> list);

    List<Comment> batchQueryByIds(List<Long> ids);

    List<Comment> batchQueryByUuids(List<String> uuids);

    Comment findByUuid(String uuid);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

    int deleteByUuid(String uuid);

    int deleteByUserAndOrder(@Param("user") String user, @Param("uuid") String uuid);

    int countByUser(@Param("user") String user);

    Comment findByUserAndOrder(@Param("user") String user, @Param("uuid") String uuid);

    IPage<Comment> getAll(Page page, @Param("condition") SearchCommentDto condition);

    IPage<Comment> findByNurse(Page page, @Param("condition") SearchCommentDto condition);

    int countByNurse(@Param("condition") SearchCommentDto condition);
}
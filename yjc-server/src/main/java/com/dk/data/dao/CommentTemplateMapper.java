package com.dk.data.dao;

import com.dk.data.dto.SearchCommentTemplateDto;
import com.dk.data.entity.CommentTemplate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 评论模板 Mapper
 *
 * @author ban
 * @date 2019/01/03
 */
@Mapper
public interface CommentTemplateMapper extends BaseMapper<CommentTemplate> {
    
    int deleteByPrimaryKey(Long id);

    IPage<CommentTemplate> findAll(Page page, @Param("condition") SearchCommentTemplateDto condition);

    int count(@Param("condition") SearchCommentTemplateDto condition);

    int batchInsert(List<CommentTemplate> list);

    List<CommentTemplate> batchQueryByIds(List<Long> ids);

    List<CommentTemplate> batchQueryByUuids(List<String> uuids);

    CommentTemplate findByUuid(String uuid);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

    int deleteByUuid(String uuid);

}
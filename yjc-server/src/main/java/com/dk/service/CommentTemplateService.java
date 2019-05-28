package com.dk.service;

import com.dk.data.dto.SearchCommentTemplateDto;
import com.dk.data.entity.CommentTemplate;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 评论模板 Service
 *
 * @author ban
 * @date 2019/01/03
 */
public interface CommentTemplateService {

    CommentTemplate add(CommentTemplate bean);

    CommentTemplate update(CommentTemplate bean);

    CommentTemplate findById(Long id);

    CommentTemplate findByUuid(String uuid);

    IPage<CommentTemplate> findAll(SearchCommentTemplateDto condition);

    int count(SearchCommentTemplateDto condition);

    int batchInsert(List<CommentTemplate> list);

    List<CommentTemplate> batchQueryByIds(List<Long> ids);

    List<CommentTemplate> batchQueryByUuids(List<String> uuids);

    int delete(Long id);

    int delete(String uuid);

    int delete(CommentTemplate bean);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

}

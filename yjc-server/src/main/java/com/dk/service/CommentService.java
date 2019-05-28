package com.dk.service;

import com.dk.data.dto.SearchCommentDto;
import com.dk.data.entity.Comment;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 评论 Service
 *
 * @author ban
 * @date 2018/12/12
 */
public interface CommentService {

    Comment add(Comment bean);

    Comment update(Comment bean);

    Comment findById(Long id);

    Comment findByUuid(String uuid);

    IPage<Comment> findAll(SearchCommentDto condition);

    int count(SearchCommentDto condition);

    int batchInsert(List<Comment> list);

    List<Comment> batchQueryByIds(List<Long> ids);

    List<Comment> batchQueryByUuids(List<String> uuids);

    int delete(Long id);

    int delete(String uuid);

    int delete(Comment bean);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

    String addImage(String route, MultipartFile[] imageFiles);

    int deleteByUserAndOrder(String user, String uuid);

    int countByUser(String user);

    Comment findByUserAndOrder(String user, String uuid);

    Boolean deleteImage(String route);

    IPage<Comment> getAll(SearchCommentDto condition);

    IPage<Comment> findByNurse(SearchCommentDto condition);
}

package com.dk.service.impl;

import cn.sourcespro.commons.service.IdWoker;
import cn.sourcespro.commons.utils.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dk.data.dao.CommentMapper;
import com.dk.data.dto.SearchCommentDto;
import com.dk.data.entity.Comment;
import com.dk.service.CommentService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dk.upload.ImageUtil;
import com.dk.upload.WebAppConfig;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 评论 ServiceImpl
 *
 * @author ban
 * @date 2018/12/12
 */
@Service
public class CommentServiceImpl implements CommentService {

    private static final Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);

    @Autowired
    private CommentMapper mapper;
    @Autowired
    private IdWoker idWoker;

    @Autowired
    WebAppConfig webAppConfig;

    @Override
    public Comment add(Comment bean) {

        initBasicVals(bean);

        int result = mapper.insert(bean);
        if (result > 0) {
            return bean;
        }
        return null;
    }

    @Override
    public Comment update(Comment bean) {
        Long id = bean.getId();
        if (id == null) {
            return null;
        }
        Comment dbComment = mapper.selectById(id);
        if (dbComment == null) {
            return null;
        }
        dbComment.setUpdateTime(new Date());
        BeanUtil.copyProperties(bean, dbComment);
        int result = mapper.updateById(dbComment);
        if (result > 0){
            return dbComment;
        }
        return null;
    }

    @Override
    public Comment findById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public Comment findByUuid(String uuid) {
        return mapper.findByUuid(uuid);
    }

    @Override
    public IPage<Comment> findAll(SearchCommentDto condition) {
        Page page = condition.getPage();
        page.setTotal(mapper.count(condition));
        return mapper.findAll(page, condition);
    }

    @Override
    public int count(SearchCommentDto condition) {
        return mapper.count(condition);
    }

    @Override
    public int batchInsert(List<Comment> list) {
        for (Comment bean : list) {
            initBasicVals(bean);
        }
        return mapper.batchInsert(list);
    }

    @Override
    public List<Comment> batchQueryByIds(List<Long> ids) {
        return mapper.batchQueryByIds(ids);
    }

    @Override
    public List<Comment> batchQueryByUuids(List<String> uuids) {
        return mapper.batchQueryByUuids(uuids);
    }

    @Override
    public int delete(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int delete(String uuid) {
        return mapper.deleteByUuid(uuid);
    }

    @Override
    public int delete(Comment bean) {
        return delete(bean.getId());
    }

    @Override
    public int batchDeleteById(List<Long> ids) {
        if (CollectionUtils.isNotEmpty(ids)) {
            return mapper.batchDeleteById(ids);
        }
        return 0;
    }

    @Override
    public int batchDeleteByUuid(List<String> uuids) {
        if (CollectionUtils.isNotEmpty(uuids)) {
            return mapper.batchDeleteByUuid(uuids);
        }
        return 0;
    }

    @Override
    public String addImage(String route, MultipartFile[] imageFiles) {
        String imageUrl = "";
        if (imageFiles.length != 0) {
            for (int i = 0; i < imageFiles.length; i++) {
                MultipartFile imageFile = imageFiles[i];
                if (imageFile != null) {
                    String contentType = imageFile.getContentType();
                    if (contentType != null) {
                        String return_path = ImageUtil.getFilePath(route);
                        String filePath = webAppConfig.getLocation() + return_path;
                        String file_name = null;
                        try {
                            file_name = ImageUtil.saveImg(imageFile, filePath);
                            imageUrl += return_path + File.separator + file_name;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (i < imageFiles.length - 1) {
                        imageUrl += ",";
                    }
                }
            }
        }
        return imageUrl;
    }

    @Override
    public int countByUser(String user) {
        return mapper.countByUser(user);
    }

    @Override
    public Comment findByUserAndOrder(String user, String uuid) {
        return mapper.findByUserAndOrder(user, uuid);
    }

    @Override
    public Boolean deleteImage(String route) {
        String path = webAppConfig.getLocation() + route;
        File file = new File(path);
        return file.delete();
    }

    @Override
    public IPage<Comment> getAll(SearchCommentDto condition) {
        Page page = condition.getPage();
        page.setTotal(mapper.count(condition));
        return mapper.getAll(page, condition);
    }

    @Override
    public IPage<Comment> findByNurse(SearchCommentDto condition) {
        Page page = condition.getPage();
        page.setTotal(mapper.countByNurse(condition));
        return mapper.findByNurse(page, condition);
    }

    @Override
    public int deleteByUserAndOrder(String user, String uuid) {
        return mapper.deleteByUserAndOrder(user, uuid);
    }

    private void initBasicVals(Comment bean){
        bean.setUuid(idWoker.nextStringId());
        bean.setCreateTime(new Date());
        bean.setUpdateTime(new Date());
        bean.setDeleted(false);
    }
}
package com.dk.service;

import cn.sourcespro.commons.data.vo.NodeVo;
import com.dk.data.dto.SearchMenuDto;
import com.dk.data.entity.Menu;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 菜单 Service
 *
 * @author ban
 * @date 2018/11/28
 */
public interface MenuService {

    Menu add(Menu bean);

    Menu update(Menu bean);

    Menu findById(Long id);

    Menu findByUuid(String uuid);

    IPage<Menu> findAll(SearchMenuDto condition);

    int count(SearchMenuDto condition);

    List<Menu> findByPid(Long pid);

    List<Menu> findByLevel(int level);

    List<Menu> findByPidCascaded(Long pid);

    List<NodeVo<Long>> findNodeByPidCascaded(Long pid);

    int batchInsert(List<Menu> list);

    List<Menu> batchQueryByIds(List<Long> ids);

    List<Menu> batchQueryByUuids(List<String> uuids);

    int delete(Long id);

    int delete(String uuid);

    int delete(Menu bean);

    int batchDeleteById(List<Long> ids);

    int batchDeleteByUuid(List<String> uuids);

}

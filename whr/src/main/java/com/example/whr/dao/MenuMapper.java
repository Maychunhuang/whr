package com.example.whr.dao;

import com.example.whr.bean.Menu;

import java.util.List;

/**
 * @author huangchunmei
 * @create 2019/8/14 15:53
 */
public interface MenuMapper {
    /**
     * 查询所有菜单和角色的对应关系
     *
     * @return
     */
    List<Menu> getAllMenu();

    /**
     * 根据hrId获取相应菜单，有权限区别
     *
     * @param hrId
     * @return
     */
    List<Menu> getMenusByHrId(Long hrId);

    /**
     * 菜单树
     * @return
     */
    List<Menu> menuTree();

    List<Long> getMenusByRid(Long rid);
}

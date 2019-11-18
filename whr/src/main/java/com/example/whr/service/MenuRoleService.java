package com.example.whr.service;

import com.example.whr.dao.MenuRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MenuRoleService {
    @Autowired
    private MenuRoleMapper menuRoleMapper;

    //更新菜单角色关联，先删除旧的，再添加新的
    public int updateMenuRole(Long rid, Long[] mids) {
        //先删除旧的关联
        menuRoleMapper.deleteMenuByRid(rid);
        if (mids.length == 0) {
            return 0;
        }
        //再把本次的菜单和角色关联添加进去
        return menuRoleMapper.addMenu(rid, mids);
    }
}

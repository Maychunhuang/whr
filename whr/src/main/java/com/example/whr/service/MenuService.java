package com.example.whr.service;

import com.example.whr.bean.Menu;
import com.example.whr.common.HrUtils;
import com.example.whr.dao.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author huangchunmei
 * @create 2019/8/14 15:40
 */
@Service
@Transactional
public class MenuService {
    @Autowired
    private MenuMapper menuMapper;

    public List<Menu> getAllMenu() {
        return menuMapper.getAllMenu();
    }

    /**
     * 获取当前用户的菜单
     * @return
     */
    public List<Menu> getMenusByHrId() {
        return menuMapper.getMenusByHrId(HrUtils.getCurrentHr().getId());
    }

    public List<Menu> menuTree(){
        return menuMapper.menuTree();
    }

    public List<Long> getMenusByrid(Long rid){
        return menuMapper.getMenusByRid(rid);
    }
}

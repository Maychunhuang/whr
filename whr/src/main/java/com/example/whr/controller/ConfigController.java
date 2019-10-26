package com.example.whr.controller;

import com.example.whr.common.HrUtils;
import com.example.whr.bean.Hr;
import com.example.whr.bean.Menu;
import com.example.whr.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author huangchunmei
 * @create 2019/9/9 19:33
 */
@RestController
@RequestMapping("/config")
public class ConfigController {
    @Autowired
    private MenuService menuService;

    /**
     * 获取登录用户的菜单
     *
     * @return
     */
    @GetMapping("/sysmenu")
    public List<Menu> sysmenu() {
        return menuService.getMenusByHrId();
    }

    /**
     * 获取当前用户信息
     *
     * @return
     */
    @RequestMapping("/hr")
    public Hr currentHr() {
        return HrUtils.getCurrentHr();
    }
}

package com.example.whr.entity;

import lombok.Data;

/**
 * @author huangchunmei
 * @create 2019/8/13 10:55
 */
@Data
public class Role {
    /**
     * 主键id
     */
    private Long id;
    /**
     * 角色英文名
     */
    private String name;
    /**
     * 角色中文名
     */
    private String nameZh;
}

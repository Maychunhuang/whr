package com.example.whr.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author huangchunmei
 * @create 2019/8/13 10:55
 */
@Data
public class Role implements Serializable {
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

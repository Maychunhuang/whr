package com.example.whr.dao;

import com.example.whr.bean.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色
 */
public interface RoleMapper {
    List<Role> roles();

    int addNewRole(@Param("role") String role, @Param("roleZh") String roleZh);

    int deleteRoleById(Long rid);
}

package com.example.whr.dao;

import com.example.whr.entity.Hr;
import com.example.whr.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author huangchunmei
 * @create 2019/8/13 11:32
 */
public interface HrMapper {
    /**
     * 根据用户名加载用户
     *
     * @param username
     * @return
     */
    Hr loadUserByUsername(String username);

    /**
     * 根据用户id获取角色列表
     *
     * @param id
     * @return
     */
    List<Role> getRolesByHrId(Long id);

    /**
     * 用户注册
     *
     * @param username
     * @param password
     */
    int hrRegister(@Param("username") String username, @Param("password") String password);

    /**
     * 根据关键词查询Hr
     *
     * @param keywords
     * @return
     */
    List<Hr> getHrsByKeywords(@Param("keywords") String keywords);

    /**
     * 更新Hr
     *
     * @param hr
     * @return
     */
    int updateHr(Hr hr);

    /**
     * 删除某个Hr的角色
     *
     * @param hrId
     * @return
     */
    int deleteRoleByHrId(Long hrId);

    /**
     * 给某个Hr添加角色
     *
     * @param hrId
     * @param rids
     * @return
     */
    int addRolesForHr(@Param("hrId") Long hrId, @Param("rids") Long[] rids);

    /**
     * 根据Id获取某个Hr信息
     *
     * @param hrId
     * @return
     */
    Hr getHrById(Long hrId);

    /**
     * 删除某个Hr
     *
     * @param hrId
     * @return
     */
    int deleteHr(Long hrId);

    /**
     * 获取所有Hr信息
     *
     * @param currentId
     * @return
     */
    List<Hr> getAllHr(@Param("currentId") Long currentId);
}

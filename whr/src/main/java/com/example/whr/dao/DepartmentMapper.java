package com.example.whr.dao;

import com.example.whr.entity.Department;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author huangchunmei
 * @create 2019/9/1 23:34
 */
public interface DepartmentMapper {
    /**
     * 获取相应层级的部门信息
     * @param pid
     * @return
     */
     List<Department> getDepByPid(Long pid);

    /**
     * 添加部门
     * @param dept
     */
     void addDep(@Param("dept") Department dept);
}

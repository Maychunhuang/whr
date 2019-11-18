package com.example.whr.service;

import com.example.whr.bean.Department;
import com.example.whr.dao.DepartmentMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;


    public List<Department> getDepByPid(Long pid){
        return departmentMapper.getDepByPid(pid);
    }

    public int addDep(Department dept){
        dept.setEnabled(true);
        //调用的是存储过程
        departmentMapper.addDep(dept);
        return dept.getResult();
    }

    public int deleteDep(Long did){
        Department department = new Department();
        department.setId(did);
        departmentMapper.deleteDep(department);
        return department.getResult();
    }

    public List<Department> getAllDeps() {
        return departmentMapper.getAllDeps();
    }

}

package com.example.whr.controller.salary;

import com.example.whr.bean.Department;
import com.example.whr.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author huangchunmei
 * @create 2019/9/18 9:19
 */
@Controller
@RequestMapping("/salary/table")
public class SalaryTableController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/deps")
    public List<Department> departments() {
        return departmentService.getAllDeps();
    }
}

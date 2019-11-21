package com.example.whr.controller.salary;

import com.example.whr.bean.Employee;
import com.example.whr.bean.RespBean;
import com.example.whr.bean.Salary;
import com.example.whr.service.EmpService;
import com.example.whr.service.SalaryService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @author huangchunmei
 * @create 2019/9/18 9:18
 */
@Controller
@RequestMapping("/salary/sobcfg")
public class SalaryEmpController {
    @Autowired
    private SalaryService salaryService;
    @Autowired
    private EmpService empService;

    @GetMapping("/salaries")
    public List<Salary> salaries() {
        return salaryService.getAllSalary();
    }

    @PutMapping("/")
    public RespBean updateEmpSalary(Integer sid, Long eid) {
        if (salaryService.updateEmpSalary(sid, eid) == 1) {
            return RespBean.ok("修改成功");
        }
        return RespBean.error("修改失败");

    }

    @GetMapping("/emp")
    public Map<String, Object> getEmployeeByPage(@RequestParam(defaultValue = "1") Integer page,
                                                 @RequestParam(defaultValue = "10") Integer size) {
        Map<String, Object> map = Maps.newHashMap();
        List<Employee> emps = empService.getEmployeeByPageShort(page, size);
        Long count = empService.getCountByKeywords("", null, null,
                null, null, null, null, null);
        map.put("emps", emps);
        map.put("count", count);
        return map;
    }
}

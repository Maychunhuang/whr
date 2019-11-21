package com.example.whr.controller.salary;

import com.example.whr.bean.RespBean;
import com.example.whr.bean.Salary;
import com.example.whr.service.SalaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author huangchunmei
 * @create 2019/9/18 9:18
 */
@RestController
@RequestMapping("/salary/sob")
public class SalaryController {
    @Autowired
    private SalaryService salaryService;
    @GetMapping("/salary")
    public List<Salary> salaries(){
        return salaryService.getAllSalary();
    }

    @DeleteMapping("/salary/{ids}")
    public RespBean deleteSalary(@PathVariable String ids){
        if(salaryService.deleteSalary(ids)){
            return RespBean.ok("删除成功！");
        }
        return RespBean.error("删除失败！");
    }

    @PutMapping("/salary")
    public RespBean updateSalary(Salary salary){
        if(salaryService.updateSalary(salary) == 1){
            return RespBean.ok("修改成功");
        }
        return RespBean.error("修改失败");
    }

    @PostMapping("/salary")
    public RespBean addSalary(Salary salary){
        if(salaryService.addSalary(salary) == 1){
            return RespBean.ok("添加成功！");
        }
        return RespBean.error("添加失败！");
    }
}

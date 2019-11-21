package com.example.whr.service;

import com.example.whr.bean.Salary;
import com.example.whr.dao.SalaryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SalaryService {
    @Autowired
    private SalaryMapper salaryMapper;

    public List<Salary> getAllSalary(){
        return salaryMapper.getAllSalary();
    }

    public int addSalary(Salary salary){
        return salaryMapper.addSalary(salary);
    }

    public int updateSalary(Salary salary){
        return salaryMapper.updateSalary(salary);
    }

    public boolean deleteSalary(String ids){
        String[] split = ids.split(",");
        return salaryMapper.deleteSalary(split) == split.length;
    }

    public int updateEmpSalary(Integer sid,Long eid){
        salaryMapper.deleteSalaryByEid(eid);
        return salaryMapper.addSidAndEid(sid,eid);
    }

}

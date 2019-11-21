package com.example.whr.service;

import com.example.whr.bean.Employee;
import com.example.whr.bean.Nation;
import com.example.whr.bean.PoliticsStatus;
import com.example.whr.dao.EmpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class EmpService {
    @Autowired
    private EmpMapper empMapper;

    //SimpleDateFormat是线程不安全的
    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
    SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
    SimpleDateFormat birthdayFormat = new SimpleDateFormat("yyyy-MM-dd");
    //金钱 格式化，只保留两位小数
    DecimalFormat decimalFormat = new DecimalFormat("##.00");

    public List<Nation> getAllNations() {
        return empMapper.getAllNations();
    }

    public List<PoliticsStatus> getAllPolitics() {
        return empMapper.getAllPolitics();
    }

    //添加员工
    public int addEmp(Employee employee) {
        //计算合同期间
        Date beginContract = employee.getBeginContract();
        Date endContract = employee.getEndContract();
        //计算相差了多少个月，jAVA8可以用另一个类代替，具体的忘了，结束后查一下
        Double contractTerm = (Double.parseDouble(yearFormat.format(endContract)) - Double.parseDouble(yearFormat.format(beginContract)))
                * 12 + Double.parseDouble(monthFormat.format(endContract)) - Double.parseDouble(monthFormat.format(beginContract));
        employee.setContractTerm(Double.parseDouble(decimalFormat.format(contractTerm / 12)));
        return empMapper.addEmp(employee);
    }

    //获取目前员工中最大的工号
    public Long getMaxWorkId() {
        Long maxWorkId = empMapper.getMaxWorkId();
        return maxWorkId == null ? 0 : maxWorkId;
    }

    //获取分页员工，包含高级搜索
    public List<Employee> getEmployeeByPage(Integer page, Integer size, String keywords, Long politicId,
                                            Long nationId, Long posId, Long jobLevelId, String engageForm,
                                            Long departmentId, String beginDateScope) {
        int start = (page - 1) * size;
        Date startBeginDate = null;
        Date endBeginDate = null;
        if (beginDateScope != null && beginDateScope.contains(",")) {
            try {
                String[] splits = beginDateScope.split(",");
                //捕获异常
                startBeginDate = birthdayFormat.parse(splits[0]);
                endBeginDate = birthdayFormat.parse(splits[1]);
            } catch (ParseException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        return empMapper.getEmployeeByPage(start, size, keywords, politicId, nationId, posId, jobLevelId,
                engageForm, departmentId, startBeginDate, endBeginDate);
    }


    public Long getCountByKeywords(String keywords, Long politicId, Long nationId, Long posId, Long jobLevelId,
                                   String engageForm, Long departmentId, String beginDateScope) {
        Date startBeginDate = null;
        Date endBeginDate = null;
        if (beginDateScope != null && beginDateScope.contains(",")) {
            try {
                String[] split = beginDateScope.split(",");
                startBeginDate = birthdayFormat.parse(split[0]);
                endBeginDate = birthdayFormat.parse(split[1]);
            } catch (ParseException e) {
            }
        }
        return empMapper.getCountByKeywords(keywords, politicId, nationId, posId, jobLevelId, engageForm,
                departmentId, startBeginDate, endBeginDate);
    }

    //更新员工
    public int updateEmp(Employee employee) {
        //计算合同期间
        Date beginContract = employee.getBeginContract();
        Date endContract = employee.getEndContract();
        //计算相差了多少个月，jAVA8可以用另一个类代替，具体的忘了，结束后查一下
        Double contractTerm = (Double.parseDouble(yearFormat.format(endContract)) - Double.parseDouble(yearFormat.format(beginContract)))
                * 12 + Double.parseDouble(monthFormat.format(endContract)) - Double.parseDouble(monthFormat.format(beginContract));
        employee.setContractTerm(Double.parseDouble(decimalFormat.format(contractTerm / 12)));
        return empMapper.updateEmp(employee);
    }

    //删除员工，单个删除和批量删除
    public boolean deleteEmpById(String ids) {
        String[] split = ids.split(",");
        return empMapper.deleteEmpById(split) == split.length;
    }

    //用于导出
    public List<Employee> getAllEmployees() {
        return empMapper.getEmployeeByPage(null, null, "", null, null,
                null, null, null, null, null,
                null);
    }

    //批量添加员工信息 导入功能
    public int addEmps(List<Employee> emps) {
        return empMapper.addEmps(emps);
    }


    public List<Employee> getEmployeeByPageShort(Integer page, Integer size) {
        int start = (page - 1) * size;
        return empMapper.getEmployeeByPageShort(start, size);
    }
}

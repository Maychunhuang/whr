package com.example.whr.controller.emp;

import com.example.whr.bean.Employee;
import com.example.whr.bean.Position;
import com.example.whr.bean.RespBean;
import com.example.whr.common.EmailRunnable;
import com.example.whr.common.poi.PoiUtils;
import com.example.whr.service.DepartmentService;
import com.example.whr.service.EmpService;
import com.example.whr.service.JobLevelService;
import com.example.whr.service.PositionService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * @author huangchunmei
 * @create 2019/9/18 9:17
 */
@RestController
@RequestMapping("/employee/basic")
public class EmpBasicController {
    @Autowired
    private EmpService empService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private PositionService positionService;
    @Autowired
    private JobLevelService jobLevelService;

    //线程池
    @Autowired
    private ExecutorService executorService;

    private JavaMailSender javaMailSender;
    @Value("15195957868@163.com")
    private String emailAddress;

    @GetMapping("/basicdata")
    public Map<String, Object> getAllNations() {
        Map<String, Object> map = Maps.newHashMap();
        map.put("nations", empService.getAllNations());
        map.put("politics", empService.getAllPolitics());
        map.put("deps", departmentService.getDepByPid(-1l));
        map.put("positions", positionService.getAllPos());
        map.put("joblevels", jobLevelService.getAllJobLevels());
        return map;
    }

    @GetMapping("/maxWorkID")
    public String maxWorkID() {
        return String.format("%08d", empService.getMaxWorkId() + 1);
    }

    /**
     * 添加员工，同时发送邮件，因为邮件是阻塞操作，所有要放到子线程里去操作
     * @param employee
     * @return
     */
    @PostMapping("/emp")
    public RespBean addEmp(Employee employee) {
        if (empService.addEmp(employee) == 1) {
            //设置这位员工的岗位名称
            List<Position> allPos = positionService.getAllPos();
            for (Position pos:allPos){
                if(pos.getId() == employee.getPosId()){
                    employee.setPosName(pos.getName());
                }
            }
            //创建一个线程，发送邮件
            executorService.execute(new EmailRunnable(employee,javaMailSender,
                    emailAddress));
            return RespBean.ok("添加成功！");
        }
        return RespBean.error("添加失败");
    }

    @PutMapping("/emp")
    public RespBean updateEmp(Employee employee) {
        if (empService.updateEmp(employee) == 1) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败！");
    }

    @DeleteMapping("/emp/{ids}")
    public RespBean deleteEmp(@PathVariable String ids) {
        if (empService.deleteEmpById(ids)) {
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @GetMapping("/emp")
    public Map<String, Object> getEmpByPage(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "") String keywords,
            Long politicId, Long nationId, Long posId,
            Long jobLevelId, String engageForm,
            Long departmentId, String beginDateScope
    ) {
        Map<String, Object> map = Maps.newHashMap();
        List<Employee> emps = empService.getEmployeeByPage(page, size, keywords, politicId, nationId, posId,
                jobLevelId, engageForm, departmentId, beginDateScope);
        Long count = empService.getCountByKeywords(keywords, politicId, nationId, posId, jobLevelId, engageForm,
                departmentId, beginDateScope);
        map.put("emps", emps);
        map.put("count", count);
        return map;
    }

    /**
     * excel 导出功能
     *
     * @return ResponseEntity<byte [ ]>  响应实体，字节数组
     */
    @GetMapping("/exportEmp")
    public ResponseEntity<byte[]> exportEmp() {
        return PoiUtils.exportEmp2excel(empService.getAllEmployees());
    }

    /**
     * excel 导入功能
     *
     * @return
     */
    @PostMapping("/importEmp")
    public RespBean importEmp(MultipartFile multipartFile) {
        List<Employee> emps = PoiUtils.importEmp2List(multipartFile,
                empService.getAllNations(),
                empService.getAllPolitics(),
                departmentService.getAllDeps(),
                positionService.getAllPos(),
                jobLevelService.getAllJobLevels());
        if (empService.addEmps(emps) == emps.size()) {
            return RespBean.ok("导入成功");
        }
        return RespBean.error("导入失败");
    }
}

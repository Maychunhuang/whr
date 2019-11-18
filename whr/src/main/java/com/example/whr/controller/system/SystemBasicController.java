package com.example.whr.controller.system;

import com.example.whr.bean.*;
import com.example.whr.service.*;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author huangchunmei
 * @create 2019/9/18 9:19
 */
@RestController
@RequestMapping("/system/basic")
public class SystemBasicController {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private PositionService positionService;
    @Autowired
    private JobLevelService jobLevelService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private MenuRoleService menuRoleService;

    /**
     * 根据部门id获取部门信息
     *
     * @param pid
     * @return
     */
    @GetMapping("/dep/{pid}")
    public List<Department> getDepByPid(@PathVariable("pid") Long pid) {
        return departmentService.getDepByPid(pid);
    }

    /**
     * 添加部门
     *
     * @param dept
     * @return
     */
    @PostMapping("/dep")
    public Map<String, Object> addDep(Department dept) {
        Map<String, Object> map = Maps.newHashMap();
        //添加成功
        if (1 == departmentService.addDep(dept)) {
            map.put("status", "success");
            map.put("msg", dept);
            return map;
        }
        map.put("status", "error");
        map.put("msg", "添加失败");
        return map;
    }

    /**
     * 删除部门
     *
     * @param did
     * @return
     */
    @DeleteMapping("/dep/{did}")
    public RespBean deleteDep(@PathVariable("did") Long did) {
        if (departmentService.deleteDep(did) == 1) {
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }

    /**
     * 获取所有部门
     *
     * @return
     */
    @GetMapping("/deps")
    public List<Department> getAllDeps() {
        return departmentService.getAllDeps();
    }

    /**
     * 获取所有职位
     *
     * @return
     */
    @GetMapping("/positions")
    public List<Position> getAllPos() {
        return positionService.getAllPos();
    }

    /**
     * 更新职位
     *
     * @param position
     * @return
     */
    @PutMapping("/position")
    public RespBean updatePosById(Position position) {
        if (positionService.updatePosById(position) == 1) {
            return RespBean.ok("修改成功");
        }
        return RespBean.error("修改失败");
    }

    /**
     * 添加职位
     *
     * @param position
     * @return
     */
    @PostMapping("/position")
    public RespBean addPos(Position position) {
        int result = positionService.addPos(position);
        if (result == 1) {
            return RespBean.ok("添加成功！");
        } else if (result == -1) {
            return RespBean.error("职位名称重复，添加失败！");
        }
        return RespBean.error("添加失败！");
    }

    /**
     * 单个删除或批量删除职位
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/position/{ids}")
    public RespBean deletePosById(@PathVariable("ids") String ids) {
        if (positionService.deletePosById(ids)) {
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }

    /**
     * 获取所有职称
     *
     * @return
     */
    @GetMapping("/joblevels")
    public List<JobLevel> getAllJobLevels() {
        return jobLevelService.getAllJobLevels();
    }

    /**
     * 更新职称
     *
     * @param jobLevel
     * @return
     */
    @PutMapping("/joblevel")
    public RespBean updateJobLevel(JobLevel jobLevel) {
        if (jobLevelService.updateJobLevel(jobLevel) == 1) {
            return RespBean.ok("修改成功");
        }
        return RespBean.error("修改失败");
    }

    /**
     * 添加职称
     *
     * @param jobLevel
     * @return
     */
    @PostMapping("/joblevel")
    public RespBean addJobLevel(JobLevel jobLevel) {
        int result = jobLevelService.addJobLevel(jobLevel);
        if (result == 1) {
            return RespBean.ok("添加成功！");
        } else if (result == -1) {
            return RespBean.error("职称名重复，添加失败！");
        }
        return RespBean.error("添加失败！");
    }

    /**
     * 单个删除或批量删除职位
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/joblevel/{ids}")
    public RespBean deleteJobLevelById(@PathVariable String ids) {
        if (jobLevelService.deleteJobLevelById(ids)) {
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }

    /**
     * 获取所有角色
     *
     * @return
     */
    @GetMapping("/roles")
    public List<Role> roles() {
        return roleService.roles();
    }

    @DeleteMapping("/role/{rid}")
    public RespBean deleteRoleById(@PathVariable Long rid) {
        if (roleService.deleteRoleById(rid) == 1) {
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @PostMapping("/addRole")
    public RespBean addRole(String role, String roleZh) {
        if (roleService.addNewRole(role, roleZh) == 1) {
            return RespBean.ok("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @PutMapping("/updateMenuRole")
    public RespBean updateMenuRole(Long rid, Long[] mids) {
        if (menuRoleService.updateMenuRole(rid, mids) == mids.length) {
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @GetMapping("/menuTree/{rid}")
    public Map<String, Object> menuTree(@PathVariable Long rid) {
        Map<String, Object> map = Maps.newHashMap();
        List<Menu> menus = menuService.menuTree();
        map.put("menus", menus);
        List<Long> mids = menuService.getMenusByrid(rid);
        map.put("mids", mids);
        return map;
    }
}

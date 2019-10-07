package com.example.whr.service;

import com.example.whr.common.HrUtils;
import com.example.whr.dao.HrMapper;
import com.example.whr.entity.Hr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 加上事务，及回滚的时间点
 *
 * @author huangchunmei
 * @create 2019/8/13 11:25
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class HrService implements UserDetailsService{
    /**
     * 执行登录的过程中，这个方法将根据用户名去查找用户，
     * 如果用户不存在，则抛出UsernameNotFoundException异常，否则直接将查到的Hr返回
     * HrMapper用来执行数据库的查询操作
     * @param s
     * @return
     * @throws UsernameNotFoundException
     */
    @Autowired
    private HrMapper hrMapper;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Hr hr = hrMapper.loadUserByUsername(s);
        if(hr == null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        return hr;
    }

    //hr用户注册
    public int hrRegister(String username,String password){
        //如果用户名存在，返回提示信息，不允许注册
        if(hrMapper.loadUserByUsername(username) != null){
            return -1;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode(password);
        return hrMapper.hrRegister(username,encode);
    }

    /**
     * 根据关键字查询Hr用户列表
     * @param keywords
     * @return
     */
    public List<Hr> getHrsByKeywords(String keywords){
        return hrMapper.getHrsByKeywords(keywords);
    }

    /**
     * 更新用户hr信息
     * @param hr
     * @return
     */
    public int updateHr(Hr hr){
        return hrMapper.updateHr(hr);
    }

    /**
     * 根据用户Hr id删除对应的角色关系
     * @param hrId
     * @return
     */
    public int deleteRoleByHrId(Long hrId){
        return hrMapper.deleteRoleByHrId(hrId);
    }

    /**
     * 给某个用户Hr 添加角色
     * @param hrId
     * @param rids
     * @return
     */
    public int addRolesForHr(Long hrId,Long[] rids){
        return hrMapper.addRolesForHr(hrId,rids);
    }

    /**
     * 根据用户Hr id 获取用户信息
     * @param hrId
     * @return
     */
    public Hr getHrById(Long hrId){
        return hrMapper.getHrById(hrId);
    }

    /**
     * 根据hrId 删除 用户信息
     * @param hrId
     * @return
     */
    public int deleteHr(Long hrId){
        return hrMapper.deleteHr(hrId);
    }

    /**
     * 获取除了当前用户的所有其他用户信息
     * @return
     */
    public List<Hr> getAllHrExceptAdmin(){
        return hrMapper.getAllHr(HrUtils.getCurrentHr().getId());
    }

    public List<Hr> getAllHr(){
        return hrMapper.getAllHr(null);
    }
}

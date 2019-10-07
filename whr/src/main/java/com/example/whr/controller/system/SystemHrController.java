package com.example.whr.controller.system;

import com.example.whr.dao.HrMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author huangchunmei
 * @create 2019/8/26 14:09
 */
@Controller
public class SystemHrController {
    @Autowired
    private HrMapper hrMapper;
    /**
     * 用户注册
     * @param username
     * @param password
     * @return
     */
    @PostMapping
    public int hrReg(String username,String password){
        if(hrMapper.loadUserByUsername(username) != null) {
            return -1;
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String enPassword = encoder.encode(password);
        hrMapper.hrRegister(username,enPassword);
        return 0;
    }
}

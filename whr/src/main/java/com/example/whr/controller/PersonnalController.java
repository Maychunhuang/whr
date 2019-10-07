package com.example.whr.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangchunmei
 * @create 2019/9/18 9:11
 */
@RestController
@RequestMapping("/personnal")
public class PersonnalController {
    @RequestMapping("/")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/emp/hello")
    public String helloEmp() {
        return "hello emp";
    }
}

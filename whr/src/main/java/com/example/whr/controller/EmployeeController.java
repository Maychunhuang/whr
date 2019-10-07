package com.example.whr.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangchunmei
 * @create 2019/9/18 9:02
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @RequestMapping("/basic")
    public String basic(){
        return "basic";
    }

    @RequestMapping("/")
    public String hello(){
        return "/hello";
    }
}

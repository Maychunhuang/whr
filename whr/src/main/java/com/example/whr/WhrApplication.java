package com.example.whr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan(value = "com.example.whr.dao")
@SpringBootApplication
public class WhrApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhrApplication.class, args);
    }

}

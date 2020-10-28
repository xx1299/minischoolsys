package com.s1mple.minischoolsys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.s1mple.minischoolsys.dao")
public class MinischoolsysApplication {

    public static void main(String[] args) {
        SpringApplication.run(MinischoolsysApplication.class, args);
    }

}

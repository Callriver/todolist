package com.rxsoft;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"mapping","com.rxsoft.orm.dao","com.rxsoft.orm.model"})
public class TodolistApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodolistApplication.class, args);
    }

}

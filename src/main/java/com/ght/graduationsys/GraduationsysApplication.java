package com.ght.graduationsys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@MapperScan(basePackages = "com.ght.graduationsys.dao")
@ServletComponentScan(basePackages = {"com.ght.graduationsys.filter"})
@SpringBootApplication
public class GraduationsysApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraduationsysApplication.class, args);
    }

}

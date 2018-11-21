package com.wang.spbchapter4;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wang.spbchapter4.mapper")
public class Spbchapter4Application {

    public static void main(String[] args) {

//        SpringApplication.run(Spbchapter4Application.class,args);
        SpringApplication.run(Spbchapter4Application.class, args);
    }
}

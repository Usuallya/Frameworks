package com.wang.spblog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpblogApplication.class, args);
        //Logger logger = LoggerFactory.getLogger(getClass());
    }
}

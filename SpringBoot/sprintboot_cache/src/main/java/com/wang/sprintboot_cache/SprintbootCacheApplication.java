package com.wang.sprintboot_cache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.wang.sprintboot_cache.mapper")
@EnableCaching
public class SprintbootCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(SprintbootCacheApplication.class, args);
    }
}

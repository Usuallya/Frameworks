package com.atguigu.cache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@MapperScan("com.atguigu.cache.mapper")
@SpringBootApplication
@EnableCaching  // 開啓基於註解的緩存
public class SprinbBoot01CacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(SprinbBoot01CacheApplication.class, args);
    }
}

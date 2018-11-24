package com.wang.spbchapter4.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @ ClassName  ：DruidConfig
 * @ Author     ：王海奇
 * @ Date       ：Created in 10:27 2018/11/24
 */
@Configuration
public class DruidConfig {
    //使用配置类来将数据源的额外配置生效，需要用这个注解来将配置文件的内容引入
//    @ConfigurationProperties(prefix = "spring.datasource")
//    @Bean
//    public DataSource druid(){
//        return new DruidDataSource();
//    }
}

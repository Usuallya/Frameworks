package com.wang.spbwebapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ ClassName  ：MyMvcConfig
 * @ Author     ：王海奇
 * @ Date       ：Created in 20:46 2018/11/21
 */
@Configuration
@EnableWebMvc
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //扩展一个视图映射
        registry.addViewController("/nothing").setViewName("ok");
    }
}

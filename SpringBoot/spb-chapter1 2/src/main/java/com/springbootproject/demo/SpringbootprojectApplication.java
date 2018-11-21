package com.springbootproject.demo;

import com.springbootproject.demo.filter.Filter1;
import com.springbootproject.demo.listener.Listener;
import com.springbootproject.demo.servlet.SecondeServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//方法1：通过注解来完成servlet的整合
//@ServletComponentScan
//方法1：通过注解来完成Filter的整合
//@ServletComponentScan
//方法1：通过注解来完成Listener的整合
//@ServletComponentScan
public class SpringbootprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootprojectApplication.class, args);
    }

    //第二种方法来整合Servlet，通过编写方法来整合。使用这种方法时可以删掉上面的servletScan注解以及Servlet2本身的地址映射注解
    //两种方式可以共存
    @Bean
    public ServletRegistrationBean getServletRegistrationBean(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new SecondeServlet());
        bean.addUrlMappings("/secondServlet");
        return bean;
    }
    @Bean
    public FilterRegistrationBean getFilterRegistrationBean(){
        FilterRegistrationBean bean = new FilterRegistrationBean(new Filter1());
        bean.addUrlPatterns("/*");
        return bean;
    }
    @Bean
    public ServletListenerRegistrationBean<Listener> getListenerRegistrationBean(){
        ServletListenerRegistrationBean<Listener> s = new ServletListenerRegistrationBean<>(new Listener());
        return s;
    }

}

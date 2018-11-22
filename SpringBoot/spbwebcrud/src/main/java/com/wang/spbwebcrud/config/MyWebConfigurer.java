package com.wang.spbwebcrud.config;

import com.wang.spbwebcrud.component.LoginHandlerInterceptor;
import com.wang.spbwebcrud.component.MyLocaleResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ ClassName  ：MyWebConfigurer
 * @ Author     ：王海奇
 * @ Date       ：Created in 13:52 2018/11/22
 */
@Configuration
public class MyWebConfigurer implements WebMvcConfigurer {
    @Autowired
    ApplicationContext applicationContext;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/index.html").setViewName("login");
        registry.addViewController("/main.html").setViewName("dashboard");
    }

    //注意这里有个大坑：方法名必须是localResolver，而不能是myLocaleResolver，否则不能生效。
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//表示任意多层路径下的任意请求
//Spring Boot已经帮我们做好了静态资源映射，所以不用管了
//Spring Boot2.0中，我们还是需要指定排除静态资源。否则会被拦截，踩坑了
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/index.html","/","/user/login").excludePathPatterns("/asserts/**").excludePathPatterns("/webjars/**");
    }
}

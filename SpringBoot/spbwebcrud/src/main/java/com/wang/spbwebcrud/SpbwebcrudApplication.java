package com.wang.spbwebcrud;

import com.wang.spbwebcrud.component.MyLocaleResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;

@SpringBootApplication
public class SpbwebcrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpbwebcrudApplication.class, args);
    }

}

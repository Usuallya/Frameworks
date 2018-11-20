package cn.bupt.mybatis.controller;

import cn.bupt.mybatis.advice.Advice;
import cn.bupt.mybatis.domain.People;
import cn.bupt.mybatis.service.IPeopleService;
import cn.bupt.mybatis.service.impl.PeopleService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class test {
    public static void main(String args[]){
            ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
            PeopleService peopleService = ac.getBean("peopleService",PeopleService.class);
            Advice advice = ac.getBean("Advice",Advice.class);
//        try {
//            List<People> list = peopleService.show();
//            System.out.println(list.get(0).getName());
//        }catch(Exception e){
//            e.printStackTrace();
//        }
        try {
            peopleService.demo1();
            advice.Demo1Throws(new Exception());
        }catch(Exception e){

        }
    }
}

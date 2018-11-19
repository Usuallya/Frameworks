package spring.cn.wang.service;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Component("123")
public class PeopleSerivce {

    //aop方式3：使用注解

    public PeopleSerivce() {
    }
    @Pointcut("execution(* spring.cn.wang.service.PeopleSerivce.show())")
    public void show(){
//        File f = new File(this.getClass().getResource("/").getPath());
//        System.out.println(f);
        System.out.println("执行SHOW方法");
        //int i = 5/0;
    }

    public void testAspect(String name,int age){
        System.out.print("调用testAspect!");
    }

}

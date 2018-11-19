package spring.cn.wang.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAspectAdvice {
    @Before("spring.cn.wang.service.PeopleSerivce.show()")
    public void advice1(){
        System.out.println("Advice1");
    }

    public void advice2(){
        System.out.println("Advice2");
    }

    @Around("spring.cn.wang.service.PeopleSerivce.show()")
    public void advice3(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        System.out.print("执行环绕通知-前置");
        proceedingJoinPoint.proceed();
        System.out.print("执行环绕通知-后置");
    }
}

package spring.cn.wang.advice;

import org.aspectj.lang.annotation.Before;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class MyAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("调用前置通知");
    }
}

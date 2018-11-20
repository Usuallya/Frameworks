package spring.cn.wang.advice;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;
import java.rmi.RemoteException;

public class MyThrowAdvice implements ThrowsAdvice {

    public void afterThrowing(Exception ex) throws Throwable{
        System.out.println("异常通知执行_schema_based方式");
    }
}

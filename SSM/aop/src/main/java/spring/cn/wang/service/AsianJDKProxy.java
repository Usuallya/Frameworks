package spring.cn.wang.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class AsianJDKProxy implements InvocationHandler {

    AsianPeopleService asianPeopleService = new AsianPeopleService();

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("JDK代理，执行前");
        //注意，这里使用的是被代理类的实例，而CGLIB直接使用传入的参数
        Object result = method.invoke(asianPeopleService,args);
        System.out.println("JDK代理，执行后");
        return result;
    }
}

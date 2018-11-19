package spring.cn.wang.service;


import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class AsianProxy implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("执行拦截器方法");
        //Object result = method.invoke(o,objects);
        Object result = methodProxy.invokeSuper(o,objects);
        System.out.println("方法执行完毕");
        return result;
    }
}

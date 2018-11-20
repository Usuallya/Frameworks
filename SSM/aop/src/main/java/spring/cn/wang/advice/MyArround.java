package spring.cn.wang.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MyArround implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        //所谓环绕通知，就是把前置、后置通知全部放到这一个通知里面
        System.out.println("前置环绕通知执行");
        Object result = methodInvocation.proceed();//放行、让方法执行
        System.out.println("后置环绕通知执行");
        return result;
    }
}

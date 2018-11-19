package spring;

import javafx.application.Application;
import net.sf.cglib.proxy.Enhancer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.cn.wang.service.*;

import java.io.File;
import java.lang.reflect.Proxy;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //Cglib方式的代理执行方式
//        Enhancer enhancer = new Enhancer();
//        enhancer.setSuperclass(AsianPeopleService.class);
//        enhancer.setCallback(new AsianProxy());
//
//        AsianPeopleService asianPeopleService = (AsianPeopleService) enhancer.create();
//        asianPeopleService.method1();

        //JDK方式的代理执行方式：
        //这里获得的对象实际上就是代理类AsianJDKProxy的对象，JDK的动态代理支持比较底层
        AsianJDKProxy asianJDKProxy = new AsianJDKProxy();
        Func func = (Func)Proxy.newProxyInstance(AsianPeopleService.class.getClassLoader(),new Class[]{Func.class},asianJDKProxy);
        func.jdkUseFunc();

        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        String[] list = ac.getBeanDefinitionNames();
        for(String name:list)
            System.out.println(name);

    }

}

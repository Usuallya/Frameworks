package spring.cn.wang.service;

//只有JDK方式才要求被代理类实现一个抽象功能接口
public class AsianPeopleService implements Func {
    public void method1(){
        System.out.println("Method1");
    }
    public void method2(){
        System.out.println("Method2");
    }

    @Override
    public void jdkUseFunc() {
        System.out.println("jdkFunc执行");
    }
}

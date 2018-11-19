package cn.bupt.wang.controller;

public class Test {
    public static void main(String[] args){
        final ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("测试");
        String result  = threadLocal.get();
        System.out.print(result);

        new Thread(){
            public void run(){
                String result = threadLocal.get();
                System.out.print("结果"+result);
            };
        }.start();
    }
}

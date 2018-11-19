package cn.bupt.wang.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisUtil {
    private static SqlSessionFactory factory;
    //使用threadLocal是为了保证同一个线程中各个使用SqlSession的位置使用的是同一个SqlSession
    //而不同线程可以使用不同的SqlSession
    //如果不用threadLocal，而是把SqlSession直接作为静态变量，那么整个程序都只能使用工具类中的唯一一个SqlSession
    private static ThreadLocal<SqlSession> openSession = new ThreadLocal<>();
    static{
        try {
            //factory实例化是一个比较耗费时间性能的过程
            //这样就保证了有且只有一个factory
            InputStream is = Resources.getResourceAsStream("mybatis.xml");
            factory = new SqlSessionFactoryBuilder().build(is);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static SqlSession getSession(){
        if(openSession.get()==null){
            openSession.set(factory.openSession());
        }
        return openSession.get();
    }

    public static void closeSession(){
        SqlSession sqlSession = openSession.get();
        if(sqlSession!=null)
            sqlSession.close();
        openSession.set(null);
    }


}

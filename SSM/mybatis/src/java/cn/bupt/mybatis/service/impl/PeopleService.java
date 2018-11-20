package cn.bupt.mybatis.service.impl;

import cn.bupt.mybatis.domain.People;
import cn.bupt.mybatis.service.IPeopleService;
import com.mysql.cj.Session;
import com.mysql.cj.xdevapi.SessionFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class PeopleService implements IPeopleService {

    public PeopleService(){
    }

    @Override
    public List<People> show()throws Exception {
        //通过Resources读取xml文件
        InputStream mybatis = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(mybatis);
        SqlSession sqlSession = sessionFactory.openSession();
        List<People> peoples = sqlSession.selectList("selAll");
        People people = new People();
        people.setAge(20);
        people.setName("lilili");
        int index = sqlSession.insert("ins",people);
        sqlSession.commit();
        sqlSession.close();
        return peoples;
    }
    public void demo1(){
        System.out.println("Demo1 is running!");
        //int i = 5/0;
    }

}

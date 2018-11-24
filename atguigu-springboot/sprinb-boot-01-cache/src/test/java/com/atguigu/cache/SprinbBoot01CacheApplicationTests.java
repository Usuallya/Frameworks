package com.atguigu.cache;

import com.atguigu.cache.bean.Employee;
import com.atguigu.cache.mapper.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SprinbBoot01CacheApplicationTests {

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;    // 操作字符串

    @Autowired
    RedisTemplate redisTemplate;                // 操作k / v

    @Autowired
    RedisTemplate<Object, Employee> empRedisTemplate;

    /*
    *   stringRedisTemplate.opsForValue()   字符串
    *   stringRedisTemplate.opsForList()    列表
    *   stringRedisTemplate.opsForSet()     集合
    *   stringRedisTemplate.opsForHash()    散列
    *   stringRedisTemplate.opsForZSet()    有序集合
    * */

    @Test
    public void test01(){
        // stringRedisTemplate.opsForValue().append("msg", "hello");
        String msg = stringRedisTemplate.opsForValue().get("msg");
        System.out.println(msg);
    }

    @Test
    public void test02(){
        Employee employee = employeeMapper.getEmpById(1);
        //  默認如果保存對象，使用jdk序列化機制，序列化後的數據 保存到redis中
        // redisTemplate.opsForValue().set("emp-01", employee);
        // 1\將數據以json的方式保存
        // (1)自己將對象轉爲json
        // (2)redisTemplate有默認的序列化規則
        empRedisTemplate.opsForValue().set("emp-01", employee);
    }

    @Test
    public void contextLoads() {
        Employee employee = employeeMapper.getEmpById(1);
        System.out.println(employee);
    }

}

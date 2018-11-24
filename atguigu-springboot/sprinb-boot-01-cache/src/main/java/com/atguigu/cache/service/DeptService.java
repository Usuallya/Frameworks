package com.atguigu.cache.service;

import com.atguigu.cache.bean.Department;
import com.atguigu.cache.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheManager = "deptCacheManager")
public class DeptService {

    @Autowired
    DepartmentMapper departmentMapper;

    @Qualifier("deptCacheManager")
    @Autowired
    RedisCacheManager deptCacheManager;

//    @Cacheable(cacheNames = "dept")
//    public Department getDeptById(Integer id){
//        System.out.println("查詢部門:"+id);
//        Department deptById = departmentMapper.getDeptById(id);
//        return deptById;
//    }

    public Department getDeptById(Integer id){
        System.out.println("查詢部門:"+id);
        Department deptById = departmentMapper.getDeptById(id);

        Cache dept = deptCacheManager.getCache("dept");
        dept.put("dept:1", dept);

        return deptById;
    }

}

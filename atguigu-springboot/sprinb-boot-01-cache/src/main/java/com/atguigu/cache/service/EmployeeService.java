package com.atguigu.cache.service;

import com.atguigu.cache.bean.Employee;
import com.atguigu.cache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames = "emp", cacheManager = "employeeCacheManager")
@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    /*
    * 將方法的運行結果進行緩存；以後再要緩存相同的數據，直接從緩存中取，不用調用方法
    *
    * CacheManager管理多個Cache組件的，對緩存的真正CRUD操作在Cache組件中，每一個緩存組件有自己唯一一個名字;
    * 幾個屬性:
    *   cacheName/value：指定緩存組件的名字;將方法的返回結果放在哪個緩存中是數組的方式，可以指定多個緩存
    *   key:緩存數據使用的key；可以用它來指定。默認是使用方法的參數   1-方法的返回值
    *       編寫SpEl; #id;參數id的值  #a0 #p0 #root.args[0]
    *   keyGenerator: key的生成器: 可以指定key的生成器的組件id
    *       key/keyGenerator: 二選一使用
    *       key = "#root.methodName + '['+id+']'"
    *   cacheManager: 指定緩存管理器；或者cacheResolver指定獲取解析器
    *   condition: 指定符合條件的情況下才緩存
    *       condition = "#id>0"
    *       condition = "#a0>1" 第一個參數>1的情況下進行緩存
    *   unless: 否定緩存；當ubless指定的條件爲true，方法的返回值就不會被緩存；可以獲取到結果進行判斷
    *       unless = "#result == null"
    *       #result爲方法的返回值
    *   sync:是否使用異步緩存
    * */

    @Cacheable(/*cacheNames = {"emp"}, keyGenerator = "myKeyGenerator", condition = "#a0>0"*/)  // 進行緩存
    public Employee getEmp(Integer id){
        System.out.println("查詢"+id+"號員工");
        Employee emp = employeeMapper.getEmpById(id);
        return emp;
    }

    /*
    *   @CachePut:既調用方法，又更新緩存是數據；
    *   修改了數據庫的某個數據，同時更新緩存；
    *
    * */
    @CachePut(/*value = "emp", */key = "#employee.id")
    public Employee updateEmp(Employee employee){
        System.out.println("updateEmp:"+employee);
        employeeMapper.updateEmp(employee);
        return employee;
    }

    /*
    * @CacheEvict:緩存清除
    * allEntries刪除所有緩存數據
    * beforeInvocation默認爲false，緩存的清除是否在方法之前執行
    *
    * */
    @CacheEvict(/*value = "emp", */key = "#id"/*, allEntries = true*/)
    public void deleteEmp(Integer id){
        System.out.println("deleteEmp:"+id);
        //employeeMapper.deleteEmpById(id);

    }

    @Caching(
            cacheable = {
                    @Cacheable(/*value = "emp", */key = "#lastName")
            },
            put = {
                    @CachePut(value = "emp", key = "#result.id"),
                    @CachePut(value = "emp", key = "#result.email")
            }
    )
    public Employee getEmpByLastName(String lastName){
        return employeeMapper.getEmpByLastName(lastName);
    }
}

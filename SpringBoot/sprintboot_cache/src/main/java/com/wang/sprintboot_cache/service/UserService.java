package com.wang.sprintboot_cache.service;

import com.wang.sprintboot_cache.domain.Users;
import com.wang.sprintboot_cache.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @ ClassName  ：UserService
 * @ Author     ：王海奇
 * @ Date       ：Created in 15:49 2018/11/24
 */
@Service
public class UserService {
    @Autowired
    private UsersMapper usersMapper;

    /**
     * 使用缓存注解，将方法的返回结果进行缓存，以后再需要相同的数据时，就可以直接从缓存中获取。
     * Cacheable会在方法执行前先看缓存里有没有，没有的话再执行。
     * CacheManager管理了多个Cache组件，对缓存真正的CRUD操作在Cache组件中，每一个Cache组件有一个唯一的名字
     * 这个名字就由cacheNames/value属性来指定。
     * 此外，key属性来指定缓存数据使用的key，默认是使用方法参数的值。
     * #id = #a0 =#p0
     *
     * keyGenerator：自己指定key的生成器，和KEY二选一。
     * cacheManager：指定缓存管理器（和resolver一样）
     * condition指定符合条件的情况下才缓存
     * @param id
     * @return
     */
    @Cacheable(cacheNames = "users",key = "#id")
    public Users getUsers(Integer id){
        System.out.println("执行查询操作！");
        Users users = usersMapper.getUsers(id);
        return users;
    }

    /**
     * @CachePut注解：既调用方法，又把方法的返回值用来更新缓存数据，一般用在更新操作上。起到同步
     * 更新缓存的目的。
     * @CachePut注解是先调用目标方法，然后将目标方法的结果缓存起来
     * 这里注意，必须指定和之前缓存的数据相同的key，才能起到更新缓存内容的作用。否则相当于创建了
     * 新的缓存。
     * 注意，这里的方法返回值必须和上面cacheable的对象一致。
     */
    @CachePut(cacheNames = "users",key = "#user.id")
    public Users updateUsers(Users user){
        usersMapper.updateUsers(user.getName(),user.getId());
        return user;
    }

    /**
     * 清空时必须指定相同的value和key
     * allentries是指是否清除所有缓存键值对
     *
     * 这个注解默认是在方法执行之后执行清空缓存的，可以使用beforeInovation来
     * 设置执行之前清空。
     * @param id
     */
    @CacheEvict(cacheNames = "users",key="#id",allEntries = true)
    public void deleteUsers(Integer id){
        System.out.println("清除缓存");
    }
}

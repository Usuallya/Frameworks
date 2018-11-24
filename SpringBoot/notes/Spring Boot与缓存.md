# SpringBoot与缓存

J2EE发布了缓存规范JSR107，定义了5个核心接口

- CachingProvider：定义了创建、配置、获取和管理控制多个CacheManager
- CacheManager：定义了多个唯一命名的Cache组件
- Cache：是一个类似于Map的数据结构并临时存储以Key为索引的值。一个Cache仅被一个CacheManager所拥有。
- Entry：是一个存储在Cache中的Key-value对。
- Expiry：每一个存储在Cache中的条目有一个定义的有效期，一旦超过这个时间，条目就为过期状态。

![1543042007944](C:\Users\王海奇\AppData\Roaming\Typora\typora-user-images\1543042007944.png)

但是由于很多缓存没有提供JSR107的实现，现在多用Spring提供的缓存。

Spring定义了cache和cacheManager接口来统一不同的缓存技术，而且支持使用JSR107的注解来简化开发。

Cache:缓存接口，定义缓存操作，它的实现有RedisCache,EhcacheCache等

CacheManager:缓存管理器，管理各种Cache组件。分为多种，可能有redis的或者别的的。	 

@Cacheable：主要针对方法配置，能够根据方法的请求参数对其结果进行缓存

@CacheEvict：清空缓存

@CachePut：更新缓存，被它标注的方法总会被调用，然后更新缓存中的数据

@EnableCache：开启基于注解的缓存

### 使用SpringCache

1. 首先创建好一套基于Mybatis的CRUD应用。

2. 开启基于注解的缓存

   ``` java
   @EnableCaching
   public class SprintbootCacheApplication {
   ```

3. 标注缓存注解即可

   ``` java
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
   
   
   //使用cachePut注解，完成缓存更新
       /**
        * @CachePut注解：既调用方法，又更新缓存数据，一般用在更新操作上。起到同步
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
   
   //缓存的删除
   
   
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
   
   //三个注解的组合：Caching注解
   @Caching{
       cacheable={
           @Cacheable(value="",key="")
       }
       put={
           @CachePut(value="",key="")
       }
   }
   public Users getUsersByLastName()
       
   //CacheConfig可以标注在类上，统一配置cacheNames
    @CacheConfig(value="users")
    
   ```





### 原理：

查看CacheAutoConfiguration类，发现有如下配置类：  org.springframework.boot.autoconfigure.cache.JCacheCacheConfiguration

org.springframework.boot.autoconfigure.cache.GenericCacheConfiguration

org.springframework.boot.autoconfigure.cache.EhCacheCacheConfiguration

org.springframework.boot.autoconfigure.cache.HazelcastCacheConfiguration

org.springframework.boot.autoconfigure.cache.InfinispanCacheConfiguration

org.springframework.boot.autoconfigure.cache.CouchbaseCacheConfiguration

==org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration==

org.springframework.boot.autoconfigure.cache.CaffeineCacheConfiguration

==org.springframework.boot.autoconfigure.cache.SimpleCacheConfiguration==

org.springframework.boot.autoconfigure.cache.NoOpCacheConfiguration

为了查看默认到底哪个类会生效，我们在配置文件中加入debug=true，查看自动配置报告，发现是SimpleCacheConfiguration匹配上了。

它的作用是默认给容器中注册了一个CacheManager ConcurrentMapCacheManager，可以获取和创建ConcurrentMapCache类型的缓存组件，它的作用是将数据保存在ConcurrentMap中。

### 整合Redis来作为缓存

首先安装Redis，可以通过docker来安装。Redis命令可以参考Redis中文网查看。

要在Spring Boot中使用Redis作为缓存，首先要引入Redis的Starter。

spring-boot-starter-redis

接下来需要配置redis：

``` properties
//配置redis主机地址
spring.redis.host=
```

Redis的自动配置也在RedisAutoConfiguration自动配置类中。查看这个自动配置类，发现它在容器中提供了两个简化操作的Template：

RedisTemplate（操作K-V）和StringRedisTemplate（操作字符串）

``` java
/**
*Redist常见的五大数据集合（String,list,set,hash,zset）
*/
//stringRedisTemplate.opsForValue//操作字符串
//stringRedisTemplate.opsForList//操作列表
//stringRedisTemplate.opsForHash//操作哈希表
public class service{

@Autowired
StringRedisTemplate stringRedisTemplate;

public void test1(){
stringRedisTemplate.opsForValue().append("msg","hello");
stringRedisTemplate.opsForValue().get("msg");
Object obj = new Object();
//这将会把对象序列化后存入，而且用的是JDK默认的序列化器
stringRedisTemplate.opsForValue().set("obj1",obj);
//如果要用json格式存储对象，可以改用json格式的序列化器
}
```

要改用Json格式的序列化器，我们需要自定义RedisConfig类，在其中添加RedisTemplate组件（复制自动配置类的，然后修改一下序列化器的配置即可），可能需要改个名字。



### 自定义缓存管理器

当我们引入Redis后，SimpleCacheManager就不会匹配了。容器中保存的是RedisCacheManager（通过RedisCacheConfiguration创建的）。它帮我们创建了RedisCache来作为缓存组件，这个组件通过操作Redis来进行缓存。这样，我们的缓存注解（Cacheable之类）就将会存在Redis中。当k-v是object时，会利用序列化保存。这是因为默认创建的redistTemplate使用了jdk默认的序列化机制。我们可以通过自己写配置类更改这个配置。

注意，这和上面直接利用Redsi的API进行操作时是不同的，这里要改的是RedistCacheManager中的相关内容，影响的是SpringBoot缓存的效果。只有改了RedistCacheManager，才能影响到。

当我们更改了Manager，可能支持了某个类的JSON序列化，但是会发现，其他类不能按照这样的方式序列化和反序列化，这时可能就需要配置多个CacheManager，分别使用多种RedisTemplate，来操作各种对象的缓存。在各个需要缓存的Service类中，可以在前面提到过的CacheConfig注解中指定要使用的CacheManager。

#### 通过编码方式操作缓存

整合的情况下，除了使用Spring Boot的缓存外，也可以把缓存管理器CacheManager注入到我们要使用的位置，然后直接

cacheManager.getCache()，就可以获取到Redis中的这个缓存内容，可以直接对其进行修改。
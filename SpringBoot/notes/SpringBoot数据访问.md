## SpringBoot数据访问

### 1.JDBC

默认使用org.apache.tomcat.jdbc.pool.DataSource来配置。数据源的相关配置都在DataSourceProperties里面。

#### 自动配置原理：

org.springframework.boot.autoconfigure.jdbc

1. 参考DataSourceConfiguration，根据配置创建数据源，默认使用Tomcat连接池，可以使用spring.datasource.type指定自定义的数据源类型。

2. 默认可以支持 tomcat的数据源，hikaridatasource,basicdatasource以及我们自定义的数据源。

3. DataSourceInitializer：ApplicationListener,一个初始化器，它的作用：

   runSchemaScripts（）运行建表语句

   runDataScripts（）运行插入数据的SQL语句

   默认只需要将文件名为schema-*.sql 和 data-*.sql的文件放在类根目录下即可。也可以通过配置来修改这个文件位置。

4. 操作数据库：容器中已经含有JDBCTemplate，可以直接注入使用。



#### 使用druid数据源

首先引入druid依赖，然后在配置文件中，使用spring.datasource.type来指定druid数据源。



### 2.Mybatis

首先要引入mybatis-spring-boot-starter。在初始化时，需要加入mysql,jdbc和mybatis。

如果要使用Mybatis注解版，只需要创建好JavaBean，然后再Mapper接口上加@Mapper，或者在各个Mapper上不加，而是在启动类上统一加一个MapperScan做批量扫描。在相应的接口方法上加注解，然后就可以直接运行了。不需要任何额外配置。自动配置是在MybatisAutoConfiguration类中。我们可以通过创建一个MybatisConfig类，并在其中加入定制器，来修改更多配置。

如果要使用配置文件版，首先写好mybatis-config和各自的mapper.xml文件，然后再全局配置文件中，使用：

``` properties
mybatis.cofing-location:
mybatis.mappper-locations:
//分别指定mybatis配置文件和mapper映射文件地址
```

### 3.SpringData和JPA

SpringData是Spring官方提供的，为了简化构建基于Spring框架应用的数据访问技术，包括关系型、非关系型、map-reudce、云数据服务等。他为我们提供了统一的API来对数据访问层进行操作。

JPA全称是Java persistent API，也就是JAVA持久层APII，是java持久层的标准化规范。它的实现有很多种，包括Hibernate，TopLink等。而我们通过Springdata JPA，可以不用了解具体底层实现。

#### 整合JPA

查看依赖关系可以发现，JPA底层用的Hibernate核心。

JPA是基于ORM（Obejct-Relational Mapping），所以，首先要编写一个实体类：

1. 编写一个实体类（这个实体类要和启动类放在一个包下）和数据表进行映射，并配置好映射关系（使用JPA注解@Entity）

   ``` java
   import javax.persistence.*;
   
   @Entity//告诉JPA这是一个实体类，要和数据表映射
   @Table(name="tbl_user")//如果省略，默认是对应user表
   public class User {
   @Id//设置主键
   @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
   private Integer id;
   @Column(name="last_name",length = 50)//表明这是和表列明对应
   private String lastName;
   @Column
   private String email;
   
   public Integer getId() {
   return id;
   }
   
   public void setId(Integer id) {
   this.id = id;
   }
   
   public String getLastName() {
   return lastName;
   }
   
   public void setLastName(String lastName) {
   this.lastName = lastName;
   }
   
   public String getEmail() {
   return email;
   }
   
   public void setEmail(String email) {
   this.email = email;
   }
   }
   }
   }
   ```

2. 编写一个Dao接口来操作实体类对应的数据表（Repository），让它继承JPARepository，无需实现什么功能，就会自动具有CRUD和排序分页功能。

3. 在配置文件中加入一些基本配置：

   ```yaml
   spring:
    jpa:
     hibernate:
     #更新或者创建数据表结构（如果启动时没找到，就按照ENTITY自动创建）
      ddl-auto:update
     #控制台显示SQL信息
     show-sql:true
   ```

4. 在其他位置想要使用数据库时，只需要先注入userRepository，然后直接使用它的方法即可。

   ``` java
   //使用JPA来操作数据库
   @GetMapping("/addUserByJPA")
   @ResponseBody
   public User addUserByJPA(){
   User user = userRepository.findById(1).get();
   return user;
   }
   ```





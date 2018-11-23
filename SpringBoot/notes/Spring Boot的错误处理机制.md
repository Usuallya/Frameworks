## Spring Boot的错误处理机制

### 1 默认错误处理效果

如果是浏览器访问，默认返回一个错误页面

如果是其他客户端（例如PostMan），默认响应一个Json数据

自动配置类:ErrorMvcAutoConfiguration

给容器中添加了一下组件：

- DefaultErrorAttributes：帮我们在页面共享信息，包括时间戳，错误状态码，错误提示，异常对象，会以model的形式放在响应中。


- BasicErrorController：处理默认的/error请求，在这里会区分浏览器或者其他客户端，给与不同的响应。

  == 浏览器发送请求的请求头会含有accept:text/hml ==

  postman中请求头的accept不含text/html



- ErrorPageCustomizer:注册错误页面的响应规则（/error）

  一旦出现错误，ErrorPageCustomizer就会注册错误页面的相应规则：

```java
//系统出现错误之后来到Error请求进行处理;
@Value("${error.path:/error}")

private String path = "/error";
```



- DefaultErrorViewResolver：错误的响应由哪个页面显示，是由这个解析器来配置的。

![1542975726726](C:\Users\王海奇\AppData\Roaming\Typora\typora-user-images\1542975726726.png)

解析器默认是解析error/错误代码，所以如果要定制我们的错误页面，做法：

1. 有模板引擎时，在templates下创建error，其中加入各个错误代码页面
2. 没有模板引擎时，默认在静态资源文件夹下找
3. 都没有时，默认来到springboot默认的错误提示页面。



## 配置嵌入式Servlet容器



SpringBoot默认使用的是嵌入式的Servlet容器Tomcat，可以在依赖树中看到。

### 问题

1. 如何定制和修改Servlet容器的相关配置？

   1. 修改server有关的配置，在配置文件中直接修改，相当于改了ServerProperties（实现了EmbeddedServletContainerCustomer，本质上也是修改定制类）

      ``` :british_indian_ocean_territory:
      //服务器通用配置
      server.port = 80081
      server.content-path=/crud
      
      //tomcat专用配置
      server.tomcat.xxx
      ```

   2. 在mvcConfig中加一个embededServletContainerCustomer的定制类，然后再定制器中修改。本质上这是修改配置的唯一方法，其他方法都是间接使用它。

      ![1542977286764](C:\Users\王海奇\AppData\Roaming\Typora\typora-user-images\1542977286764.png)

   3. 添加servlet、listener、Filter：

      需要以组件的形式注册，首先写一个webmvc配置类，然后在其中将这些以组件的形式添加进来。其中servlet是ServletRegistrationBean：

      ![1542977399607](C:\Users\王海奇\AppData\Roaming\Typora\typora-user-images\1542977399607.png)

      filter是filterRegistrationBean，listener是类似的。除了这种自己写配置类的方式以外，还可以直接在启动类上加一个servletComponentScan，来自动扫描。不过这需要servlet上添加webservlet注解。

2. SpringBoot能否支持其他Servlet容器？包括Jetty（适合长连接应用，比如聊天室）和Undertow（高性能非阻塞，但不支持JSP）等

   SpringBoot对他们的支持可以从上面的定制类中发现：

   ![1542977701573](C:\Users\王海奇\AppData\Roaming\Typora\typora-user-images\1542977701573.png)

   切换jetty时，只需要先把tomcat依赖exclude掉，然后把jetty的依赖引入就行了。

### 嵌入式Servlet容器的自动配置原理

使用EmbeddedServletContainerAutoConfiguration:嵌入式Servlet容器的自动配置类：

![1542978423631](C:\Users\王海奇\AppData\Roaming\Typora\typora-user-images\1542978423631.png)

![1542978577692](C:\Users\王海奇\AppData\Roaming\Typora\typora-user-images\1542978577692.png)

可见，导入了哪个容器的依赖，就会生成哪个容器的工厂。

1. EmbeddedServletContainerFactory（嵌入式Servlet容器工厂）

   有三种容器工厂，分tomcat，jetty和undertow。

2. EmbeddedServletContainer（嵌入式Servlet容器）同样分上面三种。

3. 下面以TomcatEmbeddedServletContainerFacotry为例：

   ![1542978700230](C:\Users\王海奇\AppData\Roaming\Typora\typora-user-images\1542978700230.png)

   只要端口号大于0，这里返回Container时，就将会自动启动Tomcat。

   ### 自动配置步骤：

   1. SpringBoot根据导入的依赖情况，给容器中添加对应的embeddedServletContainerFctory
   2. 容器中某个组件要创建对象，就会触发后置处理器，EmbeddeddSrevletContainerCustomizerBeanPostProcessof
   3. 后置处理器会从容器中获取所有的嵌入式Servlet容器的定制器，并执行定制方法。这样容器就启动了。

### 嵌入式Servlet容器的启动原理

什么时候创建了嵌入式的Servlet容器工厂，什么时候获取嵌入式的Servlet容器并启动它？

获取嵌入式的Servlet容器工厂：

1. Spring Boot启动，运行Run方法
2. refreshContext(context);是SPringBOOT刷新IOC容器，并初始化容器，创建容器中的每一个组件；如果是web环境，就创建AnnotationConfigEmbeddedWebApplicationContext
3. refresh(context)，刷新刚才创建好的IOC容器。
4. web的ioc容器重写了onRefresh方法
5. web容器会创建嵌入式Servlet容器：createEmbeddedServletContainer
6. 从容器中获取嵌入式的Servlet容器工厂，只要有tomcat的包，就会利用tomcat的工厂创建对象，然后触发后置处理器，获取所有定制器来定制Servlet容器的相关配置。
7.  使用容器工厂获取嵌入式的Servlet容器。
8. 嵌入式的Servlet容器创建对象并启动Servlet容器。

先启动了嵌入式的Servlet容器，再将IOC容器中剩下没有创建出的对象获取到。是IOC容器启动时创建了嵌入式的Servlet容器。

## 使用外置Servlet容器

嵌入式容器的缺点：默认不支持JSP，优化定制比较复杂。

外面安装的tomcat容器需要应用war包的方式打包。实际上我们只需要在创建Spring Boot工程时，指定打包为WAR包，然后配置好争取的目录结构即可。

然后需要将Pom文件中，嵌入式tomcat加一个provided，然后需要加入一个servletInitlizer，继承自SpringBootServletInitializer并调用Configure方法，给这个方法传入SpringBoot的主程序。

![1542984656044](C:\Users\王海奇\AppData\Roaming\Typora\typora-user-images\1542984656044.png)

最后，启动服务器就可以使用了。

### 原理

jar包：先执行SpringBoot主类的main方法，启动IOC容器，创建嵌入式Servlet容器。

war包：先启动服务器，然后服务器启动SpringBoot应用（使用SpringBootServletInitializer），再启动IOC容器。



Servlet3.0中的规范：共享库和运行时插件，规则：

1. 服务器启动时，会创建当前web应用里面每一个jar包里面ServletContainerInitializer实例
2. 这个类的实现必须在jar包里的META-INF/services文件夹下，有一个名为javax.servlet.ServletContainerInitializer的文件，文件的内容就是全类名（而不是类的实现），比放在Spring中的全类名就是SPring的
3. 还可以使用@HandlesType，在应用启动的时候加载我们感兴趣的类。



#### 流程

![1542986147332](C:\Users\王海奇\AppData\Roaming\Typora\typora-user-images\1542986147332.png)

接下来，会调用到configure方法，由于子类重写了这个方法，则会将SpringBoot的主程序类传入进来，然后用builder构建好SPringBoot应用，最后run来执行。这个run和之前的主类run是一样的。
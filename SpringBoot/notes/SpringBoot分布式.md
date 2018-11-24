# Spring Boot分布式

在分布式系统中，国内常用Zookeeper+dubbo，而SpringBoot推荐使用Spring SpringBoot+Spring Cloud

我们将各个模块都拆分成很多微服务，例如用户模块要使用订单模块，就要适用到RPC（远程过程调用）以及注册中心（ZooKeeper）

ZooKeeper和Dubbo：Zookeeper就是注册中心，而Dubbo就是分布式服务框架，Dubbo的架构如图所示：

![1543033412052](C:\Users\王海奇\AppData\Roaming\Typora\typora-user-images\1543033412052.png)

## Zookeeper和dubbo的安装

使用docker来安装zookeeper

启动：docker run --name myZookeeper -p 2181:2181 --restart always -d zookeeperID

启动之后，我们创建一个服务提供者和消费者，使用Dubbo来调用。还需要引入Zookeeper的包zkclient

在服务提供者的配置文件中，需要加入：

``` :2nd_place_medal:
dubbo.application.name=provider-ticket
dubbo.registry.address=zookeeper://00000:2181
dubbo.scan.base-package=com.wang.ticket.service
```

然后在服务类上，加入注解Service和Component，注意这不是Spring的，而是DUBBO的。它能让服务发布出去。

最后，运行provider，它的服务就可以注册成功。



在消费者端，我们需要：

1. 引入依赖
2. 配置消费服务，包括应用名和注册中心地址
3. 将TicketService包带路径复制到消费者中（接口），然后在消费者的方法中，使用@Reference，来远程注入提供者的服务，最后直接使用即可。



## 使用SpringCloud

Spring Cloud是一个分布式的整体解决方案，他为开发者提供了在分布式系统中快速构建的工具，包括了服务发现Netflix Eureka，负载均衡Netflix Ribbon、断路器 Netflix Hystrix、服务网关Netflix Zuul和分布式配置Spring Cloud Config。
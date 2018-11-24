package com.atguigu.elastic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
* SpringBoot默认支持两种技术来和ES交互
* 1、Jest(默认不生效)
*   需要导入jest的工具包(io.searchbox.client,JestClient)
* 2、SpringData ElasticSearch[ES版本有可能不合适]
*       版本适配说明:https://github.com/spring-projects/spring-data-elasticsearch
*           1) 升级spring-boot版本
*           2) 安装对应版本的ES
*       1)、Client节点信息clusterNodes；clusterName
*       2)、ElasticsearchTemplate操作es
*       3)、编写一个ElasticsearchRepository的子接口来操作es
*   两种用法：https://github.com/spring-projects/spring-data-elasticsearch
*   1)、编写一个ElasticsearchRepository
* */
@SpringBootApplication
public class Springboot03ElasticApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot03ElasticApplication.class, args);
    }
}

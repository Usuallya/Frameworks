package com.atguigu.amqp;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
*   自動配置
*   1 RabbitAutoConfiguration
*   2 有自動配置了連接工場ConnectionFactory
*   3 RabbitProperties封裝了RabbitMQ的配置
*   4 RabbitTemplate:給RabbitMQ發送和接受消息
*   5 AmqpAdmin:RabbitMQ系統管理功能組件
*       AmqpAdmin:創建和刪除Queue, Exchange, Binding
*   6 @EnableRabbit + @RabbitListener 監聽消息隊列的內容
* */
@EnableRabbit   // 開啓基於註解的RabbitMQ模式
@SpringBootApplication
public class Springboot02AmqpApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot02AmqpApplication.class, args);
    }
}

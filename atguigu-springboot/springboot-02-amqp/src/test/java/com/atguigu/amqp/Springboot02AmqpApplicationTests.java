package com.atguigu.amqp;

import com.atguigu.amqp.bean.Brook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot02AmqpApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;

    @Test
    public void createExchange(){
        amqpAdmin.declareExchange(new DirectExchange("amqpadmin.exchange"));
        System.out.println("創建完成");

        amqpAdmin.declareQueue(new Queue("amqpadmin.queue", true));

        // 創建綁定規則
        amqpAdmin.declareBinding(new Binding("amqpadmin.queue", Binding.DestinationType.QUEUE, "amqpadmin.exchange", "amqp.haha", null));
    }

    /*
    * 1 單播(點對點)
    * */
    @Test
    public void contextLoads() {
        // Message需要自己構造一個；定義消息體內容和消息頭
        // rabbitTemplate.send(exchage, routeKey, message);

        // object默認當成消息體,只需要傳入要發送的對象，自動序列化發送給rabbitMQ
        // rabbitTemplate.convertAndSend(exchage, routeKey, object);

        Map<String, Object> map = new HashMap<>();
        map.put("msg", "這是第一個消息");
        map.put("data", Arrays.asList("helloworld", "123", true));
        // 對象被默認序列化以後發送出去
        rabbitTemplate.convertAndSend("exchange.direct", "atguigu.news", new Brook("西遊記", "吳承恩"));
    }

    // 接收數據，如何將數據自動轉爲json發送出去
    @Test
    public void receive(){
        Object o = rabbitTemplate.receiveAndConvert("atguigu.news");
        System.out.println(o.getClass());
        System.out.println(o);
    }

    /*
    * 廣播
    * */
    @Test
    public void sendMsg(){
        rabbitTemplate.convertAndSend("exchange.fanout", "", new Brook("三國演義", "羅貫中"));
    }
}

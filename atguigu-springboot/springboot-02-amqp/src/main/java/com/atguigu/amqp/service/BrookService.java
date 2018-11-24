package com.atguigu.amqp.service;

import com.atguigu.amqp.bean.Brook;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class BrookService {

    @RabbitListener(queues = "atguigu.news")
    public void receive(Brook brook){
        System.out.println("收到消息"+brook);
    }

    @RabbitListener(queues = "atguigu")
    public void receive02(Message message){
        System.out.println(message.getBody());
        System.out.println(message.getMessageProperties());
    }
}

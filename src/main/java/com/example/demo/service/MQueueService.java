package com.example.demo.service;

import com.example.demo.mq.OrderMes;

import java.util.concurrent.ArrayBlockingQueue;

public interface MQueueService {
    //消息队列的最大值
    int MAX_SIZE =500;
    //保存消息的容器
    ArrayBlockingQueue<OrderMes> queue=new ArrayBlockingQueue<>(MQueueService.MAX_SIZE);
    //发布消息
    void consume(OrderMes orderMes);


}

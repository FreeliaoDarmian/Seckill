package com.example.demo.service;

import com.example.demo.mq.OrderMes;
import com.example.demo.utils.BroadCaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MQueueServiceImpl implements MQueueService {

    @Autowired
    BroadCaster broadCaster;

    @Override
    public void consume(OrderMes orderMes) {



    }
}

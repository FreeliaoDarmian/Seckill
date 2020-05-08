package com.example.demo.utils;

import com.example.demo.utils.BroadCaster;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ListenerConfig {


    @Bean
    public BroadCaster getBroadCaster(){
        return new BroadCaster();
    }
}

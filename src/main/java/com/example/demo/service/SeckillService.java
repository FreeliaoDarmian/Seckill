package com.example.demo.service;

import java.util.Map;

public interface SeckillService {
    /**
     * 根据md5暴露秒杀的接口
     * @param seckillid
     * @return
     */
    String exposeSeckillUrl(Integer seckillid);

    /**
     * 秒杀商品是否还存在
     * @param seckillid
     * @return
     */
    boolean exists(Integer seckillid);

    int doSeckill(String pathId,Integer userId,String goodsId);
}

package com.example.demo.service;

import cn.hutool.core.lang.Console;
import cn.hutool.crypto.SecureUtil;
import com.example.demo.mapper.SecKillMapper;
import com.example.demo.mq.OrderMes;
import com.example.demo.redis.JedisPoolUtil;
import com.example.demo.redis.JedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;


@Service
public class SeckillServiceImpl implements SeckillService {

    @Autowired
    MQueueService mQueueService;

    @Autowired
    SecKillMapper secKillMapper;

    @Override
    public String exposeSeckillUrl(Integer seckillid){
       String stringUrl=String.valueOf(seckillid);
       String md5Url=SecureUtil.md5(stringUrl).substring(0,14);
       return md5Url;
    }

    @Override
    public boolean exists(Integer seckillid) {
        return false;
    }

    @Override
    @Transactional
    public int doSeckill(String pathId,Integer userId,String goodsId) {
        Jedis jedis =JedisPoolUtil.getJedis();
        //使用wactch命令
        jedis.watch(pathId);
        Integer val=Integer.valueOf(jedis.get(pathId));
        Console.log("当前线程:{}获取的数量:{}",Thread.currentThread().getName(),val);
        //开启事务
        Transaction transaction=jedis.multi();
        jedis.decr(pathId);
        List<Object> res=transaction.exec();

        if (res == null || res.isEmpty()) {
            System.out.println(Thread.currentThread().getName() + ":" + "Error:(" + val + "=>" + (val + 1) + ")");
        } else {
            String values = "";
            for (int i = 0; i < res.size(); i++) {
                values += res.get(i).toString();
            }
            System.out.println(Thread.currentThread().getName() + ":" + values + ":(" + val + "=>" + (val + 1) + ")");
        }
    jedis.close();
        //后期可以用消息队列并行处理发送下单消息到数据库
        //这里没有用乐观锁
        //用到是唯一性索引 如果插入失败就会回滚
        int flag=secKillMapper.insertOrder(userId,goodsId);
        int result=0;
        if(flag>0){
            result=secKillMapper.updateStock(goodsId);
        }
        return result>=0?1:0;
    }
}

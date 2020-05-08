package com.example.demo.rest;


import com.example.demo.enums.RedisReturnType;
import com.example.demo.redis.Redis;
import com.example.demo.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/seckill")
@RestController
public class SeckillController {

    @Autowired
    SeckillService seckillService;

    /**
     * 获取秒杀的动态链接 md5加密
     * @param SeckillId
     * @return
     */
    @GetMapping("/{seckillid}/getUrl")
    @Redis(redisReturnType = RedisReturnType.string ,filed = "#pathId",expireTime = 60)
    public ResponseEntity getSeckillUrl(@PathVariable("seckillid") Integer SeckillId){
        return new ResponseEntity(seckillService.exposeSeckillUrl(SeckillId),HttpStatus.OK);
    }

    /**
     * 秒杀业务核心
     * @param userId
     * @param goodsId
     * @return
     */
    @PostMapping("/{pathId}/doSeckill")
    //@Redis(redisReturnType = RedisReturnType.string ,filed = "#pathId",expireTime = 60)
    public ResponseEntity doSeckill(@PathVariable("pathId") String pathId,Integer userId,String goodsId){
        seckillService.doSeckill(pathId,userId,goodsId);
        return new ResponseEntity("秒杀成功,请尽快付款",HttpStatus.OK);
    }




}

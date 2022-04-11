package com.xxd.redis.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 分布式锁  注意重入问题，注意业务顺序即可避免  会有超时误删问题！！！勿使用
 * @Author xxd
 * @Date 2021/12/14
 * @Version 1.0
 */
@Component
// @Slf4j
public class RedisLock {
    private static RedisTemplate<String, Object> redisTemplate;

    private static StringRedisTemplate stringRedisTemplate;

    @Autowired
    private void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate){
        RedisLock.stringRedisTemplate = stringRedisTemplate;
    }

    @Autowired
    private void setUserMessageService(RedisTemplate<String, Object> redisTemplate){
        RedisLock.redisTemplate = redisTemplate;
    }

    /**
     * 加锁
     * @param key
     * @param value
     * @param timeout 过期时间
     */
    public static boolean lock(String key, String value, Integer timeout){
        Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(key, value,timeout, TimeUnit.SECONDS);
        // if(!b) log.info("lock err!");
        return result;
    }

    /**
     * 释放锁
     * @param key
     */
    public static void releaseLock(String key){
        // log.info("releaseLock success!");
        redisTemplate.delete(key);
    }

}

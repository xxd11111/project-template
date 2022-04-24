package com.xxd.controller;


import com.xxd.entity.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * TODO
 *
 * @Author xxd
 * @Date 2021/12/13
 * @Version 1.0
 */
@RestController
public class RedisController {
    @Resource
    RedisTemplate<String, Object> redisTemplate;
    /**
     * @param userId
     * @return
     * @Cacheable： 1.方法运行之前，先去查询Cache（缓存组件），按照cacheNames指定的名字获取；
     * 2.去Cache中查找缓存的内容，使用一个key，默认就是方法的参数；
     * 3.没有查到缓存就调用目标方法
     * 4.将目标方法返回的结果，放进缓存中
     * 5.condition：指定符合条件的情况下才缓存；
     */
    @GetMapping("redis/{userId}")
    @Cacheable(value = "mall", key = "#userId")
    public String getUserByIdWithInject(@PathVariable String userId) {
        User user = new User();
        user.setId(Long.parseLong(userId));
        user.setName("张三");
        user.setPassword(String.valueOf(Math.random() * 100));
        return user.toString();
    }

    @GetMapping("redis2")
    public String demo(@PathVariable String id) {
        redisTemplate.multi();
        redisTemplate.exec();
        redisTemplate.opsForStream();
        return "z2Z";
    }

}

package com.xxd.redis.hyperLogLog;

import org.springframework.data.redis.core.RedisTemplate;

/**
 * @Author xxd
 * @Date 2021/12/21
 * @Version 1.0
 */
public class PfDemo {

    private RedisTemplate<String, String> redisTemplate;

    //统计 某个key或多个key下的不重复value数量，是个近似估计，但是空间占用比set小的多
    public void test() {
        redisTemplate.opsForHyperLogLog().add("view1", "user1", "user2","user2");
        redisTemplate.opsForHyperLogLog().add("view2", "user1", "user2","user3");
        Long result1 = redisTemplate.opsForHyperLogLog().size("view1","view2");
        Long result2 = redisTemplate.opsForHyperLogLog().size("view1");
        Long result3 = redisTemplate.opsForHyperLogLog().size("view2");
    }
}

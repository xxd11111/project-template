package com.xxd.redis.lock;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * redis可重入锁  依然存在超时问题
 *
 * @Author xxd
 * @Date 2021/12/14
 * @Version 1.0
 */
public class RedisWithReentrantLock {
    private ThreadLocal<Map<String, Integer>> locks = new ThreadLocal<>();

    private final RedisTemplate<String, Integer> redisTemplate;

    public RedisWithReentrantLock(RedisTemplate<String, Integer> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private boolean _lock(String key, Integer value, long time, TimeUnit timeUnit) {
        Boolean aBoolean = redisTemplate.opsForValue().setIfAbsent(key, value, time, timeUnit);
        if (null == aBoolean) return false;
        return aBoolean;
    }

    private void _unlock(String key) {
        redisTemplate.delete(key);
    }

    private Map<String, Integer> currentLocks() {
        Map<String, Integer> stringIntegerMap = locks.get();
        if (null != stringIntegerMap) {
            return stringIntegerMap;
        }
        locks.set(new HashMap<>());
        return locks.get();
    }

    public boolean lock(String key, Integer value, long time, TimeUnit timeUnit) {
        Map<String, Integer> refs = currentLocks();
        Integer refCnt = refs.get(key);
        if (null != refCnt) {
            refs.put(key, refCnt + 1);
            return true;
        }
        boolean ok = this._lock(key, value, time, timeUnit);
        if (!ok) return false;
        refs.put(key, 1);
        return true;
    }

    public boolean unLock(String key) {
        Map<String, Integer> refs = currentLocks();
        Integer refCnt = refs.get(key);
        if (null == refCnt) return false;
        refCnt -= 1;
        if (refCnt > 0) {
            refs.put(key, refCnt);
        } else {
            refs.remove(key);
            this._unlock(key);
        }
        return true;
    }
}

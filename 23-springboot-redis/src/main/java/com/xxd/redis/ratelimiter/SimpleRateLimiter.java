package com.xxd.redis.ratelimiter;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.util.ByteUtils;

import java.nio.ByteBuffer;
import java.util.List;

/**
 * 简单redis限流，滑动时间窗方式，记录某时间段操作记录，超过则不执行，每次操作需要维护存储空间
 * 缺陷：量大的情况下会消耗大量存储空间
 *
 * @Author xxd
 * @Date 2021/12/20
 * @Version 1.0
 */
public class SimpleRateLimiter {
    private RedisTemplate redisTemplate;

    public boolean isActionAllowed(String userId, String actionKey, int period, int maxCount) {
        String StringKey = String.format("hist:%S:%S", userId, actionKey);
        byte[] key = stringToBytes(StringKey);

        long nowTs = System.currentTimeMillis();
        byte[] value = stringToBytes("" + nowTs);

        List<Boolean> list = redisTemplate.executePipelined(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                redisConnection.openPipeline();
                redisConnection.zAdd(key, nowTs, value);
                redisConnection.zRemRangeByScore(key, 0, nowTs - period * 1000);
                Long result = redisConnection.zCard(key);
                redisConnection.expire(key, period + 1);
                return result <= maxCount;
            }
        });
        return list.get(0);
    }

    private byte[] stringToBytes(String str) {
        ByteBuffer byteBuffer = ByteUtils.getByteBuffer(str);
        return ByteUtils.getBytes(byteBuffer);
    }
}

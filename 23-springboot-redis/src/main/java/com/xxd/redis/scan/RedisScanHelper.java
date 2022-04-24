package com.xxd.redis.scan;

import org.springframework.data.redis.connection.RedisClusterConnection;
import org.springframework.data.redis.connection.RedisClusterNode;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisClusterConnection;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * redis的scan使用，避免使用keys,会造成redis锁影响性能
 *
 * @Author xxd
 * @Date 2021/12/21
 * @Version 1.0
 */
@Component
public class RedisScanHelper {

    @Resource
    RedisTemplate redisTemplate;

    public Set<String> scanMatch(String matchKey) {
        Set<String> keys = new HashSet<>();
        RedisConnectionFactory connectionFactory = redisTemplate.getConnectionFactory();
        RedisConnection redisConnection = connectionFactory.getConnection();
        Cursor<byte[]> scan = null;
        if(redisConnection instanceof JedisClusterConnection){
            RedisClusterConnection clusterConnection = connectionFactory.getClusterConnection();
            Iterable<RedisClusterNode> redisClusterNodes = clusterConnection.clusterGetNodes();
            for (RedisClusterNode next : redisClusterNodes) {
                scan = clusterConnection.scan(next, ScanOptions.scanOptions().match(matchKey).count(Integer.MAX_VALUE).build());
                while (scan.hasNext()) {
                    keys.add(new String(scan.next()));
                }
                try {
                    scan.close();
                } catch (IOException e) {
                    // log.error("scan遍历key关闭游标异常",e);
                }
            }
            return keys;
        }
        if(redisConnection instanceof JedisConnection){
            scan = redisConnection.scan(ScanOptions.scanOptions().match(matchKey).count(Integer.MAX_VALUE).build());
            while (scan.hasNext()){
                //找到一次就添加一次
                keys.add(new String(scan.next()));
            }
            try {
                scan.close();
            } catch (IOException e) {
                // log.error("scan遍历key关闭游标异常",e);
            }
            return keys;
        }

        return keys;

    }
}

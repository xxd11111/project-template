package com.xxd.redis.geo;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

/**
 * redis geoHash算法使用
 *
 * @Author xxd
 * @Date 2021/12/21
 * @Version 1.0
 */
public class RedisGeoDemo {
    RedisTemplate<String,String> redisTemplate;

    void demo(){
        String key = "earth";

        Point point1 = new Point(12, 12);
        Point point2 = new Point(13, 13);
        Point point3 = new Point(14, 14);
        redisTemplate.opsForGeo().add(key, point1, "JiuJiang");
        redisTemplate.opsForGeo().add(key, point2, "NanChang");
        redisTemplate.opsForGeo().add(key, point3, "GanZhou");

        List<Point> jiuJiang = redisTemplate.opsForGeo().position(key, "JiuJiang");
        Distance distance = redisTemplate.opsForGeo().distance(key, "JiuJiang", "NanChang");
        GeoResults<RedisGeoCommands.GeoLocation<String>> results = redisTemplate.opsForGeo().radius(key, "JiuJiang", 200);

    }}

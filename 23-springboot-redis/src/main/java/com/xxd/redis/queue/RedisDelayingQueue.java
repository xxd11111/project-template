package com.xxd.redis.queue;

import com.google.gson.Gson;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Random;
import java.util.Set;
import java.util.UUID;

/**
 * redis延时队列
 * @Author xxd
 * @Date 2021/12/14
 * @Version 1.0
 */
public class RedisDelayingQueue<T> {
    static class TaskItem<T>{
        public String id;
        public T msg;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public T getMsg() {
            return msg;
        }

        public void setMsg(T msg) {
            this.msg = msg;
        }
    }

    private final RedisTemplate<String, String> redisTemplate;
    private final String queueKey;

    public RedisDelayingQueue(RedisTemplate<String, String> redisTemplate, String queueKey) {
        this.redisTemplate = redisTemplate;
        this.queueKey = queueKey;
    }

    public void delay(T msg) {
        TaskItem<T> task = new TaskItem<>();
        task.id = UUID.randomUUID().toString();
        task.msg = msg;
        Gson gson = new Gson();
        String s = gson.toJson(task);
        redisTemplate.opsForZSet().add(queueKey, s, System.currentTimeMillis() + 5000);
    }

    //可优化：使用lua 将rangeByScore+remove整合为原子操作
    public void loop() {
        while (!Thread.interrupted()) {
            // 只取一条
            Set<String> values = redisTemplate.opsForZSet().rangeByScore(queueKey, 0, System.currentTimeMillis(), 0, 1);
            if (null == values || values.isEmpty()) {
                // try {
                //     Thread.sleep(500);
                // } catch (InterruptedException e) {
                //     break;
                // }
                continue;
            }
            String s = values.iterator().next();
            Long remove = redisTemplate.opsForZSet().remove(queueKey, s);
            //说明拿到了数值
            if (null != remove && remove > 0) {
                Gson gson = new Gson();
                TaskItem taskItem = gson.fromJson(s, TaskItem.class);
                this.handleMsg((T) taskItem.msg);
            }
        }
    }

    public void handleMsg(T msg) {
        System.out.println(msg);
    }

}

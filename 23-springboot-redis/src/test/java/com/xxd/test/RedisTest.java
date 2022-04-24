package com.xxd.test;

import com.xxd.redis.lock.RedisWithReentrantLock;
import com.xxd.redis.queue.RedisDelayingQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Author xxd
 * @Date 2021/12/13
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test1(){
        Object o = redisTemplate.opsForHash().get("mall", "ss");
        System.out.println(o.toString());
    }

    @Test
    public void test2(){
        CountDownLatch countDownLatch = new CountDownLatch(2);


        new Thread(new Runnable() {
            @Override
            public void run() {
                RedisWithReentrantLock lock = new RedisWithReentrantLock(redisTemplate);
                countDownLatch.countDown();
                while (!lock.lock("123", 123, 5, TimeUnit.SECONDS)) {
                }
                System.out.println("true:lock:" + Thread.currentThread().getName()+":"+System.nanoTime());
                System.out.println(lock.unLock("123") + ":unlock:" + Thread.currentThread().getName()+":"+System.nanoTime());
            }
        }).start();

        RedisWithReentrantLock lock = new RedisWithReentrantLock(redisTemplate);
        countDownLatch.countDown();
        while (!lock.lock("123", 123, 5, TimeUnit.SECONDS)) {
        }
        System.out.println("true:lock:" + Thread.currentThread().getName()+":"+System.nanoTime());
        System.out.println(lock.lock("123", 123, 5, TimeUnit.SECONDS) + ":lock:" + Thread.currentThread().getName()+":"+System.nanoTime());
        System.out.println(lock.unLock("123") + ":unlock:" + Thread.currentThread().getName()+":"+System.nanoTime());
        System.out.println(lock.unLock("123") + ":unlock:" + Thread.currentThread().getName()+":"+System.nanoTime());
    }

    @Test
    public void test3(){
        RedisDelayingQueue<Human> queue = new RedisDelayingQueue<>(redisTemplate, "q-demo");
        Thread producer = new Thread(){
            @Override
            public void run() {
                for (int i = 0; i< 10; i++ ){
                    queue.delay(new Human("test", i));
                }
            }
        };

        Thread consumer = new Thread(){
            @Override
            public void run() {
                queue.loop();
            }
        };

        producer.start();
        consumer.start();

        try {
            producer.join();
            Thread.sleep(6000);
            consumer.interrupt();
            consumer.join();
        }catch (InterruptedException e){
            System.out.println("error++++++");
        }
    }
}

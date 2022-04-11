package com.xxd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author xxd
 * @Date 2021/12/10
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan("com.xxd.mapper")
public class RedisApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
    }
}
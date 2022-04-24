package com.xxd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * springboot 启动类
 * @Author xxd
 * @Date 2021/12/9
 * @Version 1.0
 */
@SpringBootApplication
public class LogApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(LogApplication.class);
        app.run(args);
    }
}

package com.xxd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * springboot 启动类
 * @Author xxd
 * @Date 2021/12/9
 * @Version 1.0
 */
//不加exclude= {DataSourceAutoConfiguration.class},跑不起来,会循环依赖
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class MybatisApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MybatisApplication.class);
        app.run(args);
    }
}

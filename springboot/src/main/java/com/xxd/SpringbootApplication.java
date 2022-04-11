package com.xxd;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * springboot 启动类
 * @Author xxd
 * @Date 2021/12/9
 * @Version 1.0
 */
@SpringBootApplication
public class SpringbootApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringbootApplication.class);
        //关闭banner.txt图案
        app.setBannerMode(Banner.Mode.OFF);
        // 在运行Spring Boot jar文件时，可以使用命令java -jar xxx.jar --server.port=8081来改变端口的值。这条命令等价于我们手动到application.properties中修改（如果没有这条属性的话就添加）server.port属性的值为8081。
        app.setAddCommandLineProperties(false);

        app.run(args);
    }
}

package com.xxd.cors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description 方法上加注解,该方法可跨域
 * @Author xxd
 * @Date 2022/1/4 9:40
 * @Version 1.0
 */
@Controller
public class CorsController2 {
    @RequestMapping("/hello2")
    @CrossOrigin(origins = "*")
    //@CrossOrigin(value = "http://localhost:8081") //指定具体ip允许跨域
    public String hello2() {
        return "hello world2";
    }
}

package com.xxd.cors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 该controller所有方法跨域
 * @Author xxd
 * @Date 2022/1/4 9:39
 * @Version 1.0
 */
@RestController
@CrossOrigin(origins = "*")
public class CorsController {
    @RequestMapping("/hello1")
    public String hello() {
        return "hello world";
    }
}


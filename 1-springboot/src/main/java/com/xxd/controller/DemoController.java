package com.xxd.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 一个平平无奇的controller
 * @Author xxd
 * @Date 2021/12/9
 * @Version 1.0
 */
@RestController
public class DemoController {
    @RequestMapping("/")
    String index() {
        return "hello spring boot";
    }
}

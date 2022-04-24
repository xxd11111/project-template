package com.xxd.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author xxd
 * @Date 2021/12/28 11:10
 * @Version 1.0
 */

@RestController
public class DemoController {
    @PostMapping("/1")
    public String demo(){
        return "demo";
    }
}
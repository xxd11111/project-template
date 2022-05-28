package com.xxd.websocket.controller;

import com.xxd.websocket.service.DemoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/api")
public class DemoController {
    @Resource
    private DemoService demoService;

    @GetMapping("/start")
    public String start(){
        return "index";
    }

    @PostMapping("/pushToWeb")
    //服务器端向客户端推送消息
    public String pushToWeb(@RequestBody MyInfo info){

        demoService.printTime();
        return "ok";
    }

}

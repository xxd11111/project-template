package com.xxd.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxd.entity.User;
import com.xxd.mapper.UserMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 *
 * @Author xxd
 * @Date 2021/12/12
 * @Version 1.0
 */
@RestController
public class DemoController {
    @Resource
    UserMapper userMapper;

    @GetMapping("/1")
    public User getOne(){
        return userMapper.selectById(1);
    }

    @GetMapping("/2")
    public Page<User> pageQuery(){
        return userMapper.selectPage(new Page<>(1,2), null);
    }
}

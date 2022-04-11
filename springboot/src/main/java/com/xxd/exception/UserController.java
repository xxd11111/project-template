package com.xxd.exception;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description UserController
 * @Author xxd
 * @Date 2022/2/8 15:11
 * @Version 1.0
 */
@RestController
@RequestMapping("user")
public class UserController {

    @GetMapping("t1/{id:\\d+}")
    public void get1(@PathVariable String id) {
        throw new RuntimeException("user not exist");
    }

    @GetMapping("t2/{id:\\d+}")
    public void get2(@PathVariable String id) {
        throw new UserNotExistException(id);
    }
}

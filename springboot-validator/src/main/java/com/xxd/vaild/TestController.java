package com.xxd.vaild;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @Description TestController
 * @Author xxd
 * @Date 2022/2/8 17:08
 * @Version 1.0
 */

@RestController
@Validated
public class TestController {

    @GetMapping("test1")
    public String test1(
            @NotBlank(message = "{required}") String name,
            @Email(message = "{invalid}") String email) {
        return "success";
    }
}

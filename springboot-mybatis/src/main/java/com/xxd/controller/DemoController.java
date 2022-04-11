package com.xxd.controller;

import com.github.pagehelper.PageInfo;
import com.xxd.entity.Blog;
import com.xxd.entity.Ejdcxx;
import com.xxd.mapper.BlogMapper;
import com.xxd.service.EjdcxxService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author xxd
 * @Date 2021/12/9
 * @Version 1.0
 */
@RestController
public class DemoController {
    @Resource
    private EjdcxxService ejdcxxService;
    @Resource
    BlogMapper blogMapper;

    @RequestMapping("/1")
    List<Ejdcxx> index1() {
        return ejdcxxService.select();
    }
    @RequestMapping("/2")
    List<Blog> index2() {
        return blogMapper.select();
    }
    // 分页
    @RequestMapping("/3")
    PageInfo<Ejdcxx> index3() {
        return ejdcxxService.pageQuery();
    }
}
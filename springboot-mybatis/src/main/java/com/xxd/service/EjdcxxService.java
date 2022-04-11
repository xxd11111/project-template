package com.xxd.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxd.entity.Ejdcxx;
import com.xxd.mapper.EjdcxxMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * TODO
 *
 * @Author xxd
 * @Date 2021/12/10
 * @Version 1.0
 */
@Service
public class EjdcxxService {
    @Resource
    private EjdcxxMapper ejdcxxMapper;

    //普通查询
    public List<Ejdcxx> select() {
        return ejdcxxMapper.select();
    }

    //分页查询
    public PageInfo<Ejdcxx> pageQuery(){
        PageHelper.startPage(1, 10);
        List<Ejdcxx> list = ejdcxxMapper.select();
        return new PageInfo<>(list);
    }
}

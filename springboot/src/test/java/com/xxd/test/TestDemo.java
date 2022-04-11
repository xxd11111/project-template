package com.xxd.test;

import com.xxd.value.DIYValue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * 测试用例 放在springbootApplication子目录下才能跑起来
 * @Author xxd
 * @Date 2021/12/9
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDemo {
    @Resource
    private DIYValue diyValue;

    @Test
    public void diyTest() {
        //测试value是否能从yml中读取数据
        diyValue.method();
    }
}

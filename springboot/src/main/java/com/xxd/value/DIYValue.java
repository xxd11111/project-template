package com.xxd.value;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * springboot 允许自定义属性
 *
 * @Author xxd
 * @Date 2021/12/9
 * @Version 1.0
 */
@Component
//设置导入的文件，否则默认application.yml
@PropertySource("classpath:DIY.yml")
public class DIYValue {
    //如果未指定全名，按照最后一个算 music.type image.type sport.type
    // @Value("${myset.type}")
    @Value("${type}")
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void method(){
        System.out.println(type);
    }
}

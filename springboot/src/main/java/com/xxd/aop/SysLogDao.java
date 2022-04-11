package com.xxd.aop;

import org.springframework.stereotype.Component;

/**
 * @Description 模拟系统日志dao
 * @Author xxd
 * @Date 2021/12/29 11:06
 * @Version 1.0
 */

@Component
public class SysLogDao {
    void saveSysLog(SysLog syslog){
        System.out.println(syslog);
    }
}

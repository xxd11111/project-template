package com.xxd.datasource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 多数据源配置
 * @Author xxd
 * @Date 2021/12/10
 * @Version 1.0
 */
@Aspect
@Component
public class DataSourceAspect {

    @Pointcut("@annotation(com.xxd.datasource.DS)")
    public void dataSourcePointCut() {

    }

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        DS dataSource = method.getAnnotation(DS.class);
        if(dataSource == null){
            DynamicDataSource.setDataSource(DataSourceName.MASTER);
        }else {
            DynamicDataSource.setDataSource(dataSource.name());
        }

        try {
            return point.proceed();
        } finally {
            DynamicDataSource.clearDataSource();
        }
    }
}
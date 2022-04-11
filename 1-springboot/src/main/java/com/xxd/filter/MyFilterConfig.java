package com.xxd.filter;

import com.xxd.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 配置方式二
 * 除了在过滤器类上加注解外，我们也可以通过FilterRegistrationBean来注册过滤器.
 * 定义一个WebConfig类，加上@Configuration注解表明其为配置类，然后通过FilterRegistrationBean来注册过滤器.
 *
 * @Author xxd
 * @Date 2022/2/8 15:53
 * @Version 1.0
 */
@Configuration
public class MyFilterConfig {
    @Bean
    public FilterRegistrationBean timeFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        TimeFilter timeFilter = new TimeFilter();
        filterRegistrationBean.setFilter(timeFilter);

        List<String> urlList = new ArrayList<>();
        urlList.add("/*");

        filterRegistrationBean.setUrlPatterns(urlList);
        return filterRegistrationBean;
    }
}

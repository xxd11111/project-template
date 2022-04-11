package com.xxd.filter;


import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

/**
 * @Description 配置方式一:可通过在TimeFilter上加上如下注解
 * @Author xxd
 * @Date 2022/2/8 15:51
 * @Version 1.0
 */

//@Component
//@WebFilter(urlPatterns = {"/*"})
public class TimeFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filter:过滤器初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("filter:开始执行过滤器");
        Long start = new Date().getTime();
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("filter:【过滤器】耗时 " + (new Date().getTime() - start));
        System.out.println("filter:结束执行过滤器");
    }

    @Override
    public void destroy() {
        System.out.println("filter:过滤器销毁");
    }
}
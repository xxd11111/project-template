package com.xxd.interceptor;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @Description TimeInterceptor
 * @Author xxd
 * @Date 2022/2/8 16:00
 * @Version 1.0
 */
public class TimeInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("interceptor:处理拦截之前");
        httpServletRequest.setAttribute("startTime", new Date().getTime());
        System.out.println("interceptor:" + ((HandlerMethod) o).getBean().getClass().getName());
        System.out.println("interceptor:" + ((HandlerMethod) o).getMethod().getName());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("interceptor:开始处理拦截");
        Long start = (Long) httpServletRequest.getAttribute("startTime");
        System.out.println("interceptor:【拦截器】耗时 " + (new Date().getTime() - start));
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("interceptor:处理拦截之后");
        Long start = (Long) httpServletRequest.getAttribute("startTime");
        System.out.println("interceptor:【拦截器】耗时 " + (new Date().getTime() - start));
        System.out.println("interceptor:异常信息 " + e);
    }
}
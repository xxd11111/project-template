package com.xxd.config;/**
 * @Auther: xxd
 * @Date: 2022/4/18
 * @Description: com.xxd.config
 * @Version: 1.0
 */

import com.xxd.demo.IWebserviceDemo;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

/**
 * @Description: webservice配置类
 * @author: xxd
 * @date: 2022.04.18
 */
@Configuration
public class WebServiceConfig {

    @Autowired
    private IWebserviceDemo demoWebService;

    @Bean(name = "cxfServlet")
    public ServletRegistrationBean cxfServlet(){
        //这个地址可以随意修改，用于访问的前缀
        return new ServletRegistrationBean(new CXFServlet(), "/webservice/*");
    }

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus(){
        return new SpringBus();
    }

    //有几个webservice类就建几个
    @Bean(name = "demoWebserviceEndPoint")
    public Endpoint demoWebserviceEndPoint(){
        EndpointImpl endpoint = new EndpointImpl(springBus(), demoWebService);
        endpoint.publish("/demo");
        return endpoint;
    }
}

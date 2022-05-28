package com.xxd.demo;/**
 * @Auther: xxd
 * @Date: 2022/4/18
 * @Description: com.xxd.webservice
 * @Version: 1.0
 */

/**
 * @Description: webservice demo
 * @author: xxd
 * @date: 2022.04.18
 */

import org.springframework.stereotype.Service;

import javax.jws.WebService;

@Service
@WebService(name = "DemoWebService",
        targetNamespace = "http://demo.xxd.com/",
        endpointInterface = "com.xxd.demo.IWebserviceDemo")
public class WebserviceDemo implements IWebserviceDemo{
    @Override
    public String testMethod01(String param1, String param2, String param3) {
        return "demoMethod01:" + param1 + param2 + param3;
    }

    @Override
    public String testMethod02(String param1, String param2, String param3) {
        return "demoMethod02:" + param1 + param2 + param3;
    }
}

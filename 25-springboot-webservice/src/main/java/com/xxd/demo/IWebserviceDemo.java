package com.xxd.demo;/**
 * @Auther: xxd
 * @Date: 2022/4/18
 * @Description: com.xxd.webservice
 * @Version: 1.0
 */

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @Description: 接口demo
 * @author: xxd
 * @date: 2022.04.18
 */
@WebService
public interface IWebserviceDemo {
    @WebMethod
    String testMethod01(
            @WebParam(targetNamespace = "http://demo.xxd.com/") String param1,
            @WebParam(targetNamespace = "http://demo.xxd.com/") String param2,
            @WebParam(targetNamespace = "http://demo.xxd.com/") String param3
    );

    @WebMethod
    String testMethod02(
            @WebParam(name = "param1", targetNamespace = "http://demo.xxd.com/") String param1,
            @WebParam(name = "param2", targetNamespace = "http://demo.xxd.com/") String param2,
            @WebParam(name = "param3", targetNamespace = "http://demo.xxd.com/") String param3
            );
}

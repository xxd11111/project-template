##使用说明
````
总体上3个类
接口类
实现类
配置类

xmlns="http://demo.xxd.com/"     
http://demo.xxd.com/与targetNamespace相同

@WebService(name = "DemoWebService",
targetNamespace = "http://demo.xxd.com/",
endpointInterface = "com.xxd.demo.IWebserviceDemo")

name 类名
targetNamespace  http://逆序包名路径
endpointInterface  实现的接口
````

## 调试方法1
```
postman调试
请求类型 post
请求url http://localhost:8081/webservice/demo
请求参数选择body,raw,xml
参数内容如下
<?xml version="1.0" encoding="utf-8"?>
<soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
　　<soap:Body>
　　　　<testMethod01 xmlns="http://demo.xxd.com/">
　　　　　　<arg0>String</arg0>
　　　　　　<arg1>param2</arg1>
　　　　　　<arg2>param3</arg2>
　　　　</testMethod01>
　　</soap:Body>
</soap:Envelope>

返回结果
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
    <soap:Body>
        <ns2:testMethod01Response xmlns:ns2="http://demo.xxd.com/">
            <return>demoMethod01:Stringparam2param3</return>
        </ns2:testMethod01Response>
    </soap:Body>
</soap:Envelope>
```

## 调试方法2
```
postman调试
请求类型 post
请求url http://localhost:8081/webservice/demo
请求参数选择body,raw,xml
参数内容如下
<?xml version="1.0" encoding="utf-8"?>
<soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
　　<soap:Body>
　　　　<testMethod02 xmlns="http://demo.xxd.com/">
　　　　　　<param1>String</param1>
　　　　　　<param2>param2</param2>
　　　　　　<param3>param3</param3>
　　　　</testMethod02>
　　</soap:Body>
</soap:Envelope>

返回结果
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
    <soap:Body>
        <ns2:testMethod02Response xmlns:ns2="http://demo.xxd.com/">
            <return>demoMethod02:Stringparam2param3</return>
        </ns2:testMethod02Response>
    </soap:Body>
</soap:Envelope>
```

## 调试方法2
```
postman调试
请求类型 get
请求url http://localhost:8081/webservice/demo?wsdl
请求参数 无

返回结果
<?xml version='1.0' encoding='UTF-8'?>
<wsdl:definitions xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:tns="http://demo.xxd.com" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:ns2="http://schemas.xmlsoap.org/soap/http" xmlns:ns1="http://demo.xxd.com/" name="WebserviceDemoService" targetNamespace="http://demo.xxd.com">
    <wsdl:import location="http://localhost:8081/webservice/demo?wsdl=IWebserviceDemo.wsdl" namespace="http://demo.xxd.com/">
    </wsdl:import>
    <wsdl:binding name="WebserviceDemoServiceSoapBinding" type="ns1:IWebserviceDemo">
        <soap:binding style="document" transport="http://schemas.xmlsoap.org/soap/http"/>
        <wsdl:operation name="testMethod01">
            <soap:operation soapAction="" style="document"/>
            <wsdl:input name="testMethod01">
                <soap:body use="literal"/>
            </wsdl:input>
            <wsdl:output name="testMethod01Response">
                <soap:body use="literal"/>
            </wsdl:output>
        </wsdl:operation>
        <wsdl:operation name="testMethod02">
            <soap:operation soapAction="" style="document"/>
            <wsdl:input name="testMethod02">
                <soap:body use="literal"/>
            </wsdl:input>
            <wsdl:output name="testMethod02Response">
                <soap:body use="literal"/>
            </wsdl:output>
        </wsdl:operation>
    </wsdl:binding>
    <wsdl:service name="WebserviceDemoService">
        <wsdl:port binding="tns:WebserviceDemoServiceSoapBinding" name="DemoWebServicePort">
            <soap:address location="http://localhost:8081/webservice/demo"/>
        </wsdl:port>
    </wsdl:service>
</wsdl:definitions>
```
package com.by.webService.simple.server;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

//1、添加注解
@WebService
public class HelloWebService {
	// 2、至少包含一个可以对外公开的服务
	public String sayHello(String name) {
		System.err.println("invoke " + name);
		return "Hello " + name;
	}
	@WebMethod(exclude=true)
	public String sayTest(String name) {
		System.err.println("invoke " + name);
		return "Hello " + name;
	}

	public static void main(String[] args) {
		// 3、第一个参数称为Binding即绑定地址，
		// 第二个参数是实现者，即谁提供服务
		Endpoint.publish("http://localhost:9998/helloWebService", new HelloWebService());
		System.out.println("服务启动");
	}

}

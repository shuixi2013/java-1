package com.by.webService.axis2.client;

import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

public class RpcClient {
	/**
	 * 使用RPC方式调用web service （RPC： Remote Procedure Call protocol，远程过程调用协议 )
	 * 
	 * @throws AxisFault
	 */
	public static void testRpc() throws AxisFault {
		// RPC客户端服务类
		RPCServiceClient serviceClient = new RPCServiceClient();
		Options options = serviceClient.getOptions();
		// 指定调用web service的URL
		String url = "http://localhost:8080/axis2/services/SimpleService";
		EndpointReference taretRef = new EndpointReference(url);
		options.setTo(taretRef);

		// 指定getGreeting方法的参数值
		Object[] opsArgs = new Object[] { "和尚" };

		// 指定getGreeting方法的返回值的数据类型的calss对象
		Class[] returnTypes = new Class[] { String.class };

		// 指定要调用的getGreeting方法及其WSDL文件的命名空间
		QName qName = new QName("http://ws.apache.org/axis2", "getGreeting");

		// 调用getGreeting方法并输出改方法的返回值
		/**
		 * 其中第一个参数的类型是QName对象，表示要调用的方法名；<br>
		 * 第二个参数表示要调用的WebService方法的参数值，参数类型为Object[]；<br>
		 * 第三个参数表示WebService方法的返回值类型的Class对象，参数类型为Class[]。<br>
		 */
		System.out.println(serviceClient.invokeBlocking(qName, opsArgs, returnTypes)[0]);

		/** 下面调用getPrice方法的代码，与上面调用getPrice方法的代码类似 */
		qName = new QName("http://ws.apache.org/axis2", "getPrice");
		returnTypes = new Class[] { int.class };
		System.out.println(serviceClient.invokeBlocking(qName, new Object[] {}, returnTypes)[0]);

		/**
		 * 下面调用getPrice方法的代码，与上面调用setCount方法的代码类似<br>
		 * 如果被调用的WebService方法没有返回值，应使用RPCServiceClient类的invokeRobust方法<br>
		 */
		qName = new QName("http://ws.apache.org/axis2", "setCount");
		serviceClient.invokeRobust(qName, new Object[] { new Integer(100) });
	}

	public static void main(String[] args) {
		try {
			testRpc();
		} catch (AxisFault e) {
			e.printStackTrace();
		}
	}

}

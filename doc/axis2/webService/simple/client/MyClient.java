package by.webService.simple.client;

public class MyClient {

	public static void main(String[] args) {
		HelloWebServiceService hwss = new HelloWebServiceService();
		HelloWebService hws = hwss.getHelloWebServicePort();
		String result = hws.sayHello("hanxuemin");
		System.out.println(result);
	}
}

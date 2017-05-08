package by.webService.axis2.server;
import java.util.Random;
/**
 * axis2服务端代码,放在了tomcat 8.0 WEBINF/pojo中负责发布服务
 * @author baoyang
 */
public class SimpleService {
		
		public String getGreeting(String name) {
			System.out.println("===============服务器打印：调用getGreeting方法");
			return "你好，" + name;
		}
		
		public int getPrice() {
			System.out.println("===============服务器打印：调用getPrice方法");
			return new Random().nextInt(1000);
		}
		
		public void setCount(int price) {
			System.out.println("===============服务器打印：调用setPrice方法: " + price);
		}

}

package com.by.lang.os;

import java.io.FileOutputStream;
import java.util.Map;
import java.util.Properties;

/**
 * @author RockAgen
 *
 */
public class SystemTest {
	/**
	 * @param args
	 * @return
	 */
	public static void getStatus() {

		String status = System.getProperty("java.version") + "\n" 
				+ System.getProperty("java.vendor") + "\n"
				+ System.getProperty("java.vendor.url") + "\n"
				+ System.getProperty("java.vm.specification.version")+ "\n" 
				+ System.getProperty("java.class.path")
				+ System.getProperty("os.name")+ "\n"
				+ System.getProperty("os.arch") + "\n"
				+ System.getProperty("os.version") + "\n"
				+ System.getProperty("user.name") + "\n"
				+ System.getProperty("user.dir");
		System.out.println(status);
	}

	public static void main(String[] args) throws Exception {
		getStatus();
		Map<String, String> env = System.getenv();
		for (String name : env.keySet()) {
			System.out.println(name + " ---> " + env.get(name));
		}
		System.out.println(System.getenv("JAVA_HOME"));
		
		Properties props = System.getProperties();
		
		props.store(new FileOutputStream("props.txt"), "System Properties");
	}

}

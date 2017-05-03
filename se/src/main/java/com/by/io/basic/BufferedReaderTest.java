
package com.by.io.basic;

/**
 * @author baoyang
 */
import java.io.BufferedReader;
import java.io.FileReader;

public class BufferedReaderTest {

	public static String read(String filename) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String s;
		StringBuffer sb = new StringBuffer();
		while ((s = br.readLine()) != null) {
			sb.append(s + "\n");
		}
		br.close();
		return sb.toString();
	}

	public static void main(String[] args) throws Exception {
		System.out.println(read("src/InputStreamTest.java"));
	}
}

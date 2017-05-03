package com.by.io.commons;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.output.FileWriterWithEncoding;

public class FileWriterWithEncodingTest {
	public static void main(String[] args) {
		File file = new File("/test.txt");
		try {
			FileWriterWithEncoding fwwe = new FileWriterWithEncoding(file,"gbk");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}

package com.by.lang.os;

import org.apache.commons.lang3.SystemUtils;

public class systemUtilsTest {
	public static void main(String[] args) {
		System.out.println("获得系统文件分隔符.");
        System.out.println(SystemUtils.FILE_SEPARATOR);
        System.out.println("获得源文件编码.");
        System.out.println(SystemUtils.FILE_ENCODING);
        System.out.println("获得ext目录.");
        System.out.println(SystemUtils.JAVA_EXT_DIRS);
        System.out.println("获得java版本.");
        System.out.println(SystemUtils.JAVA_VM_VERSION);
        System.out.println("获得java厂商.");
        System.out.println(SystemUtils.JAVA_VENDOR);
	}
}

package com.by.io.commons;

import java.io.IOException;

import org.apache.commons.io.FileSystemUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FileSystemUtilsTest {
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * 获取磁盘空余空间
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testFreeSpace() throws IOException {
		// 以字节为单位
		System.out.println(FileSystemUtils.freeSpace("c:\\") / 1024 / 1024 / 1024);
		System.out.println(FileSystemUtils.freeSpace("d:\\") / 1024 / 1024 / 1024);
		// 以k为单位
		System.out.println(FileSystemUtils.freeSpaceKb("e:\\") / 1024 / 1024);
		System.out.println(FileSystemUtils.freeSpaceKb("f:\\") / 1024 / 1024);
		
	}

}

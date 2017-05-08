package by.io.commons;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LineIteratorTest {
	
	private String basePath = null;

	@Before
	public void setUp() throws Exception {
		basePath = System.getProperty("user.dir") + "\\file\\";
	}

	@After
	public void tearDown() throws Exception {
	}
	
	/**
	 * 测试行迭代器
	 * @throws IOException
	 */
	@Test
	public void testIterator() throws IOException{
		File file = new File(basePath + "a.txt");
		LineIterator li = FileUtils.lineIterator(file);
		while(li.hasNext()){
			System.out.println(li.nextLine());
		}
		LineIterator.closeQuietly(li);
	}

}

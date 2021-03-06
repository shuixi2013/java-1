package by.io.commons;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.filefilter.AndFileFilter;
import org.apache.commons.io.filefilter.EmptyFileFilter;
import org.apache.commons.io.filefilter.NameFileFilter;
import org.apache.commons.io.filefilter.NotFileFilter;
import org.apache.commons.io.filefilter.OrFileFilter;
import org.apache.commons.io.filefilter.PrefixFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FileFilterTest {
	// Filters，commons-io提供了多种过滤器，能够根据条件过滤指定目录下的文件，同时还支持过滤器组合，
		private static final String PARENT_DIR = "/Users/fly/work/GitHub/algorithm/src/main/java/com/fly/practice/apachecommonsio";

		public static void runExample() {
			System.out.println("File Filter example...");
			// NameFileFilter：获取指定目录下，符合给定文件列表的文件
			// 例如获取此例，获取PARENT_DIR目录下commonios和commonio.txt文件
			File dir = FileUtils.getFile(PARENT_DIR);
			String[] acceptedNames = { "commonios", "commonio.txt" };
			for (String file : dir.list(new NameFileFilter(acceptedNames,
					IOCase.INSENSITIVE))) {
				System.out.println("File found, named: " + file);
			}
			// WildcardFileFilter：支持正则匹配，获取指定目录下，满足正则的文件
			for (String file : dir.list(new WildcardFileFilter("*common*"))) {
				System.out.println("Wildcard file found, named: " + file);
			}
			// PrefixFileFilter：获取以给定字符串为前缀的文件名
			for (String file : dir.list(new PrefixFileFilter("common"))) {
				System.out.println("Prefix file found, named: " + file);
			}
			// SuffixFileFilter：：获取以给定字符串为后缀的文件名
			for (String file : dir.list(new SuffixFileFilter(".txt"))) {
				System.out.println("Suffix file found, named: " + file);
			}
			// OrFileFilter：支持传入多个过滤器，过滤器之间是或的关系
			for (String file : dir.list(new OrFileFilter(new WildcardFileFilter(
					"*ample*"), new SuffixFileFilter(".txt")))) {
				System.out.println("Or file found, named: " + file);
			}
			// AndFileFilter：支持传入多个过滤器，过滤器之间是且的关系
			for (String file : dir.list(new AndFileFilter(new WildcardFileFilter(
					"*ample*"), new NotFileFilter(new SuffixFileFilter(".txt"))))) {
				System.out.println("And/Not file found, named: " + file);
			}
		}
	
	private String basePath = null;

	@Before
	public void setUp() throws Exception {
		basePath = System.getProperty("user.dir") + "\\file\\";
	}

	@After
	public void tearDown() throws Exception {
	}
	
	/**
	 * 空内容文件过滤器
	 * @throws IOException
	 */
	@Test
	public void testEmptyFileFilter() throws IOException{
		File dir = new File(basePath);
		String[] files = dir.list(EmptyFileFilter.NOT_EMPTY);
		for (String file : files) {
			System.out.println(file);
		}
	}
	
	/**
	 * 文件名称后缀过滤器
	 * @throws IOException
	 */
	@Test
	public void testSuffixFileFilter() throws IOException{
		File dir = new File(basePath);
		String[] files = dir.list(new SuffixFileFilter("a.txt"));
		for (String file : files) {
			System.out.println(file);
		}
	}

}
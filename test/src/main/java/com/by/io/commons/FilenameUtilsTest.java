package by.io.commons;

import org.apache.commons.io.FilenameUtils;

public class FilenameUtilsTest {
	public static void main(String[] args) {
//		String filePath = "C:\\Users\\baoyang\\Desktop\\java-IO\\sina1896014801.db";
//		System.out.println(FilenameUtils.normalize(filePath));
//		System.out.println(FilenameUtils.(filePath));
		test1();
	}
	
	public static void test1(){
		
//		String filePath = "C:\\Users\\baoyang\\Desktop\\java-IO\\sina1896014801.db";
		
		String filePath = "/share/2016-11-12/test.html";
		
		// 获取文件路径,不忽略分隔符C:\Users\baoyang\Desktop\java-IO\
		System.out.println(FilenameUtils.getFullPath(filePath)); 
		// 获取文件路径,忽略分割符 C:\Users\baoyang\Desktop\java-IO
		System.out.println(FilenameUtils.getFullPathNoEndSeparator(filePath));
		// 获取盘符C:\
		System.out.println(FilenameUtils.getPrefix(filePath));  
		//Users\baoyang\Desktop\java-IO
		System.out.println(FilenameUtils.getPathNoEndSeparator(filePath)); 
		// 获取文件名,含后缀sina1896014801.db
		System.out.println(FilenameUtils.getName(filePath));
		// 获取文件名,不包含后缀sina1896014801
		System.out.println(FilenameUtils.getBaseName(filePath));
		//获取文件扩展名db
        System.out.println(FilenameUtils.getExtension(filePath));  
        // 通配符匹配false
        Boolean isMatch = FilenameUtils.wildcardMatch(filePath, "D:/Hello*");
        
        System.out.println(isMatch);
	}
}

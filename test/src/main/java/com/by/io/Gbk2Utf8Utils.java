package by.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import org.apache.commons.io.FileUtils;

public class Gbk2Utf8Utils {

	public static void main(String[] args) throws IOException {
		// GBK编码格式源码路径
		final String gbkDir = "D:\\eclipseworkspace\\test-java\\src\\";
		// 转为UTF-8编码格式源码路径
		final String utfDir = "D:\\eclipseworkspace\\future-common\\doc\\tmp";
		gbk2Utf(gbkDir, utfDir);
	}
	
	//有两种识别文件编码格式的方法 未测试
	public static void getEncode(String filePath) throws IOException {
		File file = new File(filePath);
		InputStream in = new java.io.FileInputStream(file);
		byte[] b = new byte[3];
		in.read(b);
		in.close();
		if (b[0] == -17 && b[1] == -69 && b[2] == -65) {
			System.out.println(file.getName() + "：编码为UTF-8");
		} else {
			System.out.println(file.getName() + "：可能是GBK，也可能是其他编码");
		}
	}

	public static String getCode(String fileName) throws Exception {
		java.io.FileInputStream fis = new java.io.FileInputStream(fileName);
		int p = (fis.read() << 8) + fis.read();
		String code = null;
		switch (p) {
			case 0xefbb:
				code = "UTF-8";
				break;
			case 0xfffe:
				code = "Unicode";
				break;
			case 0xfeff:
				code = "UTF-16BE";
				break;
			default:
				code = "GBK";
		}
		fis.close();
		return code;
	}

	/**
	 * gbk转换成utf-8
	 * 
	 * @param gbkDir
	 * @param utfDir
	 * @throws IOException
	 */
	public static void gbk2Utf(String gbkDir, String utfDir) throws IOException {
		File srcfile = new File(gbkDir);
		String tmp = srcfile.getPath();
		tmp = tmp.endsWith(File.separator) ? tmp : tmp + File.separator; // 矫正路径
		// 递归获取所有java文件
		Collection<File> javaGbkFileCol = FileUtils.listFiles(srcfile, new String[] { "java" }, true);
		for (File javaGbkFile : javaGbkFileCol) {
			String filePath = javaGbkFile.getPath();
			String name = filePath.replace(tmp, ""); // 相对路径
			File file = new File(utfDir, name); // UTF8格式文件路径
			// 使用GBK读取数据，然后用UTF-8写入数据
			FileUtils.write(file, FileUtils.readFileToString(javaGbkFile, "GBK"), "UTF-8");
			// FileUtils.writeLines(new File(utf8FilePath),
			// "UTF-8",FileUtils.readLines(javaGbkFile, "GBK"));
		}
		System.out.println("转换完成");
	}
}

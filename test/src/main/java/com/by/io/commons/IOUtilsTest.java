package by.io.commons;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.IOUtils;


public class IOUtilsTest {
	public void urlToString() {
		URL url = null;
		InputStream inputStream = null;
		try {
			url = new URL("http://www.21cn.com");
			inputStream = url.openStream();
			String contents = IOUtils.toString(inputStream);
			System.out.println(contents);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	 touch(File file) 更新指定文件最终修改时间和访问时间；若文件不存在则生成空文件；
//	 Closeable 是可以关闭的数据源或目标。调用 close 方法可释放对象保存的资源（如打开文件）。
	 public static void touch(File file) throws IOException {
		 OutputStream out = null;
		 if (!file.exists()) { 
//			 out = FileUtil.openOutputStream(file);
		 }
		 IOUtils.closeQuietly(out); 
		 boolean success = file.setLastModified(System.currentTimeMillis());
		 if (!success) { 
			 throw new IOException("Unable to set the last modification time for " + file); 
		 } 
	}
	 
}
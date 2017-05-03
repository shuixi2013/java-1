package work.productDefinition.utils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.commons.io.output.FileWriterWithEncoding;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.FileResourceLoader;
/**
 * beetl模板引擎使用工具类
 * @author baoyang
 *
 */
public class BeetlUtils {
	
	public boolean createSql(String sqlTemplate,Map<String, Object> sqlData){
		String root = System.getProperty("user.dir")+File.separator+"template";
		FileResourceLoader resourceLoader = new FileResourceLoader(root);
		Configuration cfg = null;
		try {
			cfg = Configuration.defaultConfiguration();
		} catch (IOException e) {
			e.printStackTrace();
		}
		GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
		gt.setSharedVars(sqlData);//共享数据变量
		Template t = gt.getTemplate(sqlTemplate);//"/s01/hello.txt"
		String str = t.render();
		FileWriterWithEncoding fileWritter = null;
		try {
			fileWritter = new FileWriterWithEncoding(root+"/tmp.sql","gbk",true);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		t.renderTo(fileWritter);
		try {
			System.out.println(new String(str.getBytes("gbk")));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return false;
	}
	public static void main(String[] args) {
		
	}
}

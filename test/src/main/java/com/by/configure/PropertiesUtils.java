package by.configure;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;

public class PropertiesUtils {
	private static final Pattern dotXml = Pattern.compile(".+\\.[xX][mM][lL]");
	
	public static void main(String[] args) throws Exception {
//		store();
		Properties prop = loadByClassPath("configure/log4j.properties");
		System.out.println(prop.toString());
	}

	public static void store() throws Exception {
		Properties props = new Properties();
		props.setProperty("username", "yeeku");
		props.setProperty("password", "123456");
		props.store(new FileOutputStream("a.ini"), "comment line"); // ע��
	}

	public static Properties loadByFilePath(String filepath) {
		InputStream is = null;
		try {
			is = new FileInputStream(filepath);
			return loadByInputStream(is,filepath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(is);
		}
		return new Properties();
	}
	
	public static Properties loadByInputStream(InputStream is,String path){
		Properties props = new Properties();
		try {
			if (dotXml.matcher(path).matches()) {
				props.loadFromXML(is);
			} else {
				props.load(is);
			}
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props;
	}

	public static Properties loadByClassPath(String filepath) {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream is = classLoader.getResourceAsStream(filepath);
		// InputStream in = getClass().getResourceAsStream(path);
		return loadByInputStream(is,filepath);
	}

}

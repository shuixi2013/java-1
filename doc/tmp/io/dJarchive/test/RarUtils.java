package io.dJarchive.test;

import io.csv.univocityParsers.CsvUtils;
import io.dJarchive.src.JArchive;
import io.dJarchive.src.JFile;
import io.dJarchive.src.JInputStream;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class RarUtils {

	public static void main(String args[]) throws FileNotFoundException,
			IOException {
		String unlDir = "C:\\Users\\baoyang\\Desktop\\test\\unlTest.rar";
		JInputStream js = JArchive.getJInputStream(new File(unlDir));
		genericOutput(js);
	}
	public static void genericOutput(String unlDir, Map<File,List<String[]>> map) throws IOException {
		JInputStream js = JArchive.getJInputStream(new File(unlDir));
		genericOutput(js,map);
	}
	public static void genericOutput(String unlDir, Map<File,List<String[]>> map,String[] filter) throws IOException {
		JInputStream js = JArchive.getJInputStream(new File(unlDir));
		genericOutput(js,map,filter);
	}

	public static void genericOutput(JInputStream is, Map<File,List<String[]>> map) throws IOException {
		JFile entry;
		while ((entry = is.getNextEntry()) != null) {
			if (entry.isArchive()) {
				genericOutput(JArchive.getJInputStream(entry, is),map);
				continue;
			}
			List<String[]> data = CsvUtils.ReadUnl(is);
			map.put(new File(entry.getName()),data);
		}
	}
	public static void genericOutput(JInputStream is, Map<File,List<String[]>> map,String[] filter) throws IOException {
		JFile entry;
		while ((entry = is.getNextEntry()) != null) {
			if (entry.isArchive()) {
				genericOutput(JArchive.getJInputStream(entry, is),map,filter);
				continue;
			}
			String name = FilenameUtils.getBaseName(entry.getName().toLowerCase());
			if(StringUtils.containsAny(name, filter)){
				List<String[]> data = CsvUtils.ReadUnl(is);
				map.put(new File(name),data);
			}
		}
	}
	public static void genericOutput(JInputStream is) throws IOException {
		JFile entry;
		while ((entry = is.getNextEntry()) != null) {
			if (entry.isArchive()) {
				genericOutput(JArchive.getJInputStream(entry, is));
				continue;
			}
			System.out.println(entry.getName());
			System.out.println(readFile(is));
		}
	}

	private static String readFile(InputStream is) throws IOException {
		byte[] b = new byte[10240];
		int len = b.length;
		int total = 0;
		while (total < len) {
			int result = is.read(b, total, len - total);
			if (result == -1) {
				break;
			}
			total += result;
		}
		return new String(b,"GBK");
	}

}

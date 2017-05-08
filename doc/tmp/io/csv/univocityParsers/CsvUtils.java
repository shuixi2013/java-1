package io.csv.univocityParsers;

import com.univocity.parsers.common.ParsingContext;
import com.univocity.parsers.common.processor.RowProcessor;
import com.univocity.parsers.csv.*;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class CsvUtils {

	public static void createCSVFile(String[] heads, List<String[]> rows,
			String outPutPath, String filename) throws IOException {
		//创建文件
		File csvFile = new File(outPutPath + filename + ".csv");
		FileOutputStream fos = FileUtils.openOutputStream(csvFile);
		Writer fileWriter = new OutputStreamWriter(fos);
		//设置格式
		CsvWriterSettings settings = new CsvWriterSettings();
//			CsvFormat format = settings.getFormat();
//			format.setDelimiter(';');
		//输出文件
		CsvWriter writer = new CsvWriter(fileWriter, settings);
		if(heads!=null){
			writer.writeHeaders(heads);
		}
		for (int i = 0; i < rows.size(); i++) {
			writer.writeRow(rows.get(i));
		}
		writer.close();
//		writer.writeRowsAndClose(rows);
	}

	public static List<String[]> ReadUnl(String filePath) throws IOException {
		File file = new File(filePath);
		return	ReadUnl(file);
	}
	
	/*读取.unl文件 */
	public static List<String[]> ReadUnl(File file) throws IOException {
		InputStream is = new FileInputStream(file);
		return ReadUnl(is);
	}
	/*通过is读取.unl文件*/
	public static List<String[]> ReadUnl(InputStream is) throws IOException {
		InputStreamReader reader = new InputStreamReader(is, "GBK");
		CsvParserSettings settings = new CsvParserSettings();
		CsvFormat format = settings.getFormat();
//		settings.getFormat().setLineSeparator("\r\n");
//		settings.setNullValue("");//null
		format.setDelimiter('|');
		CsvParser parser = new CsvParser(settings);
		List<String[]> list = parser.parseAll(reader);
		for (int i = 0; i < list.size(); i++) {
			String [] arr= list.get(i);
			int alen = arr.length-1;
			if(arr[alen]==null){//去除最后一列为null的情况
				arr = Arrays.copyOf(arr, alen);
			}
			list.set(i,arr);
		}
		return list;
	}
	
	public static void main(String[] args) {
		try {
			List<String[]> allRows = CsvUtils.ReadUnl("533_appl_age_lmt.unl");
			for(String[] arr :allRows){
				System.out.println(Arrays.toString(arr));
			}
			CsvUtils.createCSVFile(null, allRows, "", "304_cashval.csv");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 特殊处理业务
	 */
	public static List<String[]> SpecialReadTest(String filePath) throws IOException {
		
		 CsvParserSettings settings = new CsvParserSettings();
		 settings.setRowProcessor(new RowProcessor() {
			 StringBuilder stringBuilder = new StringBuilder();
			 /**
			 * 处理第一行数据之前，你可以根据业务逻辑做相关初始化配置。
			 **/
			 @Override
			 public void processStarted(ParsingContext context) {
				 System.out.println("Started to process rows of data.");
			 }
			 /**
			 * 根据你的业务逻辑，处理行数据
			 **/
			 @Override
			 public void rowProcessed(String[] row, ParsingContext context) {
				 System.out.println("The row in line #" + context.currentLine() + ": ");
				 for (String col : row) {
				 stringBuilder.append(col).append("\t");
			 	}
			 }
			
			 /**
			 * 所有行数据处理完成之后，做清理工作。
			 **/
			 @Override
			 public void processEnded(ParsingContext context) {
				 System.out.println("Finished processing rows of data.");
				 System.out.println(stringBuilder);
			 }
		 });
		 CsvParser parser = new CsvParser(settings);
		 List<String[]> allRows = parser.parseAll(new FileReader("/myFile.csv"));
		 return allRows;
	}
}

/**
 * 
 */
package excel.poi;

import com.kingdee.util.DateTimeUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author lifeTime 2010-4-20 下午01:09:25
 * 
 */
public class ExcelUtil {

	public static HSSFWorkbook load(String filePath) throws Exception {
		FileInputStream fileInputStream = new FileInputStream(filePath);
		POIFSFileSystem fs = new POIFSFileSystem(fileInputStream);
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		fileInputStream.close();
		return wb;
	}

	public static HSSFSheet[] getSheets(String filePath) throws Exception {
		HSSFWorkbook wb = load(filePath);
		int tabs = getSheetTabs(wb);
		if (tabs > 0) {
			HSSFSheet[] sheets = new HSSFSheet[tabs];
			for (int i = 0; i < tabs; i++) {
				sheets[i] = wb.getSheetAt(i);
			}
			return sheets;
		}
		return null;
	}

	public static String[] getSheetNames(String filePath) throws Exception {
		HSSFWorkbook wb = load(filePath);
		int tabs = getSheetTabs(wb);
		if (tabs > 0) {
			String[] sheets = new String[tabs];
			for (int i = 0; i < tabs; i++) {
				sheets[i] = wb.getSheetName(i);
			}
			return sheets;
		}
		return null;
	}

	public static int getSheetTabs(HSSFWorkbook wb) {
		int index = 0;
		while (true) {
			try {
				String name = wb.getSheetName(index);
				if (name != null) {
					index++;
				}
			} catch (RuntimeException e) {
				return index;
			}
		}
	}

	public static int getColumnCount(HSSFRow hssfRow) {
		short index = 0;
		while (true) {
			HSSFCell cell = hssfRow.getCell(index);
			if (cell != null && cell.getRichStringCellValue() != null && cell.getRichStringCellValue().getString() != null && cell.getRichStringCellValue().getString().trim().length() != 0) {
				index++;
				continue;
			} else {
				break;
			}
		}
		return index;
	}

	public static String getCellValue(HSSFCell cell) {
		if (cell == null)
			return "";
		switch (cell.getCellType()) {
		case 1: {
			return cell.getRichStringCellValue() != null ? cell.getRichStringCellValue().getString().trim() : "";
		}
		case 0: {
			double d = cell.getNumericCellValue();
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				Date cal = HSSFDateUtil.getJavaDate(d);
				return DateTimeUtils.format(cal, "yyyy-MM-dd");
			}
			BigDecimal bd = new BigDecimal((new Double(d)).toString());
			String s = bd.toString();
			if (s.endsWith(".0"))
				s = s.substring(0, s.length() - 2);
			return s;
		}
		case 2: {
			String valueStr = null;
			valueStr = cell.getRichStringCellValue().getString();
			if (valueStr != null && valueStr.trim().length() > 0)
				return valueStr.trim();
			double value = cell.getNumericCellValue();
			BigDecimal bd = new BigDecimal((new Double(value)).toString());
			valueStr = bd.toString();
			if (valueStr.endsWith(".0"))
				valueStr = valueStr.substring(0, valueStr.length() - 2);
			return valueStr;
		}
		case 4: {
			return String.valueOf(cell.getBooleanCellValue());
		}
		case 3: {
			return "";
		}
		}
		return cell.getRichStringCellValue() != null ? cell.getRichStringCellValue().getString().trim() : "";
	}

}

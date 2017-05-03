package com.by.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 以行为单位读取文件并比对，显示不同行
 */
public class FileCompareTest {
	
	public static void main(String[] args) throws IOException {
		String test1Dir = "C:\\Users\\baoyang\\Desktop\\test\\test1";
		String test2Dir = "C:\\Users\\baoyang\\Desktop\\test\\test2";
		File file = new File(test1Dir);
		File[] files = file.listFiles();
		List<String> diffList = new ArrayList<String>();
		for(File filetmp : files){
			try{
				compare(filetmp,new File(test2Dir,filetmp.getName()));
			}catch(IOException e){
				diffList.add(e.getMessage());
			}
		}
		for(int i=0;i<diffList.size();i++){
			System.out.println(diffList.get(i));
		}
		System.out.println("比较完成");
	}

	/**
	 * 简单比较两个文件
	 * @param filePath1
	 * @param filePath2
	 * @throws IOException
	 */
	public static void compare(File filetmp1, File filetmp2)
			throws IOException {
		List<String> list1 = readFileByLines(filetmp1);
		List<String> list2 = readFileByLines(filetmp2);
		int size1 = list1.size();
		int size2 = list2.size();
		if (size1 != size2) {
			throw new IOException(filetmp1.getName() + ":数据记录行数不一致"+size1+":"+size2);
		} else {
			for (int i = 0; i < size1; i++) {
				if (StringUtils.equalsIgnoreCase(list1.get(i), list2.get(i))) {
				} else {
					throw new IOException(filetmp1.getName() + ":数据记录第+"
							+ i + "+行数不一致");
				}
			}

		}
	}

	/**
	 * 以行为单位读取文件，常用于读面向行的格式化文件
	 * @throws FileNotFoundException
	 */
	public static List<String> readFileByLines(File filetmp)
			throws IOException {
		FileReader file = new FileReader(filetmp);
		BufferedReader reader = null;
		List<String> contentList = new ArrayList<String>();
		reader = new BufferedReader(file);
		String tempString;
		while ((tempString = reader.readLine()) != null) {
			contentList.add(tempString.trim());
		}
		IOUtils.closeQuietly(reader);
		IOUtils.closeQuietly(file);
		return contentList;
	}

	/**
	 * 詳細比較
	 * @param input
	 *            Map input Map where stored the entry and ID
	 * @param previous
	 *            Map previous Map
	 */
	public List<Entry> deltaEntryList(Map<Object, Object> input,
			Map<Object, Object> previous) {
		List<Entry> deltaList = new ArrayList<Entry>();
		Iterator iterInput = input.entrySet().iterator();
		while (iterInput.hasNext()) {
			Map.Entry entry = (Map.Entry) iterInput.next();
			Object keyInput = entry.getKey();
			Object valInput = entry.getValue();
			// For Add
			if (!previous.containsKey(keyInput)) {
				Entry deltaAdd = (Entry) valInput;
				deltaList.add(deltaAdd);
			} else {// For Modify
				Entry inputEntry = (Entry) valInput;
				Entry previousEntry = (Entry) previous.get(keyInput);
				// List inputEntryList = inputEntry.getValueList();
				// List previousEntryList = previousEntry.getValueList();
				boolean isMod = false;
				// for(int listIndex = 0; listIndex < inputEntryList.size(); ++
				// listIndex){
				// if( !previousEntryList.contains(
				// inputEntryList.get(listIndex) ) ){
				// isMod = true;
				// break;
				// }
			}
			// if(isMod){
			// inputEntry.setOperation(Entry.OP_MODIFY);
			// deltaList.add(inputEntry);
			// Util.log("debug", "Modify -> " + inputEntry.getID());
		}
		// }
		// }
		// For Delete from previous to input
		// Iterator iterPrevious = previous.entrySet().iterator();
		// while (iterPrevious.hasNext()) {
		// Map.Entry entry = (Map.Entry) iterPrevious.next();
		// Object keyPrevious = entry.getKey();
		// Object valPrevious = entry.getValue();
		// if( !input.containsKey(keyPrevious) ){
		// Entry deltaDel = (Entry)valPrevious;
		// // deltaDel.setOperation(Entry.OP_DELETE);
		// deltaList.add(deltaDel);
		// // Util.log("debug", "Delete -> " + deltaDel.getID());
		// }
		// }
		// / Util.log("debug", "Delta Entry size : " + deltaList.size());
		return deltaList;
	}

}

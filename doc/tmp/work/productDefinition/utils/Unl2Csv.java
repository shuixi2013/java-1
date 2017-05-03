package work.productDefinition.utils;

import io.csv.univocityParsers.CsvUtils;
import io.dJarchive.test.RarUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

public class Unl2Csv {
	
	private static final String[] suffix = {"unl"};
	private String unlRarDir;
	private String unlDir;
	private String csvDir;
	
	
	public Unl2Csv(String filePath) throws IOException {
		Properties conf = new Properties();
		conf.load(new FileInputStream(filePath));
		unlRarDir = conf.getProperty("unlRarDir");
		unlDir = conf.getProperty("unlDir");
		csvDir = conf.getProperty("csvDir", unlDir);
	}
	/**
	 * 初始化配置unl文件列表
	 * @throws IOException 目錄文件配置不正確
	 */
	private File[] initFiles(String[] str) throws IOException{
		File fileDir = new File(unlDir);
		Collection<File> coll = FileUtils.listFiles(fileDir, suffix, true);
		Collection<File> result = new ArrayList<File>();// 过滤文件
		for(File file :coll){
			String name = FilenameUtils.getBaseName(file.getName().toLowerCase());
			if(StringUtils.containsAny(name,str)){
				result.add(file);
			}
		}
		return FileUtils.convertFileCollectionToFileArray(result);
	}
	/**
	 * 从unl文件中抽取数据
	 * @throws IOException 
	 */
	private Map<File,List<String[]>> extractDataByFiles(File[] files) throws IOException{
		Map<File,List<String[]>> map= new HashMap<File,List<String[]>>();
		for(File file : files){
			List<String[]> data;
			try {
				data = CsvUtils.ReadUnl(file);
			} catch (IOException e) {
				throw new IOException("抽取数据失败"+file.getName(), e);
			}
			map.put(file, data);
		}
		return map;
	}
	
	/**
	 * 从rar压缩包中文件unl中抽取数据
	 * @throws IOException 
	 */
	private Map<File,List<String[]>> extractDataByRar(String unlDir,String[] str) throws IOException{
		Map<File,List<String[]>> map= new HashMap<File,List<String[]>>();
		RarUtils.genericOutput(unlDir,map,str);
		return map;
	}
	/**
	 * 组织输出数据
	 * @throws IOException 
	 */
	private boolean organize(Map<File,List<String[]>> map) throws IOException{
		Map<String,List<String[]>> p3sMap = new HashMap<String,List<String[]>>();
		p3sMap.put("cashval", new ArrayList<String[]>());
		p3sMap.put("prem_rate", new ArrayList<String[]>());
		p3sMap.put("reserve", new ArrayList<String[]>());
		p3sMap.put("surcharge_rate", new ArrayList<String[]>());
		for (Map.Entry<File,List<String[]>> entry : map.entrySet()) {
			File file = entry.getKey();
			String name = FilenameUtils.getBaseName(file.getName().toLowerCase());
			List<String[]> list = entry.getValue();
			String[] header;
			if(name.contains("cashval")){
				header = "POL_CODE,APPL_AGE,SEX,INSUR_YEAR,INSUR_DUR,MONEYIN_DUR,MONEYIN_ITRVL,ANN_DRAW_AGE,ANN_ITRVL,ANN_INCR_RATE,OCC_CLASS_NO,CASHVALUE".split(",");
				p3sMap.get("cashval").addAll(list);
			}else if(name.contains("prem_rate")){
				header = "BRANCH_CODE,POL_CODE,APPL_AGE,SEX,INSUR_DUR,MONEYIN_DUR,MONEYIN_ITRVL,ANN_DRAW_AGE,ANN_ITRVL,ANN_DRAW_D_YEARS,ANN_INCR_PCT,ZERO_COEF,OCC_CLASS_NO,FACE_AMNT,PREMIUM".split(",");
				int size = list.size();
				List<String[]> listP3s = new ArrayList<String[]>();
				for (int i = 0; i < size; i++) {
					String[] arr= list.get(i);
					int let = arr.length;
					String[] arrNew = new String[let+1];
					arrNew[0]="0";//N3S第一列为0
					System.arraycopy(arr, 0, arrNew, 1,let);  
					list.set(i, arrNew);
					//处理P3S数据
					String[] arrP3S = new String[let+3];
					arrP3S[0]="0";//P3S第一列为0
					System.arraycopy(arr, 0, arrP3S, 3,let);
					listP3s.add(arrP3S);
				}
				p3sMap.get("prem_rate").addAll(listP3s);
			}else if(name.contains("reserve")){
				header = "POL_CODE,APPL_AGE,SHIFT_AGE,SEX,INSUR_YEAR,INSUR_DUR,MONEYIN_DUR,MONEYIN_ITRVL,ANN_DRAW_AGE,ANN_ITRVL,ANN_INCR_PCT,OCC_CLASS_NO,RESERVE".split(",");
				p3sMap.get("reserve").addAll(list);
			}else if(name.contains("surcharge_rate")){
				header = "POL_CODE,INSUR_DUR,MONEYIN_DUR,MONEYIN_ITRVL,FROM_MONEYIN_YEAR,TO_MONEYIN_YEAR,FROM_APPL_AGE,TO_APPL_AGE,CHARGE_RATE,COMM_RATE".split(",");
				p3sMap.get("surcharge_rate").addAll(list);
			}else {
				System.out.println("此文件名称未配置，请查看"+name);
				continue;
			}
			try {//N3S文件输出
				CsvUtils.createCSVFile(header,list,csvDir+"/N3S/",name);
			} catch (IOException e) {
				throw new IOException("生成N3S"+name+".csv,出现问题,停止运行", e);
			}
		}
		//P3S文件输出
		for (Entry<String, List<String[]>> entry : p3sMap.entrySet()) {
			String name = entry.getKey();
			List<String[]> list = entry.getValue();
			if(list.size()==0){
				//不生成文件
			}else{
				try {
					CsvUtils.createCSVFile(null,list,csvDir+"/P3S/",name);
				} catch (IOException e) {
					throw new IOException("生成P3S"+name+".csv,出现问题,停止运行", e);
				}
			}
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		//过滤的文件名
		String[] str = {"cashval","prem_rate","reserve","surcharge_rate"};
		Unl2Csv uc = new Unl2Csv("conf.ini");
		if(uc.organize(uc.extractDataByRar(uc.unlDir,str))){
			System.out.println("数据完成");
		}
	}
}

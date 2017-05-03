package com.by.io.basic;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Title: 解压缩文件,网上说只支持utf-8
 */
public class ZipUtils {

	//压缩文件
	public static void ZipFiles( java.io.File zipfile,File... srcfile) {
		byte[] buf = new byte[1024];
		try {
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile));
//			out.setComment("hello");注释
			for (int i = 0; i < srcfile.length; i++) {
				FileInputStream in = new FileInputStream(srcfile[i]);
				out.putNextEntry(new ZipEntry(srcfile[i].getName()));
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				out.closeEntry();
				in.close();
			}
			out.close();
			System.out.println("压缩完成.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void ZipFiles(File file,File zipfile) throws IOException {
		if(!file.exists()){
			throw new RuntimeException("文件或目录不存在");
		}else if (file.isDirectory()) {
			File[] files = file.listFiles();
			ZipFiles(zipfile,files);
		}else{
			ZipFiles(zipfile,file);
		}
	}

	/**
	 * zip解压缩
	 * 
	 * @param zipfile
	 *            File 需要解压缩的文件
	 * @param descDir
	 *            String 解压后的目标目录
	 */
	public static void unZipFiles(java.io.File zipfile, String descDir) {
		try {
			ZipFile zf = new ZipFile(zipfile,Charset.forName("utf-8"));
//			zf.getName()
			for (Enumeration<ZipEntry> entries = (Enumeration<ZipEntry>) zf.entries(); entries.hasMoreElements();) {
				ZipEntry entry = ((ZipEntry) entries.nextElement());
				String zipEntryName = entry.getName();
				InputStream in = zf.getInputStream(entry);
				OutputStream out = new FileOutputStream(descDir + zipEntryName);
				byte[] buf1 = new byte[1024];
				int len;
				while ((len = in.read(buf1)) > 0) {
					out.write(buf1, 0, len);
				}
				in.close();
				out.close();
				// System.out.println("解压缩完成.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void readZipFile(String file) throws Exception {
		ZipFile zf = new ZipFile(file);
		InputStream in = new BufferedInputStream(new FileInputStream(file));
		ZipInputStream zin = new ZipInputStream(in);
		ZipEntry ze;
		while ((ze = zin.getNextEntry()) != null) {
			if (ze.isDirectory()) {
			} else {
				System.err.println("file - " + ze.getName() + " : " + ze.getSize() + " bytes");
				long size = ze.getSize();
				if (size > 0) {
					BufferedReader br = new BufferedReader(new InputStreamReader(zf.getInputStream(ze)));
					String line;
					while ((line = br.readLine()) != null) {
						System.out.println(line);
					}
					br.close();
				}
				System.out.println();
			}
		}
		zin.closeEntry();
	}

}

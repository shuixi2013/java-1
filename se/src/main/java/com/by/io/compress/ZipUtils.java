package com.by.io.compress;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

public class ZipUtils {

	private static int BUFFER_SIZE = 2048;

	public static void main(String[] args) throws Exception {

		ZipUtils.unZip("uploadTmp/parZip", "target");
		// ZipUtils.zip("target/parZip", "uploadTmp/parZip.zip");

	}

	public static void unZip(String zipFilePath, String saveFileDir) {
		valiZipFile(zipFilePath);
		ZipArchiveInputStream zais = null;
		try {
			zais = new ZipArchiveInputStream(new BufferedInputStream(new FileInputStream(zipFilePath), BUFFER_SIZE));
			ArchiveEntry archiveEntry = null;
			while ((archiveEntry = zais.getNextEntry()) != null) {
				if (!archiveEntry.isDirectory()) {
					File outFile = new File(saveFileDir, archiveEntry.getName()); // 把解压出来的文件写到指定路径
					OutputStream os = new BufferedOutputStream(FileUtils.openOutputStream(outFile), BUFFER_SIZE);
					IOUtils.copy(zais, os);
					IOUtils.closeQuietly(os);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(zais);
		}
	}

	public static void zip(String dir, String zipPath) {
		valiZipFile(zipPath);
		File file = new File(dir);
		String tmp = file.getPath();
		tmp = tmp.endsWith(File.separator) ? tmp : tmp + File.separator; // 矫正路径
		Collection<File> files = FileUtils.listFiles(file, null, true);
		compressFilesZip(files, zipPath, tmp);
	}

	private static void compressFilesZip(Collection<File> files, String zipFilePath, String dir) {
		ZipArchiveOutputStream zaos = null;
		try {
			File zipFile = new File(zipFilePath);
			zaos = (ZipArchiveOutputStream) new ArchiveStreamFactory()
					.createArchiveOutputStream(ArchiveStreamFactory.ZIP, new FileOutputStream(zipFile));
			// new ZipArchiveOutputStream(new FileOutputStream(zipFile));
			zaos.setEncoding("UTF-8");
			zaos.setUseZip64(Zip64Mode.AsNeeded);
			for (File file : files) {
				if (file != null) {
					String filePath = file.getPath();
					String p = filePath.replace(dir, "").replace("\\", "/"); // 修正路径
					String name = p;
					ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(file, name);
					zaos.putArchiveEntry(zipArchiveEntry);
					if (zipArchiveEntry.isDirectory()) {
						// zaos.closeArchiveEntry();
						continue;
					}
					InputStream is = null;
					try {
						is = new BufferedInputStream(new FileInputStream(file), BUFFER_SIZE);
						byte[] buffer = new byte[1024];
						int len = -1;
						while ((len = is.read(buffer)) != -1) {
							zaos.write(buffer, 0, len); // 把缓冲区的字节写入到ZipArchiveEntry
						}
						zaos.closeArchiveEntry();
					} catch (Exception e) {
						throw new RuntimeException(e);
					} finally {
						if (is != null)
							is.close();
					}
				}
			}
			zaos.finish();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (zaos != null) {
					zaos.close();
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

	}

	private static void valiZipFile(String filePath) {
		if (!"zip".equalsIgnoreCase(FilenameUtils.getExtension(filePath))) {
			throw new RuntimeException("文件后缀不为zip");
		}
	}
}

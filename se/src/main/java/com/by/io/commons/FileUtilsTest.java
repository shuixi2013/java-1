package com.by.io.commons;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.io.filefilter.SuffixFileFilter;

public class FileUtilsTest {
	public static final String TEST_DIRECTORY_PATH_1 = "D:/testDirectory1";
	public static final String TEST_DIRECTORY_PATH_2 = "D:/testDirectory2";
	public static final File DIRECTORY_1 = new File(TEST_DIRECTORY_PATH_1);
	public static final File DIRECTORY_2 = new File(TEST_DIRECTORY_PATH_2);
	public static final String TEST_FILE_PATH_1 = "D:/testDirectory1/test.txt";
	public static final String TEST_FILE_PATH_2 = "D:/testDirectory2/test.txt";
	public static final File FILE_1 = new File(TEST_FILE_PATH_1);
	public static final File FILE_2 = new File(TEST_FILE_PATH_2);
	private static final String MAIN_PATH = "C:\\Users\\Lilykos\\Desktop\\FUExFiles\\";

	public static void main(String[] args) throws IOException {
		// 创建指定目录，如果失败抛出异常；sizeOf(File file)
		// ：返回指定文件大小或者目录下所有文件大小之和；
		// isFileNewer(File file, File reference)：
		// 判断给定文件与比较文件哪个更新（创建时间更晚），第二个参数为参照文件；
		// isFileOlder(File file, File reference)：
		// 判断给定文件与比较文件哪个更旧（创建时间更早），第二个参数为参照文件；
		// 转换文件长度单位file.length().isFile().isDirectory()
		String fileSize = FileUtils.byteCountToDisplaySize(1024 * 1024);
		
		FileUtils.sizeOfDirectory(DIRECTORY_1);// 返回目录的大小
		
		FileUtils.cleanDirectory(DIRECTORY_1);// 清空某目录下的所有目录,含文件夹和文件

		FileUtils.convertFileCollectionToFileArray(null);// 把文件集合转换为数组形式

		FileUtils.copyDirectory(DIRECTORY_1, DIRECTORY_2);// 将参数1目录下的全部内容复制到参数2目录

		File file1 = FileUtils.getFile(MAIN_PATH + "cmpFile1.txt");
		FileUtils.copyDirectory(FileUtils.getFile(MAIN_PATH), // source
				FileUtils.getFile(MAIN_PATH + "copiedPath\\")); // destination
		
		FileUtils.copyDirectoryToDirectory(DIRECTORY_1, DIRECTORY_2);// 将参数1目录整个复制到参数2目录下
		
		FileUtils.copyFile(FILE_1, FILE_2);// copy參數1文件到參數2
		
		FileUtils.copyFileToDirectory(FILE_1, DIRECTORY_1);// copy參數1文件到參數2目錄下
		
		FileUtils.copyURLToFile(new URL("http://www.xxx.com/a.html"), FILE_1);// copy URL中文件到參數2

		FileUtils.copyDirectory(new File("d:/aaa"), new File("d:/bbb"),
				new FileFilter() {
					@Override
					public boolean accept(File pathname) {

						// 拷贝目录或html结尾的文件
						if (pathname.isDirectory()
								|| pathname.getName().endsWith("html")) {
							return true;
						} else {
							return false;
						}
					}
				});

		// Copy a directory by filtering out all the txt files.
		FileUtils.copyDirectory(FileUtils.getFile(FILE_1),
				FileUtils.getFile(FILE_1 + "copiedFilterPath\\"),
				new SuffixFileFilter(".zip"));

		// 强制删除文件
		FileUtils.forceDelete(FILE_1);
		// forceDelete(File file)：删除指定文件，如果为目录，则清空目录并删除目录文件，如果为文件，直接删除文件；
		// forceDeleteOnExit(File file)：在JVM退出时进行删除，如果为目录，则清空目录并删除目录文件，如果为文件，直接删除文件；
		// cleanDirectoryOnExit(File directory)：在JVM退出时清空目录；forceMkdir(File directory)：

		// 将文件转为InputStrem,对应有openOutStream方法
		FileUtils.openInputStream(FILE_1);
		FileUtils.openOutputStream(FILE_1);
		
		byte[] bytes = FileUtils.readFileToByteArray(FILE_1);// 读取文件转为字节数组
		// ：读取指定文件内容到一个字符串；第二个参数为指定的字符集编码；
		// readFileToString(File file, String encoding)，
		// readFileToString(File file)
		// readFileToByteArray(File file) ：读取指定文件到字节数组；
		// readLines(File file, String encoding) ：读取指定文件按行存入字符串List，第二个参数为指定的字符集编码；

		// 读取文件转换为String类型,方便文本读取
		String content = FileUtils.readFileToString(FILE_1, "UTF-8");
		System.out.println(content);

		File file = new File((FileUtilsTest.class.getClass().getResource("/demo/web.xml")).getFile());
		List<?> lines = FileUtils.readLines(file, "UTF-8");
		for (int i = 0; i < lines.size(); i++) {
			System.out.println(lines.get(i));
		}
		
		// 通过FileUtils.getFile(String)创建File对象，然后根据FileUtils.lineIterator(File)
		File exampleFile = FileUtils.getFile(TEST_FILE_PATH_1);
		LineIterator iter = FileUtils.lineIterator(exampleFile);
		System.out.println("Contents of exampleTxt...");
		while (iter.hasNext()) {
			System.out.println("/t" + iter.next());
		}
		iter.close();

		// 写字符串到参数1文件中 ---ISO-8859-1
		FileUtils.writeStringToFile(FILE_1, "test", "UTF-8");

		// 目录是否已经包含文件
		File parent = FileUtils.getFile(TEST_FILE_PATH_2);
		FileUtils.directoryContains(parent, exampleFile);

		// 判断文件是否相等
		System.out.println("Are cmpFile1 and cmpFile2 equal: "
				+ FileUtils.contentEquals(FILE_1, FILE_2));

		System.out.println("Does the copiedPath exist: "
				+ FileUtils.getFile(MAIN_PATH + "copiedPath\\").exists());

		for (File f : FileUtils.getFile(MAIN_PATH + "copiedFilterPath\\")
				.listFiles()) {
			System.out.println("Contents of copiedFilterPath: " + f.getName());
		}

		// Copy a file
		File copy = FileUtils.getFile(MAIN_PATH + "copyOfFile1.txt");
		FileUtils.copyFile(file1, copy);
		System.out.println("Are cmpFile1 and copyOfFile1 equal: "
				+ FileUtils.contentEquals(file1, copy));

		// Right now there are these files in the MAIN_PATH.
		for (File f : FileUtils.getFile(MAIN_PATH).listFiles()) {
			System.out.println("Contents of MAIN_PATH: " + f.getName());
		}
		// Let's delete a directory and see the results.
		FileUtils.deleteDirectory(FileUtils.getFile(MAIN_PATH
				+ "copiedFilterPath\\"));
		for (File f : FileUtils.getFile(MAIN_PATH).listFiles()) {
			System.out.println("Contents of MAIN_PATH after deletion: "
					+ f.getName());
		}

		// Directories
		System.out.println("Temp Dir: "
				+ FileUtils.getTempDirectory().getAbsolutePath());
		System.out.println("User Dir: "
				+ FileUtils.getUserDirectory().getAbsolutePath());
		// writeStringToFile(File file, String data, String encoding)
		// ，writeStringToFile(File file, String data, String encoding, boolean
		// append):
		// 按指定的编码把字符串写入指定文件中；第四个参数如果为true,则把内容写到文件最后；如果文件不存在则创建；
		// writeByteArrayToFile(File file, byte[] data)，
		// writeByteArrayToFile(File file, byte[] data, boolean append)


		// Ways of writing lines to a file.
		File fileToWrite1 = FileUtils.getFile(MAIN_PATH + "fileToWrite1.txt");
		File fileToWrite2 = FileUtils.getFile(MAIN_PATH + "fileToWrite2.txt");

		FileUtils.write(fileToWrite1, "Written with FileUtils!");
		FileUtils.writeLines(fileToWrite2, lines);
		
		//移动文件
//		moveDirectory(File srcDir, File destDir)
		// ：将源目录移动为指定目录；重命名那句代码，如果生命名成功就无须再移动文件了，如果生命名失败再进行拷贝和删除操作；
		// boolean rename = srcDir.renameTo(destDir);
		// moveDirectoryToDirectory(File src, File destDir, boolean createDestDir)：
		// 把源目录移动到指定目录下，如果目标目录不存在，根据第三个参数是否创建；如果不存在并且不创建，则会抛出异常；
		// moveFile(File srcFile, File destFile)：移动文件，同上；
		// moveFileToDirectory(File srcFile, File destDir, boolean createDestDir)：
		// 移动文件到指定目录；如果目标目录不存在，根据第三个参数是否创建；如果不存在并且不创
		
//		FileUtils.moveToDirectory(srcFile, destDir, true);
		//过滤文件夹下文件  文件和目录  不包还子目录
//		FileUtils.listFiles(directory, fileFilter, dirFilter);
		//过滤文件夹下文件  文件和目录  包含子目录
//		FileUtils.listFilesAndDirs(directory, fileFilter, dirFilter)
		//过滤文件夹下文件  相关文件
//		FileUtils.listFiles(directory, extensions, recursive);
		
	}
	
	/**
	 * 给指定文件追加内容
	 * @param filePath
	 * @param contents
	 */
	public static void addContent(String filePath, List<String> contents)
			throws IOException {
		try {
			FileUtils.writeLines(new File(filePath), contents);
		} catch (IOException e) {
			throw e;
		}
	}
	/**
	 * 复制文件或者目录,复制前后文件完全一样。
	 * @param resFilePath
	 *            源文件路径
	 * @param distFolder
	 *            目标文件夹
	 * @IOException 当操作发生异常时抛出
	 */
	public static void copyFile(String resFilePath, String distFolder)
			throws IOException {
		File resFile = new File(resFilePath);
		File distFile = new File(distFolder);
		if (resFile.isDirectory()) { // 目录时
			FileUtils.copyDirectoryToDirectory(resFile, distFile);
		} else if (resFile.isFile()) { // 文件时
			// FileUtils.copyFileToDirectory(resFile, distFile, true);
			FileUtils.copyFileToDirectory(resFile, distFile);
		}
	}

	/**
	 * 删除一个文件或者目录
	 * @param targetPath
	 *            文件或者目录路径
	 * @IOException 当操作发生异常时抛出
	 */
	public static void deleteFile(String targetPath) throws IOException {
		File targetFile = new File(targetPath);
		if (targetFile.isDirectory()) {
			FileUtils.deleteDirectory(targetFile);
		} else if (targetFile.isFile()) {
			targetFile.delete();
		}
	}
	/*
	 * 文件复制nio
	 */
	public static void Copy2(String[] args) throws IOException {
		long start = System.currentTimeMillis();
		RandomAccessFile rafi = new RandomAccessFile(args[0], "r");
		RandomAccessFile rafo = new RandomAccessFile(args[1], "rw");
		FileChannel fci = rafi.getChannel();
		FileChannel fco = rafo.getChannel();
		long size = fci.size();
		MappedByteBuffer mbbi = fci.map(FileChannel.MapMode.READ_ONLY, 0, size);
		MappedByteBuffer mbbo = fco
				.map(FileChannel.MapMode.READ_WRITE, 0, size);
		for (int i = 0; i < size; i++) {
			byte b = mbbi.get(i);
			mbbo.put(i, b);
		}
		fci.close();
		fco.close();
		rafi.close();
		rafo.close();
		System.out.println("Spend: "
				+ (double) (System.currentTimeMillis() - start) / 1000 + "s");
	}
	
	/**
	 * 文件目标位置插入内容
	 * 
	 * @param fileName
	 * @param pos
	 * @param insertContent
	 * @throws IOException
	 */
	public static void insert(String fileName, long pos, String insertContent)
			throws IOException {
		File tmp = File.createTempFile("tmp", null);
		tmp.deleteOnExit();
		try (RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
		// 创建一个临时文件来保存插入点后的数据
				FileOutputStream tmpOut = new FileOutputStream(tmp);
				FileInputStream tmpIn = new FileInputStream(tmp)) {
			raf.seek(pos);
			// ------下面代码将插入点后的内容读入临时文件中保存------
			byte[] bbuf = new byte[64];
			// 用于保存实际读取的字节数
			int hasRead = 0;
			// 使用循环方式读取插入点后的数据
			while ((hasRead = raf.read(bbuf)) > 0) {
				// 将读取的数据写入临时文件
				tmpOut.write(bbuf, 0, hasRead);
			}
			// ----------下面代码插入内容----------
			// 把文件记录指针重新定位到pos位置
			raf.seek(pos);
			// 追加需要插入的内容
			raf.write(insertContent.getBytes());
			// 追加临时文件中的内容
			while ((hasRead = tmpIn.read(bbuf)) > 0) {
				raf.write(bbuf, 0, hasRead);
			}
		}
	}
	
	// 递归查找文件名包含关键字的文件
	public static File[] searchFile(File folder, final String keyWord) {
		File[] subFolders = folder.listFiles(new FileFilter() {// 运用内部匿名类获得文件
					@Override
					public boolean accept(File pathname) {// 实现FileFilter类的accept方法
						if (pathname.isDirectory()
								|| (pathname.isFile() && pathname.getName()
										.toLowerCase()
										.contains(keyWord.toLowerCase())))// 目录或文件包含关键字
							return true;
						return false;
					}
				});
		List<File> result = new ArrayList<File>();// 声明一个集合
		for (int i = 0; i < subFolders.length; i++) {// 循环显示文件夹或文件
			if (subFolders[i].isFile()) {// 如果是文件则将文件添加到结果列表中
				result.add(subFolders[i]);
			} else {// 如果是文件夹，则递归调用本方法，然后把所有的文件加到结果列表中
				File[] foldResult = searchFile(subFolders[i], keyWord);
				for (int j = 0; j < foldResult.length; j++) {// 循环显示文件
					result.add(foldResult[j]);// 文件保存到集合中
				}
			}
		}
		File files[] = new File[result.size()];// 声明文件数组，长度为集合的长度
		result.toArray(files);// 集合数组化
		return files;
	}
	/**
	 * 寻找指定目录下，具有指定后缀名的所有文件。
	 * 
	 * @param filenameSuffix
	 *            : 文件后缀名
	 * @param currentDirUsed
	 *            : 当前使用的文件目录
	 * @param currentFilenameList
	 *            ：当前文件名称的列表
	 * @throws IOException
	 */
	public File[] listFiles(String filenameSuffix, String currentDirUsed)
				throws IOException {
			File dir = new File(currentDirUsed);
			if (!dir.exists() || !dir.isDirectory()) {
				throw new IOException("列出目錄有問題!");
			}
			List<File> result = new ArrayList<File>();// 文件集合
			for (File file : dir.listFiles()) {
				if (file.isDirectory()) {
					File[] fileTmp = listFiles(filenameSuffix,
							file.getAbsolutePath());
					for (int j = 0; j < fileTmp.length; j++) {// 循环显示文件
						result.add(fileTmp[j]);// 文件保存到集合中
					}
				} else {
					if (file.getAbsolutePath().endsWith("." + filenameSuffix)) {
						result.add(file);
					}
				}
			}
			File files[] = new File[result.size()];// 声明文件数组，长度为集合的长度
			result.toArray(files);// 集合数组化
			return files;
		}


}





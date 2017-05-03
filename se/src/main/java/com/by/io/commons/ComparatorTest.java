package com.by.io.commons;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.comparator.CompositeFileComparator;
import org.apache.commons.io.comparator.DirectoryFileComparator;
import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.comparator.NameFileComparator;
import org.apache.commons.io.comparator.PathFileComparator;
import org.apache.commons.io.comparator.SizeFileComparator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ComparatorTest {

	private String basePath = null;

	@Before
	public void setUp() throws Exception {
		basePath = System.getProperty("user.dir") + "\\file\\";
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * 文件名称比较器
	 * 
	 * @throws IOException
	 */
	@Test
	public void testNameFileComparator() throws IOException {
		File f1 = new File(basePath + "a.txt");
		File f2 = new File(basePath + "c.txt");
		int result = NameFileComparator.NAME_COMPARATOR.compare(f1, f2);
		System.out.println(result);
	}

	/**
	 * 文件路径比较器
	 * 
	 * @throws IOException
	 */
	@Test
	public void testPathFileComparator() throws IOException {
		File f1 = new File(basePath + "a.txt");
		File f2 = new File(basePath + "c.txt");
		int result = PathFileComparator.PATH_COMPARATOR.compare(f1, f2);
		System.out.println(result);
	}

	/**
	 * 组合比较器
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testCompositeFileComparator() throws IOException {
		File dir = new File(basePath);
		File[] files = dir.listFiles();
		for (File file : files) {
			System.out.println(file.getName());
		}
		CompositeFileComparator cfc = new CompositeFileComparator(
				DirectoryFileComparator.DIRECTORY_COMPARATOR,
				NameFileComparator.NAME_COMPARATOR);
		cfc.sort(files);
		System.out.println("*****after sort*****");
		for (File file : files) {
			System.out.println(file.getName());
		}
	}

	public void test() {
		final String PARENT_DIR = "C:\\Users\\Lilykos\\workspace\\ApacheCommonsExample\\ExampleFolder";
		final String FILE_1 = "C:\\Users\\Lilykos\\workspace\\ApacheCommonsExample\\ExampleFolder\\example";
		final String FILE_2 = "C:\\Users\\Lilykos\\workspace\\ApacheCommonsExample\\ExampleFolder\\exampleTxt.txt";

		System.out.println("Comparator example...");
		// NameFileComparator
		// Let's get a directory as a File object
		// and sort all its files.
		File parentDir = FileUtils.getFile(PARENT_DIR);
		NameFileComparator comparator = new NameFileComparator(IOCase.SENSITIVE);
		File[] sortedFiles = comparator.sort(parentDir.listFiles());

		System.out.println("Sorted by name files in parent directory: ");
		for (File file : sortedFiles) {
			System.out.println("\t" + file.getAbsolutePath());
		}

		// SizeFileComparator

		// We can compare files based on their size.
		// The boolean in the constructor is about the directories.
		// true: directory's contents count to the size.
		// false: directory is considered zero size.
		SizeFileComparator sizeComparator = new SizeFileComparator(true);
		File[] sizeFiles = sizeComparator.sort(parentDir.listFiles());

		System.out.println("Sorted by size files in parent directory: ");
		for (File file : sizeFiles) {
			System.out.println("\t" + file.getName() + " with size (kb): "
					+ file.length());
		}

		// LastModifiedFileComparator

		// We can use this class to find which file was more recently modified.
		LastModifiedFileComparator lastModified = new LastModifiedFileComparator();
		File[] lastModifiedFiles = lastModified.sort(parentDir.listFiles());

		System.out
				.println("Sorted by last modified files in parent directory: ");
		for (File file : lastModifiedFiles) {
			Date modified = new Date(file.lastModified());
			System.out.println("\t" + file.getName() + " last modified on: "
					+ modified);
		}

		// Or, we can also compare 2 specific files and find which one was last
		// modified.
		// returns > 0 if the first file was last modified.
		// returns < 0 if the second file was last modified.
		File file1 = FileUtils.getFile(FILE_1);
		File file2 = FileUtils.getFile(FILE_2);
		if (lastModified.compare(file1, file2) > 0)
			System.out.println("File " + file1.getName()
					+ " was modified last because...");
		else
			System.out.println("File " + file2.getName()
					+ "was modified last because...");

		System.out.println("\t" + file1.getName() + " last modified on: "
				+ new Date(file1.lastModified()));
		System.out.println("\t" + file2.getName() + " last modified on: "
				+ new Date(file2.lastModified()));
	}

	// Comparator example...
	// Sorted by name files in parent directory:
	// C:UsersLilykosworkspaceApacheCommonsExampleExampleFoldercomparator1.txt
	// C:UsersLilykosworkspaceApacheCommonsExampleExampleFoldercomperator2.txt
	// C:UsersLilykosworkspaceApacheCommonsExampleExampleFolderexample
	// C:UsersLilykosworkspaceApacheCommonsExampleExampleFolderexampleFileEntry.txt
	// C:UsersLilykosworkspaceApacheCommonsExampleExampleFolderexampleTxt.txt
	// Sorted by size files in parent directory:
	// example with size (kb): 0
	// exampleTxt.txt with size (kb): 87
	// exampleFileEntry.txt with size (kb): 503
	// comperator2.txt with size (kb): 1458
	// comparator1.txt with size (kb): 4436
	// Sorted by last modified files in parent directory:
	// exampleTxt.txt last modified on: Sun Oct 26 14:02:22 EET 2014
	// example last modified on: Sun Oct 26 23:42:55 EET 2014
	// comparator1.txt last modified on: Tue Oct 28 14:48:28 EET 2014
	// comperator2.txt last modified on: Tue Oct 28 14:48:52 EET 2014
	// exampleFileEntry.txt last modified on: Tue Oct 28 14:53:50 EET 2014
	// File example was modified last because...
	// example last modified on: Sun Oct 26 23:42:55 EET 2014
	// exampleTxt.txt last modified on: Sun Oct 26 14:02:22 EET 2014

	// 让我们来看看这里用到了哪些类：
	//
	// NameFileComparator：通过文件名来比较文件。
	//
	// SizeFileComparator：通过文件大小来比较文件。
	//
	// LastModifiedFileComparator：通过文件的最新修改时间来比较文件。
	//
	// 在这里你需要注意，比较可以在定的文件夹中（文件夹下的文件已经被sort()方法排序过了），也可以在两个指定的文件之间（通过使用compare()方法）。
}
package by.io.commons;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 文件变化监听器
 * 
 * 在Apache的Commons-IO中有关于文件的监控功能的代码. 文件监控的原理如下：
 * 由文件监控类FileAlterationMonitor中的线程不停的扫描文件观察器FileAlterationObserver，
 * 如果有文件的变化，则根据相关的文件比较器，判断文件时新增，还是删除，还是更改。（默认为1000毫秒执行一次扫描）
 * 
 * @author wy
 * 
 */
public class FileListenerTest extends FileAlterationListenerAdaptor {
	private static final Logger log = LogManager.getLogger(FileListenerTest.class);

	public static void main(String[] args) throws Exception {
		// 监控目录
		String rootDir = "doc/";
		// 轮询间隔 5 秒
		long interval = TimeUnit.SECONDS.toMillis(5);
		// 创建一个文件观察器用于处理文件的格式
		FileAlterationObserver _observer = new FileAlterationObserver(rootDir,
				FileFilterUtils.and(FileFilterUtils.fileFileFilter(),
						FileFilterUtils.suffixFileFilter(".txt")), // 过滤文件格式
				null);
		FileAlterationObserver observer = new FileAlterationObserver(rootDir);
		observer.addListener(new FileListenerTest()); // 设置文件变化监听器
		// 创建文件变化监听器
		FileAlterationMonitor monitor = new FileAlterationMonitor(interval,
				observer);
		// 开始监控
		monitor.start();
	}

	/**
	 * 文件创建执行
	 */
	@Override
	public void onFileCreate(File file) {
		log.info("[新建]:" + file.getAbsolutePath());
	}

	/**
	 * 文件创建修改
	 */
	@Override
	public void onFileChange(File file) {
		log.info("[修改]:" + file.getAbsolutePath());
	}

	/**
	 * 文件删除
	 */
	@Override
	public void onFileDelete(File file) {
		log.info("[删除]:" + file.getAbsolutePath());
	}

	/**
	 * 目录创建
	 */
	@Override
	public void onDirectoryCreate(File directory) {
		log.info("[新建]:" + directory.getAbsolutePath());
	}

	/**
	 * 目录修改
	 */
	@Override
	public void onDirectoryChange(File directory) {
		log.info("[修改]:" + directory.getAbsolutePath());
	}

	/**
	 * 目录删除
	 */
	@Override
	public void onDirectoryDelete(File directory) {
		log.info("[删除]:" + directory.getAbsolutePath());
	}

	@Override
	public void onStart(FileAlterationObserver observer) {
		super.onStart(observer);
	}

	@Override
	public void onStop(FileAlterationObserver observer) {
		super.onStop(observer);
	}

}

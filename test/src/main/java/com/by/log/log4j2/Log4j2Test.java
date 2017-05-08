package by.log.log4j2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Hello world!
 *
 */
public class Log4j2Test {

	private static Logger logger = LogManager.getLogger(Log4j2Test.class);

	public static void main(String[] args) {
		test2();
	}

	// 测试try..catch的exception结果会不会自动打印在日志中
	public static void test2() {
		try {
			System.out.println(3 / 0);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e);
		}
	}

	public static void tests() {
		for (int i = 0; i < 5; i++) {
			logger.trace("1测试级别trace");
			logger.warn("1测试级别：warn");
			logger.debug("1方法测试");
			logger.error("1方法发生错误，test");
		}
	}

}

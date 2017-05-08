package by.lang;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.StopWatch;

public class DateFormatUtilsTest {
	public static void main(String[] args) {
		System.out.println("**DateFormatUtilsDemo**");
		System.out.println("格式化日期输出.");
		System.out.println(DateFormatUtils.format(System.currentTimeMillis(),
				"yyyy-MM-dd HH:mm:ss"));

		System.out.println("毫秒.");
		StopWatch sw = new StopWatch();
		sw.start();
		try {
			Thread.sleep(100);
		} catch (Exception e) {
		}
		sw.stop();
		System.out.println("毫秒计时:" + sw.getTime());
	}
}

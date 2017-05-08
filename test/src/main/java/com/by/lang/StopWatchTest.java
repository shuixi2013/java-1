package by.lang;

import org.apache.commons.lang3.time.StopWatch;

public class StopWatchTest {
	public static void main(String[] args) {
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

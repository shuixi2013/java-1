package by.io.basic;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * 验证管道流
*/
public class PipedOutputStreamTest {
	public static void main(String[] args) throws IOException {
		Send send = new Send();
		Recive recive = new Recive();
		try {
			// 管道连接
			send.getOut().connect(recive.getInput());
		} catch (Exception e) {
			e.printStackTrace();
		}
		new Thread(send).start();
		new Thread(recive).start();
	}
}

/**
 * 消息发送类
 * */
class Send implements Runnable {
	private PipedOutputStream out = null;

	public Send() {
		out = new PipedOutputStream();
	}

	public PipedOutputStream getOut() {
		return this.out;
	}

	public void run() {
		String message = "hello , Rollen";
		try {
			out.write(message.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

/**
 * 接受消息类
 * */
class Recive implements Runnable {
	private PipedInputStream input = null;

	public Recive() {
		this.input = new PipedInputStream();
	}

	public PipedInputStream getInput() {
		return this.input;
	}

	public void run() {
		byte[] b = new byte[1000];
		int len = 0;
		try {
			len = this.input.read(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("接受的内容为 " + (new String(b, 0, len)));
	}
}

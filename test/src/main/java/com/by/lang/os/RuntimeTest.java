package by.lang.os;

public class RuntimeTest {
	public static void main(String[] args) {
		Runtime rt = Runtime.getRuntime();
		System.out.println(rt.availableProcessors());
		System.out.println(rt.freeMemory());
		System.out.println(rt.totalMemory());
		System.out.println(rt.maxMemory());
	}
}

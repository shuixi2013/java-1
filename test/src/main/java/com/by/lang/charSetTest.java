package by.lang;

import org.apache.commons.lang3.CharSet;
import org.apache.commons.lang3.CharSetUtils;

public class charSetTest {
	public static void main(String[] args) {
		System.out.println("**CharSetDemo**");
		CharSet charSet = CharSet.getInstance("aeiou");
		System.out.println(charSet.toString());
		String demoStr = "The quick brown fox jumps over the lazy dog.";
		int count = 0;
		for (int i = 0, len = demoStr.length(); i < len; i++) {
			if (charSet.contains(demoStr.charAt(i))) {
				count++;
			}
		}
		System.out.println("count: " + count);
		
		System.out.println("**CharSetUtilsDemo**");
		System.out.println("计算字符串中包含某字符数.");
		System.out.println(CharSetUtils.count(
				"The quick brown fox jumps over the lazy dog.", "aeiou"));

		System.out.println("删除字符串中某字符.");
		System.out.println(CharSetUtils.delete(
				"The quick brown fox jumps over the lazy dog.", "aeiou"));

		System.out.println("保留字符串中某字符.");
		System.out.println(CharSetUtils.keep(
				"The quick brown fox jumps over the lazy dog.", "aeiou"));

		System.out.println("合并重复的字符.");
		System.out.println(CharSetUtils.squeeze("a  bbbbbb     c dd", " d"));
	}
}

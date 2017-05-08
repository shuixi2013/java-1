package by.lang;

import org.apache.commons.lang3.StringEscapeUtils;

public class StringEscapeUtilsTest {
	public static void main(String[] args) {
		String sql = new String(
				"select key_sn,remark,create_date from tb_selogon_key where 1=1 ");
		// escapeHtml /unescapeHtml 转义/反转义html脚本
		System.out.println(StringEscapeUtils.escapeHtml3("<A>dddd</A>"));
		System.out.println(StringEscapeUtils.unescapeHtml4("<a>dddd</a>"));
		// 4.escapeJava/unescapeJava 把字符串转为unicode编码
		System.out.println(StringEscapeUtils.escapeJava("中国"));
		// 2输出为：用escapeJava方法转义之后的字符串为:/u4E2D/u56FD/u5171/u4EA7/u515A
		String str = "APEC召开时不让点柴火做饭";
		System.out.println("用escapeJava方法转义之后的字符串为:"
				+ StringEscapeUtils.escapeJava(str));
		System.out.println("用unescapeJava方法反转义之后的字符串为:"
				+ StringEscapeUtils.unescapeJava(StringEscapeUtils
						.escapeJava(str)));
		System.out.println("用escapeHtml方法转义之后的字符串为:"
				+ StringEscapeUtils.escapeHtml3(str));
		System.out.println("用unescapeHtml方法反转义之后的字符串为:"
				+ StringEscapeUtils.unescapeHtml4(StringEscapeUtils
						.escapeHtml3(str)));
		System.out.println("用escapeXml方法转义之后的字符串为:"
				+ StringEscapeUtils.escapeXml(str));
		System.out.println("用unescapeXml方法反转义之后的字符串为:"
				+ StringEscapeUtils.unescapeXml(StringEscapeUtils
						.escapeXml(str)));

		String a = "<html>吃饭</html>";
		System.out.println(StringEscapeUtils.escapeHtml3(a));
		System.out.println(StringEscapeUtils.unescapeHtml4(StringEscapeUtils
				.escapeHtml3(a)));
	}
}
package by.i18n;

import java.util.*;
import java.text.*;

/**
 * Description: <br/>
 * 网站: <a href="http://www.crazyit.org">疯狂Java联盟</a> <br/>
 * Copyright (C), 2001-2012, Yeeku.H.Lee <br/>
 * This program is protected by copyright laws. <br/>
 * Program Name: <br/>
 * Date:
 * 
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class ResourceBundleTest {
	public static void main(String[] args) {
		Locale currentLocale = null; // 定义一个Locale变量
		if (args.length == 2) { // 如果运行程序的指定了两个参数
			currentLocale = new Locale(args[0], args[1]); // 使用运行程序的两个参数构造Locale实例
		} else {
			currentLocale = Locale.getDefault(); // 否则直接使用系统默认的Locale
		}
		// 根据Locale加载语言资源
		ResourceBundle bundle = ResourceBundle.getBundle("i18n.myMess", currentLocale);
		String msg = bundle.getString("msg");
		System.out.println(bundle.getBaseBundleName());
		// 取得已加载的语言资源文件中msg对应消息
		// 使用MessageFormat为带占位符的字符串传入参数
		System.out.println(MessageFormat.format(msg, "yeeku", new Date()));
	}
}

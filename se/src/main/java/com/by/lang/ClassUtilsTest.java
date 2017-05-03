package com.by.lang;

import java.util.Date;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.reflect.ConstructorUtils;

public class ClassUtilsTest {
	public static void main(String[] args) {
		System.out.println("获取类实现的所有接口.");
		System.out.println(ClassUtils.getAllInterfaces(Date.class));
		System.out.println("获取类所有父类.");
		System.out.println(ClassUtils.getAllSuperclasses(Date.class));
		System.out.println("获取简单类名.");
		System.out.println(ClassUtils.getShortClassName(Date.class));
		System.out.println("获取包名.");
		System.out.println(ClassUtils.getPackageName(Date.class));
		System.out.println("判断是否可以转型.");
		System.out.println(ClassUtils.isAssignable(Date.class, Object.class));
		System.out.println(ClassUtils.isAssignable(Object.class, Date.class));
		// 获取ClassUtilsTest类所有实现的接口

		ClassUtils.getAllInterfaces(ClassUtilsTest.class);

		ClassUtils.getAllSuperclasses(ClassUtilsTest.class);

		// 获取ClassUtilsTest类所在的包名

		ClassUtils.getPackageName(ClassUtilsTest.class);

		ClassUtils.getShortClassName(ClassUtilsTest.class);

		ClassUtils.isAssignable(ClassUtilsTest.class, Object.class);

		ClassUtils.isInnerClass(ClassUtilsTest.class);

		// 获取参数为String的构造函数

		ConstructorUtils.getAccessibleConstructor(ClassUtilsTest.class, String.class);
		// 执行参数为String的构造函数
//		ClassUtilsTest classUtilsTest1 = (ClassUtilsTest) ConstructorUtils.invokeConstructor(ClassUtilsTest.class);
		ClassUtilsTest classUtilsTest = new ClassUtilsTest();
//		MethodUtils.invokeMethod(classUtilsTest, "publicHello", null);
//		MethodUtils.invokeMethod(classUtilsTest, "publicHello", "Hello");
//		MethodUtils.invokeMethod(classUtilsTest, "publicHello", new Object[] { "100"});
//		MethodUtils.invokeStaticMethod(ClassUtilsTest.class, "staticHello", null);
		// 以下两个方法完全一样,都能获取共有或私有变量,因为第三个参数都设置了不检查
//		FieldUtils.getDeclaredField(ClassUtilsTest.class, "username", true);
//		FieldUtils.getField(ClassUtilsTest.class, "username", true);
		// 读取私有或公共变量的值
//		FieldUtils.readField(classUtilsTest, "username", true);
//		FieldUtils.readStaticField(ClassUtilsTest.class, "STATIC_FIELD");
//		FieldUtils.writeField(classUtilsTest, "username", "RayRay", true);
//
//		FieldUtils.writeStaticField(ClassUtilsTest.class, "STATIC_FIELD", "static_value");
		}
	}

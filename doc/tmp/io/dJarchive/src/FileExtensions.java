package io.dJarchive.src;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
/**
 * annotation表示处理文件的类型
 * @author baoyang
 */
@Retention(RUNTIME)
public @interface FileExtensions {
	String[] values();
}


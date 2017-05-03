package io.dJarchive.src;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
/**
 * annotation表示处理文件的类型
 * @author baoyang
 */
@Retention(RUNTIME)
public @interface FileExtensions {
	String[] values();
}


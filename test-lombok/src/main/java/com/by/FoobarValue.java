package com.by;

import org.immutables.value.Value;

import java.util.List;
import java.util.Set;

/**
 * description: Immutables包测试,生成不可变对象
 * create       2017/5/26 16:56
 *
 * @author email:baoyang@jd.com,ERP:baoyang3
 * @version 1.0.0
 */
@Value.Immutable
public abstract class FoobarValue {
    public abstract int foo();
    public abstract String bar();
    public abstract List<Integer> buz();
    public abstract Set<Long> crux();
}
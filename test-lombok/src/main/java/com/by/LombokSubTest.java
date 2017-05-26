package com.by;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * description:
 * create       2017/5/26 14:45
 *
 * @author email:baoyang@jd.com,ERP:baoyang3
 * @version 1.0.0
 */
@ToString(callSuper=true,exclude="name")
@EqualsAndHashCode(callSuper=true,exclude={"name","age"})
public class LombokSubTest extends LombokTest {
    private String sub;
}

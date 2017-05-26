package com.by;

import lombok.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * description:
 * create       2017/5/26 14:38
 *
 * @author email:baoyang@jd.com,ERP:baoyang3
 * @version 1.0.0
 */
@ToString(exclude = "name")
@Data
public class LombokTest {
    private Integer age;
    private String name;
    //    @NonNull
    private List<Object> employees;
    private boolean employed = true;
    private String text;

    public void testCleanUp() {
        try {
            @Cleanup ByteArrayOutputStream baos = new ByteArrayOutputStream();
            baos.write(new byte[]{'Y', 'e', 's'});
            System.out.println(baos.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Synchronized
    public String synchronizedFormat(Date date) {
        DateFormat format = new SimpleDateFormat("MM-dd-YYYY");
        return format.format(date);
    }

    @SneakyThrows(Exception.class) //把编译异常转为运行时异常
    public void testSneakyThrows() {
        throw new IllegalAccessException();
    }

    public void NonNullExample(@NonNull String name) {
        this.name = name;
    }

    @Getter(lazy = true)
    private final double[] cached = expensive();

    private double[] expensive() {
        double[] result = new double[1000000];
        for (int i = 0; i < result.length; i++) {
            result[i] = Math.asin(i);
        }
        return result;
    }
}

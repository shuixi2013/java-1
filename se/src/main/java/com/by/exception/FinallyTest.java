package com.by.exception;

/**
 * finally里抛异常问题
 */
public class FinallyTest {
    void f() {
        throw new IllegalArgumentException("one");
    }

    void dispose() {
        throw new IllegalStateException("two");
    }

    public static void main(String[] args)
            throws Exception {
        FinallyTest lm = new FinallyTest();
        try {
            /**
             * 不会抛出
             */
            lm.f();
        } finally {
            lm.dispose();
        }
    }
}

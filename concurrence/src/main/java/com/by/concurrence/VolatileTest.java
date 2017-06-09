package com.by.concurrence;

import java.util.concurrent.TimeUnit;

public class VolatileTest extends Thread {
    private volatile static int count;

    private  static void addCount() {
        for (int i = 0; i < 100; i++) {
            count++;
        }
    }

    @Override
    public void run() {
        addCount();
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            new VolatileTest().start();
        }
        TimeUnit.SECONDS.sleep(1L);
        System.out.println("count=" + count);
    }
}

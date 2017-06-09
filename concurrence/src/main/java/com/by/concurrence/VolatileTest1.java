package com.by.concurrence;

import java.util.concurrent.TimeUnit;

public class VolatileTest1 extends Thread {
    private volatile static int count = 0;

    private void addCount() {
        System.out.println(this.getName() + "start" + count);
        if ("A".equals(this.getName())) {
            try {
                TimeUnit.MILLISECONDS.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            count = 2;
        }
        System.out.println(this.getName() + "end" + count);
    }

    @Override
    public void run() {
        addCount();
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileTest1 volatileTest1 = new VolatileTest1();
        volatileTest1.setName("A");
        volatileTest1.start();
        new VolatileTest1().start();
        TimeUnit.SECONDS.sleep(1L);
        System.out.println("count=" + count);
    }
}

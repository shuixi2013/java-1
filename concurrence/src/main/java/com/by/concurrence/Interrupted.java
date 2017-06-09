package com.by.concurrence;

public class Interrupted extends Thread {
    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 500000; i++) {
            if (this.interrupted()) {
                System.out.println("interrupted");
                break;
            }
            System.out.println("i=" + (i + 1));
        }
        System.out.println("interrupted");
    }

    public static void main(String[] args) {
        try {
            Interrupted thread = new Interrupted();
            thread.start();
            Thread.sleep(2000);
            thread.interrupt();
        } catch (InterruptedException e) {
            System.out.println("main catch");
            e.printStackTrace();
        }
        System.out.println("end!");
    }
}



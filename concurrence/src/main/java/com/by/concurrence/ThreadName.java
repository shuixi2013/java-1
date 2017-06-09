package com.by.concurrence;

public class ThreadName extends Thread {
    public ThreadName() {
        System.out.println(Thread.currentThread().getName());
        System.out.println(this.getName());
        System.out.println(this.getId());
    }

    @Override
    public void run() {
        System.out.println("start");
        System.out.println(Thread.currentThread().getName());
        System.out.println(this.getName());
        System.out.println(this.getId());
    }

    public static void main(String[] args) {
        ThreadName mythread = new ThreadName();
        mythread.start();
        Thread t1 = new Thread(mythread);
        t1.setName("a");
        t1.start();
        System.out.println("t1.id=" + t1.getId());
    }
}
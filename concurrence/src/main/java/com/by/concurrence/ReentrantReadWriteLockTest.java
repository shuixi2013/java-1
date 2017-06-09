package com.by.concurrence;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockTest {

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public static void main(String[] args) throws InterruptedException {

        ReentrantReadWriteLockTest service = new ReentrantReadWriteLockTest();

        ThreadB b = new ThreadB(service);
        b.setName("B");
        b.start();

        ThreadA a = new ThreadA(service);
        a.setName("A");
        a.start();

    }

    public void read() {
        try {
            try {
                lock.readLock().lock();
                System.out.println("获得读锁" + Thread.currentThread().getName()
                        + " " + System.currentTimeMillis());
                Thread.sleep(10000);
            } finally {
                lock.readLock().unlock();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void write() {
        try {
            try {
                lock.writeLock().lock();
                System.out.println("获得写锁" + Thread.currentThread().getName()
                        + " " + System.currentTimeMillis());
                Thread.sleep(10000);
            } finally {
                lock.writeLock().unlock();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}

class ThreadA extends Thread {

    private ReentrantReadWriteLockTest service;

    public ThreadA(ReentrantReadWriteLockTest service) {
        super();
        this.service = service;
    }

    @Override
    public void run() {
        service.read();
    }

}

class ThreadB extends Thread {

    private ReentrantReadWriteLockTest service;

    public ThreadB(ReentrantReadWriteLockTest service) {
        super();
        this.service = service;
    }

    @Override
    public void run() {
        service.write();
    }
}


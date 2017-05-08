package com.by.thread;

/**
 * Description: <br/>
 * 网站: <a href="http://www.crazyit.org">疯狂Java联盟</a> <br/>
 * Copyright (C), 2001-2012, Yeeku.H.Lee <br/>
 * This program is protected by copyright laws. <br/>
 * Program Name: <br/>
 * Date:
 *
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class DaemonThread extends Thread {
    // 定义后台线程的线程执行体与普通线程没有任何区别
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(getName() + "  " + i);
            if (i == 8) {
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DaemonThread t = new DaemonThread();
        // 将此线程设置成后台线程
        t.setDaemon(true);
        // 启动后台线程
        t.start();
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "  " + i);
            if (i == 2) {
                Thread.sleep(5);
            }
        }
        System.out.println("main 结束");
    }
}

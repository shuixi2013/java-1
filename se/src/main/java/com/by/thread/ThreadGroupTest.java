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
class MyThread extends Thread {
    // 提供指定线程名的构造器
    public MyThread(String name) {
        super(name);
    }

    // 提供指定线程名、线程组的构造器
    public MyThread(ThreadGroup group, String name) {
        super(group, name);
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i == 5) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(getName() + " 线程的i变量" + i + "-" + this.isDaemon());
        }
    }
}

public class ThreadGroupTest {
    public static void main(String[] args) {
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();  // 获取主线程所在的线程组，这是所有线程默认的线程组
        System.out.println("主线程组的名字：" + mainGroup.getName());
        System.out.println("主线程组是否是后台线程组：" + mainGroup.isDaemon());
//        new MyThread("主线程组的线程").start();
        ThreadGroup tg = new ThreadGroup("新线程组");
        tg.setDaemon(true); //只是优先级比较低而已
        System.out.println("tg线程组是否是后台线程组：" + tg.isDaemon());
        MyThread tt = new MyThread(tg, "tg组的线程甲");
        tt.start();
        new MyThread(tg, "tg组的线程乙").start();
        System.out.println("主线程组结束");
    }
}

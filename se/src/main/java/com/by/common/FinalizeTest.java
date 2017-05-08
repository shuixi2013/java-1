package com.by.common;

/**
 * description:
 * create       2017/5/3 20:15
 *
 * @author email:baoyang@jd.com,ERP:baoyang3
 * @version 1.0.0
 */
public class FinalizeTest {
    public static void main(String[] args) {
        while (!Chair.f) {
            new Chair();
            new String("To take up space");
        }
        System.out.println(
                "After all Chairs have been created:\n" +
                        "total created = " + Chair.created +
                        ", total finalized = " + Chair.finalized);
        if (args.length > 0) {
            if (args[0].equals("gc") ||
                    args[0].equals("all")) {
                System.out.println("gc():");
                System.gc();
            }
            if (args[0].equals("finalize") ||
                    args[0].equals("all")) {
                System.out.println("runFinalization():");
                System.runFinalization(); //强制调用已经失去引用的对象的finalize方法
            }
        }
        System.out.println("bye!");
    }
}

class Chair {
    static boolean gcrun = false;
    static boolean f = false;
    static int created = 0;
    static int finalized = 0;
    int i;

    Chair() {
        i = ++created;
        if (created == 47)
            System.out.println("Created 47");
    }

    public void finalize() {
        if (!gcrun) {
            gcrun = true;
            System.out.println(
                    "Beginning to finalize after " +
                            created + " Chairs have been created");
        }
        if (i == 47) {
            System.out.println(
                    "Finalizing Chair #47, " +
                            "Setting flag to stop Chair creation");
            f = true;
        }
        finalized++;
        if (finalized >= created){
            System.out.println("All " + finalized + " finalized");
        }
    }
}
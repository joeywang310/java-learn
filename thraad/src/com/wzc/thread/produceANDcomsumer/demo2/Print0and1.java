package com.wzc.thread.produceANDcomsumer.demo2;

import java.util.List;

/**
 * @program: java-learn
 * @description: 交替打印0和1,
 * @author: wangzhicheng
 * @create: 2019-12-03 16:47
 **/
public class Print0and1 {

    public static final String LOCK = "LOCK";
    static boolean flag = false;

    public static void main(String[] args) {


        new Thread(() -> {
            synchronized (LOCK) {
                while (true) {
                    if (flag) {
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println(0);
                        flag = true;
                        LOCK.notify();
                    }
                }
            }

        }).start();

        new Thread(() -> {
            synchronized (LOCK) {
                while (true) {
                    if (!flag) {
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println(1);
                        flag = false;
                        LOCK.notify();
                    }
                }
            }


        }).start();
    }

}

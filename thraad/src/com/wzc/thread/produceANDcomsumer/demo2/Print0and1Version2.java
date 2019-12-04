package com.wzc.thread.produceANDcomsumer.demo2;

/**
 * @program: java-learn
 * @description:
 * @author: wangzhicheng
 * @create: 2019-12-03 17:19
 **/
public class Print0and1Version2 {
    private static volatile boolean flag = false;

    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                if (!flag) {
                    System.out.println(0);
                    flag = true;
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                if (flag) {
                    System.out.println(1);
                    flag = false;
                }
            }
        }).start();
    }
}

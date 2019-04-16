package com.wzc.waitnotifynotifyall;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: joey
 * Date: 2019-04-15 17:57
 */
public class YieldDemo extends Thread {

    public static void main(String[] args) {
        new Yield().start();
        new YieldDemo().start();
    }


    @Override
    public void run() {
        for (int i=0; i<11; i++) {
            System.out.println("第一个线程第"+i+"次执行");
        }
    }
}
class Yield extends Thread {
    @Override
    public void run() {
        for (int i=0; i<11; i++) {
            System.out.println("第二个线程第"+i+"次执行");
            Thread.yield();
        }
    }
}

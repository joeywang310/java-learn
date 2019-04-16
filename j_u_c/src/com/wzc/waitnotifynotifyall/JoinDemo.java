package com.wzc.waitnotifynotifyall;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: joey
 * Date: 2019-04-15 18:50
 */
public class JoinDemo {
    public static void main(String[] args) {
        Join join = new Join();
        join.start();
        for (int i = 0; i < 11; i++) {
            System.out.println("主线程第" + i + "次执行");
            if (i > 5)
                try {
                    join.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
    }
}

class Join extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 11; i++) {
            System.out.println("第二个线程第" + i + "次执行");
        }
    }
}
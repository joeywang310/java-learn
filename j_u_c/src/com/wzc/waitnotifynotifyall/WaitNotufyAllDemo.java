package com.wzc.waitnotifynotifyall;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: joey
 * Date: 2019-04-15 17:23
 */
public class WaitNotufyAllDemo extends Thread {

    private Calculator calculator;

    public WaitNotufyAllDemo(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public void run() {
        synchronized (calculator) {
            try {
                System.out.println(Thread.currentThread() + "等待计算结果。。。");
                calculator.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + "计算结果为：" + calculator.count);
        }
    }


    //测试
    public static void main(String[] args) throws InterruptedException {
        Calculator calculator = new Calculator();
        new WaitNotufyAllDemo(calculator).start();
        new WaitNotufyAllDemo(calculator).start();
        new WaitNotufyAllDemo(calculator).start();
//        sleep(1000);
        calculator.start();
    }
}

class Calculator extends Thread {

    int count = 0;

    @Override
    public void run() {
        synchronized (this) {
            System.out.println("开始计数");
            for (int i=0; i<10001; i++)
                count += i;
            //释放等待当前锁的所有线程
            this.notifyAll();
        }
    }
}

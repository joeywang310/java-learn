package com.wzc.waitnotifynotifyall;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: joey
 * Date: 2019-04-15 17:00
 */
public class WaitNotifyDemo {

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();

        //当前线程拥有myThread对象上的锁。线程为了调用wait()或notify()方法，该线程必须是那个对象锁的拥有者
        synchronized (myThread) {
            try {
                System.out.println("等待对象b完成计算。。。");
                //当前线程A等待
                myThread.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("b对象计算的总和是：" + myThread.count);
        }
    }

}
class MyThread extends Thread {
    int count = 0;

    @Override
    public void run() {
        synchronized (this) {
            System.out.println("计数开始");
            for (int i=0 ; i <1001 ; i++)
                count += i;
            //释放当前锁
            this.notify();
            System.out.println("notify释放锁");
        }
    }
}
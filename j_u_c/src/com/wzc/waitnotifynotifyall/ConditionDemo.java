package com.wzc.waitnotifynotifyall;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: joey
 * Date: 2019-04-15 20:22
 */
public class ConditionDemo {
}
class MyCount {
    private int money;
    private Lock lock = new ReentrantLock();                //账户锁
    private Condition saveCondition = lock.newCondition();    //存款条件
    private Condition withdrawCondition = lock.newCondition();    //取款条件

    public MyCount(int money) {
        this.money = money;
    }

    public void save(int x,String name) {
        lock.lock();                        //获取锁
        if (x > 0) {
            money += x;                    //存款
            System.out.println(name + "存款" + x + "，当前余额为" + money);
        }
        withdrawCondition.signalAll();            //唤醒所有等待线程。
        lock.unlock();                    //释放锁
    }
    public void withdraw(int x,String name) {
        lock.lock();                                 //获取锁
        try {
            if (money - x < 0) {
                withdrawCondition.await();             //阻塞取款操作
            } else {
                money -= x;                     //取款
                System.out.println(name + "取款" + x + "，当前余额为" + money);
            }
            saveCondition.signalAll();             //唤醒所有存款操作
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();                     //释放锁
        }
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
class SaveThread extends Thread {
    private String name;                //操作人
    private MyCount myCount;        //账户
    private int x;                            //存款金额

    public SaveThread(String name,MyCount myCount,int x){
        this.name = name;
        this.myCount = myCount;
        this.x = x;
    }

    @Override
    public void run() {
        myCount.save(x,name);
    }
}
class WithdrawThread extends Thread {

    private String name;                //操作人
    private MyCount myCount;        //账户
    private int x;                            //存款金额

    @Override
    public void run() {
        myCount.withdraw(x,name);
    }

}
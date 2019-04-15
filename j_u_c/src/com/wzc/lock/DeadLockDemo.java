package com.wzc.lock;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: joey
 * Date: 2019-04-15 16:02
 */
public class DeadLockDemo {

    private final List list1 = new ArrayList<>();
    private final List lsit2 = new ArrayList<>();

    private void test1(){
        System.out.println("进入test1");
        synchronized (list1) {
            System.out.println("test1获取到第一个锁");
            synchronized (lsit2){
                System.out.println(1);
            }
        }
    }
    private void test2(){
        System.out.println("进入test2");
        synchronized (lsit2) {
            System.out.println("test2获取到第一个锁");
            synchronized (list1){
                System.out.println(1);
            }
        }
    }

    public static void main(String[] args) {
        DeadLockDemo deadLockDemo = new DeadLockDemo();
        new Thread(deadLockDemo::test1).start();
        new Thread(deadLockDemo::test2).start();
    }
}

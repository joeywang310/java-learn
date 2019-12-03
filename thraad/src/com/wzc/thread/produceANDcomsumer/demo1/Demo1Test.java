package com.wzc.thread.produceANDcomsumer.demo1;

/**
 * @program: java-learn
 * @description:
 * @author: wangzhicheng
 * @create: 2019-12-02 20:26
 **/
public class Demo1Test {


    public static void main(String[] args) {
        Resource resource = new Resource();
        Thread thread = new Thread(new Producer(resource));
        Thread thread1 = new Thread(new Consumer(resource));
        thread.start();
        thread1.start();

    }
}

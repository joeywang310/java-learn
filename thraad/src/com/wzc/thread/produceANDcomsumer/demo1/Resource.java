package com.wzc.thread.produceANDcomsumer.demo1;

/**
 * @program: java-learn
 * @description: 生产资料类
 * @author: wangzhicheng
 * @create: 2019-12-02 20:17
 **/
public class Resource {

    private String name;
    private int age;

    private boolean p = false;

    public synchronized void push(String name,int age) throws InterruptedException {
        while (true) {
            if (p)
                wait();
            else {
                this.name = name;
                this.age = age;
                System.out.println("producer"+this.name+"============"+this.age);
                p = true;
                notify();
                break;
            }
        }

    }

    public synchronized void pull() throws InterruptedException {
        while (true) {
            if (!p)
                wait();
            else {
                System.out.println("consumer"+this.name+"============"+this.age);
                p = false;
                notify();
                break;
            }
        }

    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.wzc.lock;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: joey
 * Date: 2019-04-10 14:25
 */
public class DoubleCheckLockSingleTonDemo {

    //第一步：私有化当前实例，不允许外部直接访问
    private static DoubleCheckLockSingleTonDemo instance;

    //第二步：私有化构造函数，不允许通过构造函数创建对象
    private DoubleCheckLockSingleTonDemo() {
    }

    //第三步：公共方法提供获取单例对象
    public static DoubleCheckLockSingleTonDemo getInstance() {
        if (instance == null) {
            //提供线程安全的单例对象
            synchronized (DoubleCheckLockSingleTonDemo.class) {//因为是单例，所以用的是类锁
                //双重校验，防止被当前锁阻塞的多个线程重复的是生成新对象
                if (instance == null)
                    instance = new DoubleCheckLockSingleTonDemo();
                return instance;
            }
        }
        return instance;
    }


    public static void main(String[] args) {
        for (int i = 0; i < 100; i++)
            new Thread(() -> System.out.println(DoubleCheckLockSingleTonDemo.getInstance())).start();
    }

}

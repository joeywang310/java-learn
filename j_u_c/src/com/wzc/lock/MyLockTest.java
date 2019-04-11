package com.wzc.lock;

import java.util.Arrays;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * Description: 测试自定义实现的排他锁
 * User: joey
 * Date: 2019-04-11 14:39
 */
public class MyLockTest {
    private static Random r=new Random(47);

    public static void main(String[] args) {

        int threadCount = 15;

        //自定义的派他锁
        MyMutexLock myMutexLock = new MyMutexLock();

        Thread[] threads = new Thread[threadCount];

        for (int i =0;i<15;i++){
            int finalI = i;
            threads[i] = new Thread(()->{
                myMutexLock.lock();
                System.out.println(finalI +"放苹果！");
                System.out.println(finalI +"重量："+(r.nextInt(10)+3));
                System.out.println(finalI +"取苹果！");
                myMutexLock.getQueuedThreads().forEach(thread -> System.out.println(thread.getName()));
                myMutexLock.unlock();
            },String.valueOf(i));

        }
        //执行线程
        Arrays.asList(threads).forEach(Thread::start);
        //打印结果

//        0放苹果！
//        0重量：11
//        0取苹果！
//        6
//        5
//        1
//        2
//        2放苹果！
//        2重量：8
//        2取苹果！
//        14
//        13
//        12
//        11
//        10
//        4
//        8
//        7
//        9
//        3
//        6
//        5
//        1
//        1放苹果！
//        1重量：6
//        1取苹果！
//        14
//        13
//        12
//        11
//        10
//        4
//        8
//        7
//        9
//        3
//        6
//        5
//        5放苹果！
//        5重量：4
//        5取苹果！
//        14
//        13
//        12
//        11
//        10
//        4
//        8
//        7
//        9
//        3
//        6
//        6放苹果！
//        6重量：4
//        6取苹果！
//        14
//        13
//        12
//        11
//        10
//        4
//        8
//        7
//        9
//        3
//        3放苹果！
//        3重量：12
//        3取苹果！
//        14
//        13
//        12
//        11
//        10
//        4
//        8
//        7
//        9
//        9放苹果！
//        9重量：11
//        9取苹果！
//        14
//        13
//        12
//        11
//        10
//        4
//        8
//        7
//        7放苹果！
//        7重量：3
//        7取苹果！
//        14
//        13
//        12
//        11
//        10
//        4
//        8
//        8放苹果！
//        8重量：5
//        8取苹果！
//        14
//        13
//        12
//        11
//        10
//        4
//        4放苹果！
//        4重量：10
//        4取苹果！
//        14
//        13
//        12
//        11
//        10
//        10放苹果！
//        10重量：11
//        10取苹果！
//        14
//        13
//        12
//        11
//        11放苹果！
//        11重量：11
//        11取苹果！
//        14
//        13
//        12
//        12放苹果！
//        12重量：4
//        12取苹果！
//        14
//        13
//        13放苹果！
//        13重量：12
//        13取苹果！
//        14
//        14放苹果！
//        14重量：12
//        14取苹果！

//        结论:在aqs中有FIFO实现的竞争锁队列，
//        当线程没有获取同步锁时会放入队列中，然后当获得锁线程执行完之后，队列头第一个率先取得锁然后执行。
//        队列里后续等待锁线程自旋，判断之前一个节点时候执行完，执行完则会获取到同步锁
//        aqs是公平锁，根据队列里的位置挨个获取同步锁，然后执行
    }
}

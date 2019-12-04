package com.wzc.thread.produceANDcomsumer.demo2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @program: java-learn
 * @description: 多线程批量做某件时间，但是限制同时运行的线程个数
 * @author: wangzhicheng
 * @create: 2019-12-03 18:12
 **/
public class MultiDoWork {

    public static void main(String[] args) {
        LinkedList<Thread> runningThread = new LinkedList<>();

        final Object LOCK = new Object();

        List<Thread> list = new ArrayList<>();
        Stream.of("T1", "T2", "T3", "T4", "T5", "T6", "T7", "T8", "T9", "T10").forEach((name) -> {
            Thread thread = new Thread(name) {
                @Override
                public void run() {
                    while (true) {
                        synchronized (LOCK) {
                            if (runningThread.size() >= 5) {
                                try {
                                    System.out.println(Thread.currentThread().getName()+" waiting...");
                                    LOCK.wait();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }

                        }
                        synchronized (LOCK) {
                            runningThread.addLast(this);
                        }

                        try {
                            System.out.println(Thread.currentThread().getName()+" i am working . 10s");
                            Thread.sleep(3_000);
                            System.out.println(Thread.currentThread().getName()+" i am finish . ");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        synchronized (LOCK) {
                            runningThread.removeFirst();
                            LOCK.notifyAll();
                        }
                        break;
                    }
                }
            };
            thread.start();
            list.add(thread);
        });

        list.forEach(thread -> {

            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

    }

}

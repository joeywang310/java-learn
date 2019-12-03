package com.wzc.thread.produceANDcomsumer.demo1;

import java.util.stream.Stream;

/**
 * @program: java-learn
 * @description: 生产者
 * @author: wangzhicheng
 * @create: 2019-12-02 20:19
 **/
public class Producer implements Runnable {

    private Resource resource;

    public Producer(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 50; i++) {
                if (i % 2 == 0) {
                    resource.push("小明", 1);
                } else {
                    resource.push("小红", 2);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

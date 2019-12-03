package com.wzc.thread.produceANDcomsumer.demo1;

/**
 * @program: java-learn
 * @description: 消费者
 * @author: wangzhicheng
 * @create: 2019-12-02 20:23
 **/
public class Consumer implements Runnable {

    private Resource resource;

    public Consumer(Resource resource) {

        this.resource = resource;
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            try {
                resource.pull();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

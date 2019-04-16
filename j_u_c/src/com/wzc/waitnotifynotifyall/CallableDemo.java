package com.wzc.waitnotifynotifyall;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: joey
 * Date: 2019-04-15 19:47
 */
public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Callable callable1 = new MyCallable("1");
        Callable callable2 = new MyCallable("2");
        Future submit1 = executorService.submit(callable1);
        Future submit2 = executorService.submit(callable2);
        System.out.println(submit1.get().toString());
        System.out.println(submit2.get().toString());
        executorService.shutdown();
    }
}
class MyCallable implements Callable {

    private String oid;

    MyCallable(String oid) {
        this.oid = oid;
    }

    @Override
    public Object call() throws Exception {
        return new ArrayList<>();
    }
}
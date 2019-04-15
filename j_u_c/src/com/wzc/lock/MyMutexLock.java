package com.wzc.lock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: joey
 * Date: 2019-04-11 14:22
 */
public class MyMutexLock {


    private static class Sync extends AbstractQueuedSynchronizer {

        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        @Override
        protected boolean tryAcquire(int arg) {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int releases) {
            if (getState() == 0)
                throw new IllegalMonitorStateException();
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }
    }

    // 仅需要将操作代理到Sync上即可
    private final Sync sync = new Sync();

    //获取等待的线程
    public Collection<Thread> getQueuedThreads(){
        return sync.getQueuedThreads();
    }

    //独占锁的操作接口
    public void lock() {//获取锁
        sync.acquire(1);
    }

    public void unlock() {//释放锁
        sync.release(1);
    }
}

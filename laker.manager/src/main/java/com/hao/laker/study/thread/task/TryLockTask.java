package com.hao.laker.study.thread.task;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by haojiahong on 17/3/20.
 */
public class TryLockTask implements Runnable {

    private ReentrantLock lock;

    public TryLockTask(ReentrantLock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            lock.tryLock();
            System.out.println(Thread.currentThread().getName() + "trylock开始执行");
            Thread.sleep(2000);

            System.out.println(Thread.currentThread().getName() + "执行结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread()) {//trylock很可能未获取到锁，所以如果没有获取到锁但是unlock了会报illegalMonitorException
                lock.unlock();
            }
        }


    }

}

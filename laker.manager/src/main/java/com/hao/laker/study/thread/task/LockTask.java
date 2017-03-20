package com.hao.laker.study.thread.task;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by haojiahong on 17/3/20.
 */
public class LockTask implements Runnable {

    private ReentrantLock lock;

    public LockTask(ReentrantLock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "开始执行");
            Thread.sleep(5000);

            System.out.println(Thread.currentThread().getName() + "执行结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }
}

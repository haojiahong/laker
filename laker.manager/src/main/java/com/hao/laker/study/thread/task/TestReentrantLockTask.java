package com.hao.laker.study.thread.task;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by haojiahong on 17/4/15.
 */
public class TestReentrantLockTask implements Runnable {

    private ReentrantLock lock;

    public TestReentrantLockTask(ReentrantLock lock) {
        this.lock = lock;
    }


    @Override
    public void run() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "获得了锁");
            System.out.println(Thread.currentThread().getName() + "线程开始休眠5分钟");
            Thread.sleep(1000 * 60 * 5);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "线程休眠结束，释放锁");
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        TestReentrantLockTask task1 = new TestReentrantLockTask(lock);
        TestReentrantLockTask task2 = new TestReentrantLockTask(lock);
//        TestReentrantLockTask task3 = new TestReentrantLockTask(lock);

        Thread thread = new Thread(task1);
        thread.start();

        Thread thread1 = new Thread(task2);
        thread1.start();
//
//        Thread thread2 = new Thread(task3);
//        thread2.start();

        thread.join();
        thread1.join();
//        thread2.join();

        System.out.println("main end");
    }
}

package com.hao.laker.study.myconcurrent;

/**
 * Created by haojiahong on 2017/8/4.
 */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadDemo implements Runnable {

    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private int state = 0;

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        lock.lock();
        // 进入临界区
        try {

            if (name.equals("B")) {
                // 只有a和a2同时为true时才打印B，否则阻塞当前线程
                while (state % 3 != 1) {
                    condition.await();// 条件不满足，暂时阻塞线程，暂时释放lock
                }
            } else if (name.equals("C")) {
                while (state % 3 != 2) {
                    condition.await();
                }
            } else if (name.equals("A")) {
                while (state % 3 != 0) {
                    condition.await();
                }
            }
            state++;
            System.out.print(name);
            condition.signalAll();// 通知正在等待的线程，此时有可能已经满足条件

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();// 记得要释放锁
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadDemo task = new ThreadDemo();
        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);
        Thread thread3 = new Thread(task);
        thread1.setName("A");
        thread2.setName("B");
        thread3.setName("C");
        thread1.start();
        thread2.start();
        thread3.start();
    }

}

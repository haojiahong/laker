package com.hao.laker.study.redisson;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * Created by haojiahong on 17/5/19.
 */
public class LockExample {

    public static void main(String[] args) throws InterruptedException {
        // connects to 127.0.0.1:6379 by default
        final RedissonClient redisson = RedissonManager.getRedisson();

        RLock lock = redisson.getLock("lock");
        lock.lock(2, TimeUnit.SECONDS);
        System.out.println(Thread.currentThread().getId() + "getLock");

        Thread t = new Thread() {
            public void run() {
                RLock lock1 = redisson.getLock("lock");
                lock1.lock();
                System.out.println(Thread.currentThread().getId() + "get lock");
                System.out.println(Thread.currentThread().getId() + "unlock");
                lock1.unlock();
            }
        };

        t.start();
        t.join();

        System.out.println(Thread.currentThread().getId() + "unlock");
        lock.unlock();

        redisson.shutdown();
    }

}


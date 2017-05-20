package com.hao.laker.study.redisson;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by haojiahong on 17/5/19.
 */
public class RedissonTest {
    public static RedissonClient redisson = RedissonManager.getRedisson();

    public static void main(String[] args) {
//        RLock lock = redisson.getLock("haoge");
//        lock.lock();
//        try {
//            System.out.println("get lock");
//        } finally {
//            lock.lock();
//        }
//        redisson.shutdown();
//

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        LockTask lockTask = new LockTask();
        Thread threadA = new Thread(lockTask);
        Thread threadB = new Thread(lockTask);

        executorService.execute(lockTask);
        executorService.execute(lockTask);


    }

    public static class LockTask implements Runnable {
        private RLock lock = redisson.getLock("lockTask");

        //没有测试出来所谓的心跳机制啊
        @Override
        public void run() {
            lock.lock(25, TimeUnit.SECONDS);
            try {
                System.out.println(Thread.currentThread().getId() + "lock");
                Thread.sleep(30000);
                System.out.println("get lockTask lock");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getId() + "unLock");
                lock.unlock();
            }
        }
    }
}

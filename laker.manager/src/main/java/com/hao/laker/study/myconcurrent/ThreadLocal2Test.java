package com.hao.laker.study.myconcurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by haojiahong on 17/5/23.
 */
public class ThreadLocal2Test {
    private static ExecutorService executor = Executors.newFixedThreadPool(1);

    public static class MyRunnable implements Runnable {

        private ThreadLocal threadLocal = new ThreadLocal();

        @Override
        public void run() {
            System.out.println(Thread.currentThread());
            threadLocal.set((int) (Math.random() * 100D));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {

            }
            System.out.println(Thread.currentThread());
            System.out.println(threadLocal.get());
        }
    }

    public static class MyRunnable2 implements Runnable {

        private ThreadLocal threadLocal = new ThreadLocal();

        @Override
        public void run() {
            System.out.println(Thread.currentThread());
            threadLocal.set((int) (Math.random() * 100D));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {

            }
            System.out.println(Thread.currentThread());
            System.out.println(threadLocal.get());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyRunnable sharedRunnableInstance = new MyRunnable();
//        Thread thread1 = new Thread(sharedRunnableInstance);
//        Thread thread2 = new Thread(sharedRunnableInstance);
//        thread1.start();
//        thread2.start();
        executor.execute(sharedRunnableInstance);
        executor.execute(sharedRunnableInstance);

        sharedRunnableInstance = null;

        System.gc();

        Thread.sleep(1000);

        MyRunnable2 shared2Instance = new MyRunnable2();
        executor.execute(shared2Instance);

    }
}

package com.hao.laker.study.myconcurrent;

import java.util.concurrent.Semaphore;

/**
 * Semaphore可以控制同时访问某个资源的线程数。
 * Created by haojiahong on 17/3/27.
 */
public class SemaphoreTest {

    private static Semaphore semaphore = new Semaphore(5);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Driver driver = new Driver(i);
            Thread thread = new Thread(driver);
            thread.start();
        }
    }


    static class Driver implements Runnable {

        private int i;

        public Driver(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("Driver" + i + "is using car");
                Thread.sleep(1000);
                System.out.println("Driver" + i + "return car");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }


    }
}

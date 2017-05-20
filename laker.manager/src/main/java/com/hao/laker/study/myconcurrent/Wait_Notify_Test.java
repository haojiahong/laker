package com.hao.laker.study.myconcurrent;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by haojiahong on 17/5/20.
 */
public class Wait_Notify_Test {
    public static Object lockObj = new Object();
    public static List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6);

    public static void main(String[] args) throws InterruptedException {
        Thread oddThread = new Thread(new OddTask(lockObj));
        Thread evenThread = new Thread(new EvenTask(lockObj));
        oddThread.start();
        evenThread.start();
        oddThread.join();
        evenThread.join();
        System.out.println("main over");

    }

    public static class OddTask implements Runnable {
        private Object lockObj;

        public OddTask(Object lockObj) {
            this.lockObj = lockObj;
        }

        @Override
        public void run() {
            synchronized (lockObj) {
                for (Integer i : list) {
                    lockObj.notify();
                    if (i % 2 != 0) {
                        System.out.println(Thread.currentThread().getId() + "输出奇数：" + i);
                        try {
                            lockObj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }

        }
    }

    public static class EvenTask implements Runnable {
        private Object lockObj;

        public EvenTask(Object lockObj) {
            this.lockObj = lockObj;
        }

        @Override
        public void run() {
            synchronized (lockObj) {
                for (Integer i : list) {
                    lockObj.notify();
                    if (i % 2 == 0) {
                        System.out.println(Thread.currentThread().getId() + "输出偶数：" + i);
                        try {
                            lockObj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        }
    }

}

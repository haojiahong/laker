package com.hao.laker.study.thread.task;

/**
 * Created by haojiahong on 17/5/22.
 */
public class InterruptTest {

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("线程被中断了");
                        return;
                    } else {
                        System.out.println("线程没有被中断");
                    }
                }
            }
        };
        Thread t = new Thread(runnable);
        t.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.interrupt();
        System.out.println("线程中断了，程序到这里了");
    }
}

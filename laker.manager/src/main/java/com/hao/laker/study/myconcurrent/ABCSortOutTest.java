package com.hao.laker.study.myconcurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

/**
 * Created by haojiahong on 2017/8/4.
 */
public class ABCSortOutTest {
    private static CountDownLatch latch = new CountDownLatch(1);
    private static CountDownLatch latch2 = new CountDownLatch(1);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        PrintATask printATask = new PrintATask(latch);
        PrintTask printBTask = new PrintTask("b", latch, latch2);
        PrintTask printCTask = new PrintTask("c", latch2, null);

        new Thread(printATask).start();
        new Thread(printBTask).start();
        new Thread(printCTask).start();


    }

    static class PrintATask implements Runnable {
        private CountDownLatch latch;

        public PrintATask(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            System.out.println("a");
            latch.countDown();

        }
    }

    static class PrintTask implements Runnable {
        private String name;
        private CountDownLatch latch;
        private CountDownLatch latch2;

        public PrintTask(String name, CountDownLatch latch, CountDownLatch latch2) {
            this.name = name;
            this.latch = latch;
            this.latch2 = latch2;
        }


        @Override
        public void run() {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name);
            if (latch2 != null) {
                latch2.countDown();
            }

        }
    }


}

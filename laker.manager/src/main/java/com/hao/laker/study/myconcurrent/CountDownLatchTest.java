package com.hao.laker.study.myconcurrent;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 设想有这样一个功能需要Thread1、Thread2、Thread3、Thread4四条线程分别统计C、D、E、F四个盘的大小，所有线程都统计完毕交给主线程去做汇总，
 * Created by haojiahong on 17/3/27.
 */
public class CountDownLatchTest {

    private static CountDownLatch latch = new CountDownLatch(4);

    private static ExecutorService executorService = Executors.newFixedThreadPool(6);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CountTask task1 = new CountTask("A", latch);
        CountTask task2 = new CountTask("B", latch);
        CountTask task3 = new CountTask("C", latch);
        CountTask task4 = new CountTask("D", latch);

        Future<Integer> num1 = executorService.submit(task1);
        Future<Integer> num2 = executorService.submit(task2);
        Future<Integer> num3 = executorService.submit(task3);
        Future<Integer> num4 = executorService.submit(task4);

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
        Integer num = num1.get() + num2.get() + num3.get() + num4.get();
        System.out.println(num);


    }

    static class CountTask implements Callable<Integer> {

        private String name;
        private CountDownLatch latch;

        public CountTask(String name, CountDownLatch latch) {
            this.name = name;
            this.latch = latch;
        }


        @Override
        public Integer call() throws Exception {
            int timer = new Random().nextInt(5);
            TimeUnit.SECONDS.sleep(timer);
            System.out.println(name + "磁盘大小统计完毕，返回：" + timer);
            latch.countDown();
            return timer;
        }
    }

}

package com.hao.laker.study.myconcurrent;

import java.util.concurrent.*;

/**
 * Created by haojiahong on 17/5/7.
 */
public class ThreadLocalTest {
    private static ExecutorService executor = Executors.newFixedThreadPool(1);
    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    public static ThreadLocal<String> threadLocal2 = new ThreadLocal<>();

    //线程池中线程通过一个任务对ThreadLocal变量赋值。
    private static class SetTask implements Callable<String> {
        private Integer i;

        public SetTask(Integer i) {
            this.i = i;
        }


        @Override
        public String call() throws Exception {
            threadLocal.set(Thread.currentThread().getName() + "的值为:" + i);
            threadLocal.set(Thread.currentThread().getName() + "的值为2:" + i);
            threadLocal2.set(Thread.currentThread().getName() + "的值为3" + i);
            System.out.println(Thread.currentThread());
            return threadLocal.get();
        }
    }

    //线程池中线程通过一个任务获取此线程本地化的ThreadLocal变量值
    private static class GetTask implements Callable<String> {

        @Override
        public String call() throws Exception {
            System.out.println(Thread.currentThread().getName());
            return threadLocal.get();
        }
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        SetTask setTask = new SetTask(2);
        GetTask getTask = new GetTask();
        //给线程池中的这个线程设置了ThreadLocal值
        Future<String> result = executor.submit(setTask);
        System.out.println(result.get());

        //新的任务拿到了以前任务设置的值，这是脏数据。
        Future<String> getResult = executor.submit(getTask);
        System.out.println(getResult.get());

        threadLocal.set(Thread.currentThread().getName() + "的值为:" + 5);
        System.out.println(threadLocal.get());

        threadLocal = null;

        Future<String> getResult2 = executor.submit(getTask);
        System.out.println(getResult2.get());
    }

}

package com.hao.laker.study.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by haojiahong on 17/3/20.
 */
public class Test {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(5));
        //当使用此拒绝策略时，主线程不会抛出异常，会继续执行了。
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());


        //3分钟后向线程提交一个新任务，用来查看线程池中已完成的任务数量
//        MyMoniter myMoniter = new MyMoniter(executor);
//        MyTask2 myTask2 = new MyTask2(executor, myMoniter);
//        executor.execute(myTask2);

        //当向线程中加入的任务数量大于15时，会执行拒绝策略，抛出异常信息
        //验证此时3分钟后向线程池中加入的任务是什么情况
        for (int i = 0; i < 20; i++) {
            MyTask myTask = new MyTask(i);
            executor.execute(myTask);
            System.out.println("线程池中线程数目：" + executor.getPoolSize() + "，队列中等待执行的任务数目：" +
                    executor.getQueue().size() + "，已执行完的任务数目：" + executor.getCompletedTaskCount());
        }
        //当主线程抛出RejectedExecutionException后，主线程代码已经不能执行到这里了。这异常时未处理的异常，保证主线程顺利执行，需要捕获该异常
        System.out.println("主线程，异常后的信息，线程中线程数量：" + executor.getPoolSize());
        try {
            Thread.sleep(60 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();

    }

}

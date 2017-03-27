package com.hao.laker.study.threadpool;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by haojiahong on 17/3/24.
 */
public class MyTask2 implements Runnable {

    ThreadPoolExecutor executor;
    MyMoniter myMoniter;

    public MyTask2(ThreadPoolExecutor executor, MyMoniter myMoniter) {
        this.executor = executor;
        this.myMoniter = myMoniter;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1 * 60 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("3分钟后，开始执行了mytask2，此时向线程池中加入一个新任务 myMoniter");
        executor.execute(myMoniter);


    }
}

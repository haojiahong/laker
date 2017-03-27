package com.hao.laker.study.threadpool;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by haojiahong on 17/3/24.
 */
public class MyMoniter implements Runnable {
    private ThreadPoolExecutor executor;

    public MyMoniter(ThreadPoolExecutor threadPoolExecutor) {
        this.executor = threadPoolExecutor;
    }

    @Override
    public void run() {
        for (; ; ) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("监视器中，查看到已经执行完的任务数目：" + executor.getCompletedTaskCount());
        }


    }
}

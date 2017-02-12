package com.hao.laker.study.thread;

import com.hao.laker.study.thread.task.SecondThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

/**
 * Created by haojiahong on 17/2/10.
 */
@Component
public class MainProcessor {

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    public void testSpringTransactionThread() {

        SecondThread secondThread = new SecondThread();
        taskExecutor.execute(secondThread);


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("我是主线程，执行完毕");
    }

}

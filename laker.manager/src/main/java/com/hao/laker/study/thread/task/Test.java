package com.hao.laker.study.thread.task;

import java.util.List;

/**
 * Created by haojiahong on 17/3/20.
 */
public class Test {

    public static void main(String[] args) {
//        ReentrantLock lock = new ReentrantLock();
//        LockTask lockTask = new LockTask(lock);
//        TryLockTask tryLockTask = new TryLockTask(lock);
//        Thread thread1 = new Thread(lockTask);
//        Thread thread2 = new Thread(tryLockTask);
//        thread1.start();
//        thread2.start();
//        System.out.println("主程序结束");
//

        List<Object> list = null;
        for (Object object : list) {
            System.out.println(object.toString());

        }

        System.out.println("done");


    }
}

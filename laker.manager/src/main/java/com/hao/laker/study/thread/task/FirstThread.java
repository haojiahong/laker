package com.hao.laker.study.thread.task;

/**
 * Created by haojiahong on 17/2/10.
 */
public class FirstThread extends Thread {

    @Override
    public void run() {
        int i = 10 / 0;
        System.out.println(i);
    }
}

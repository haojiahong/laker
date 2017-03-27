package com.hao.laker.study.javabase;

/**
 * Created by haojiahong on 17/3/26.
 */
public class TestDeadLoop {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("测试死循环查看方法jps+jstack");
        while (true) {
            Thread.sleep(10000);
            System.out.println("haojiahong");
        }
    }
}

package com.hao.laker.study.javabase;

/**
 * Created by haojiahong on 17/3/26.
 */
public class TestDeadLoop {

    public static void main(String[] args) throws InterruptedException {
//        System.out.println("测试死循环查看方法jps+jstack");
//        while (true) {
//            Thread.sleep(10000);
//            System.out.println("haojiahong");
//        }

        retry:
        for (int j = 0; j < Integer.MAX_VALUE; j++) {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                System.out.println(i);
                Thread.sleep(500);
                if (i == 100) {
                    continue retry;//从第一层循环开始循环
                }
                if (i == 50) {
                    break retry;//跳出两层循环。
                }
            }
        }
    }
}

package com.hao.laker.study.javabase;

/**
 * Created by haojiahong on 17/2/13.
 */
public class OutClass {
    public String name;

    private class InnerClass {
        private int i;
    }

    public void run() {
        System.out.println(new InnerClass().i);
    }
}

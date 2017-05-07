package com.hao.laker.study.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haojiahong on 17/5/4.
 */
public class OOMTest {
    static class OOMObject {
//        int size_1M = 1024 * 1024;
//        Byte[] bytes = new Byte[size_1M];
    }

    public static void main(String[] args) throws InterruptedException {

        Thread.sleep(1000 * 10);
        List<OOMObject> list = new ArrayList<>();
        while (true) {
//            Thread.sleep(100);
            list.add(new OOMObject());
            System.out.println(list.size());
        }
    }
}

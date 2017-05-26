package com.hao.laker.study.myconcurrent;

import java.util.HashMap;

/**
 * Created by haojiahong on 17/5/24.
 */
public class HashMap_ConcurrentHash_Test {
    public static void main(String[] args) {
        HashMap<Integer, Integer> hashMap = new HashMap<>(1334);
        long time1 = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            hashMap.put(i, i);
        }
        System.out.println(System.currentTimeMillis() - time1);


        long time2 = System.currentTimeMillis();
        HashMap<Integer, Integer> hashMap1 = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            hashMap1.put(i, i);
        }
        System.out.println(System.currentTimeMillis() - time2);

    }
}

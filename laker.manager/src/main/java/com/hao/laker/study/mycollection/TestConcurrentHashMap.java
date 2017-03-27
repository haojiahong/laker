package com.hao.laker.study.mycollection;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by haojiahong on 17/3/23.
 */
public class TestConcurrentHashMap {

    public static void main(String[] args) throws Exception {
        final ConcurrentHashMap<String, Boolean> maps = new ConcurrentHashMap<>();
        final Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread1...start");
                maps.put("first", true);
                maps.put("second", true);
                System.out.println("thread1...end");
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread2...start");
                maps.clear();
                System.out.println("thread2...end");
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        for (Map.Entry<String, Boolean> entry : maps.entrySet()) {
            System.out.println(entry.toString());
        }

        Iterator<Map.Entry<String, Boolean>> mapsIterator = maps.entrySet().iterator();

        List<Integer> list = new ArrayList<>();
        Iterator<Integer> listIterator = list.iterator();

        Map<Integer, String> myMaps = new HashMap<>();
        Iterator<Map.Entry<Integer, String>> values = myMaps.entrySet().iterator();

        System.out.println(maps.toString());


    }

}

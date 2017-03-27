package com.hao.laker.study.mycollection;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * hashMap扩容时导致死循环
 * Created by haojiahong on 17/3/26.
 */
public class DeadHashMap {

    @Getter
    @Setter
    public static class HashMapColletion {
        private AtomicInteger ai = new AtomicInteger(0);
        private Map<Integer, Integer> map = new HashMap<>(1);
    }

    public static class HashMapThread extends Thread {

        private HashMapColletion hashMapColletion;

        public HashMapThread(HashMapColletion hashMapColletion) {
            this.hashMapColletion = hashMapColletion;

        }

        public void run() {
            int ai;
            while ((ai = hashMapColletion.getAi().get()) < 100000) {
                Map map = hashMapColletion.getMap();
                map.put(ai, ai);
                hashMapColletion.getAi().incrementAndGet();
            }
        }
    }

    public static void main(String[] args) {
        HashMapColletion hashMapColletion = new HashMapColletion();
        HashMapThread hmt0 = new HashMapThread(hashMapColletion);
        HashMapThread hmt1 = new HashMapThread(hashMapColletion);
        HashMapThread hmt2 = new HashMapThread(hashMapColletion);
        HashMapThread hmt3 = new HashMapThread(hashMapColletion);
        HashMapThread hmt4 = new HashMapThread(hashMapColletion);
        hmt0.start();
        hmt1.start();
        hmt2.start();
        hmt3.start();
        hmt4.start();
    }

}

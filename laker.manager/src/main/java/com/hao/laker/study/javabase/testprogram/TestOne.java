package com.hao.laker.study.javabase.testprogram;

import com.google.common.collect.Sets;

import java.util.*;

/**
 * 题目内容是，写一个函数，它的作用是接受一个整数（假设为length），
 * 返回一个数组，数组的长度为length，数组中的内容为随机的0至（length－1）的值，并且不能重复。
 * 比如length为5的话，数组可能是[1,0,3,2,4]。
 * Created by haojiahong on 17/3/10.
 */
public class TestOne {
    public static void main(String[] args) {
        Integer length = 5;
        TestOne testOne = new TestOne();
        Integer[] result = testOne.getRandomInteger(length);
        System.out.println(Arrays.toString(result));
        Integer[] result2 = testOne.random(length);
        System.out.println(Arrays.toString(result2));
    }

    public Integer[] getRandomInteger(Integer length) {
        Integer[] result = new Integer[length];
        Set<Integer> set = Sets.newHashSet();
        while (true) {
            Random random = new Random();
            Integer i = random.nextInt(length);
            if (set.size() >= length) {
                break;
            }
            set.add(i);
        }
        result = set.toArray(result);
        return result;
    }

    public Integer[] random(int length) {
        if (length <= 0) {
            return new Integer[0];
        }
        List<Integer> list = new ArrayList<Integer>(length);
        for (int i = 0; i < length; i++) {
            list.add(i);
        }
        Integer[] array = new Integer[length];
        for (int i = 0; i < length; i++) {
            array[i] = list.remove(new Random().nextInt(length - i));
        }
        return array;
    }


}

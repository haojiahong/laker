package com.hao.laker.study.javabase.testprogram;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

/**
 * 集合，数组的转换
 * Created by haojiahong on 17/4/18.
 */
public class TestTwo {

    public static void main(String[] args) {
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4);
        Integer[] array2 = new Integer[list.size()];
        array2 = list.toArray(array2);

        System.out.println(Arrays.toString(array2));

        String[] strs = new String[]{"a", "b"};
        List<String> strList = Arrays.asList(strs);
        System.out.println(strList);

    }
}

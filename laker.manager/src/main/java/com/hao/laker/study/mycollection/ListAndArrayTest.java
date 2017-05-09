package com.hao.laker.study.mycollection;

import java.util.Arrays;
import java.util.List;

/**
 * Created by haojiahong on 17/5/9.
 */
public class ListAndArrayTest {
    public static void main(String[] args) {
        String[] strs = new String[]{"a", "b"};
        //asList方法返回的是Arrays内部的一个类，并不是util包下的ArrayList
        List<String> list = Arrays.asList(strs);

//        list.add("c");
        System.out.println(list.toString());
        strs[0] = "jia";
        System.out.println(list.toString());

    }
}

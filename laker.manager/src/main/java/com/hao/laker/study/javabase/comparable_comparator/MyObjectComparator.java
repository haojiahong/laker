package com.hao.laker.study.javabase.comparable_comparator;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by haojiahong on 17/3/25.
 */
public class MyObjectComparator implements Comparator<MyObject> {
    @Override
    public int compare(MyObject o1, MyObject o2) {
        if (o1.getStr().compareTo(o2.getStr()) > 1) {
            return 1;
        } else if (o1.getStr().compareTo(o2.getStr()) == 0) {
            return 0;
        } else {
            return -1;
        }

    }

    public static void main(String[] args) {
        MyObject o1 = new MyObject("b");
        MyObject o2 = new MyObject("b");
        MyObject o3 = new MyObject("a");
        MyObject o4 = new MyObject("d");

        MyObjectComparator comparator = new MyObjectComparator();
        System.out.println(comparator.compare(o1, o2));
        System.out.println(comparator.compare(o1, o3));
        System.out.println(comparator.compare(o1, o4));

        //同样是从小到大输出了
        List<MyObject> myObjects = new ArrayList<>();
        myObjects.add(o1);
        myObjects.add(o2);
        myObjects.add(o3);
        myObjects.add(o4);
        Collections.sort(myObjects, comparator);
        System.out.println(JSON.toJSONString(myObjects));

    }

}

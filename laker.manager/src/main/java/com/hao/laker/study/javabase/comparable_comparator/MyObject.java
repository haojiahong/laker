package com.hao.laker.study.javabase.comparable_comparator;

import com.alibaba.fastjson.JSON;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by haojiahong on 17/3/25.
 */
public class MyObject implements Comparable<MyObject> {

    @Getter
    private String str;

    public MyObject(String str) {
        this.str = str;
    }

    @Override
    public int compareTo(MyObject o) {
        //在自己的类内部是可以看到str的
        if (this.str.compareTo(o.str) > 0) {
            return 1;
        } else if (this.str.compareTo(o.str) == 0) {
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

        System.out.println(o1.compareTo(o2));
        System.out.println(o1.compareTo(o3));
        System.out.println(o1.compareTo(o4));

        //list最后会按照从小到大的顺序输出
        List<MyObject> myObjects = new ArrayList<>();
        myObjects.add(o1);
        myObjects.add(o4);
        myObjects.add(o3);
        myObjects.add(o2);
        Collections.sort(myObjects);
        System.out.println(JSON.toJSONString(myObjects));

    }
}

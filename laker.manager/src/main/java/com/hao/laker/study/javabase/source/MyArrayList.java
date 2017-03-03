package com.hao.laker.study.javabase.source;

import java.util.Arrays;

/**
 * 自定义数组
 * Created by haojiahong on 17/3/1.
 */
public class MyArrayList<E> {
    private int capacity = 10;
    private int size = 0;
    private Object[] tables;

    public MyArrayList() {
        tables = new Object[capacity];
    }

    public MyArrayList(int capacity) {
        this.capacity = capacity;
        tables = new Object[capacity];
    }

    public void add(E e) {
        if (size >= capacity) {
            enlargeCapacity();
        }
        tables[size] = e;
        size++;
    }

    public void remove(int index) {
        if (index >= size) {
            throw new RuntimeException("the " + index + " is out of bond");
        }
        for (int i = index; i < size - 1; i++) {
            tables[i] = tables[i + 1];
        }
        tables[size - 1] = null;//垃圾回收
        size--;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index >= size) {
            throw new RuntimeException("the " + index + " is out of bond");
        }
        return (E) tables[index];

    }

    /**
     * 数组扩容方法
     */
    private void enlargeCapacity() {
        capacity = capacity * 3 / 2 + 1;
//        Object[] newTables = new Object[capacity];
//        System.arraycopy(tables, 0, newTables, 0, tables.length);
        Object[] newTables = Arrays.copyOf(tables, capacity);
        tables = newTables;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(tables[i]).append(",");
        }
        if (size > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        MyArrayList<Integer> myArrayList = new MyArrayList<>();
        //添加操作
        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add(3);
        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add(3);
        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add(3);
        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add(3);
        System.out.println(myArrayList.toString());
        //删除操作
        myArrayList.remove(2);
        System.out.println(myArrayList.toString());
        //get操作
        System.out.println(myArrayList.get(0));

    }

}

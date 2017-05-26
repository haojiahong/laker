package com.hao.laker.study.javabase.serialize;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by haojiahong on 17/5/25.
 */
public class Serializable_Test {
    public static void main(String[] args) throws Exception {
        MyObj obj = new MyObj("ABC");

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("myobj.txt"));
        oos.writeObject(obj);
        oos.flush();
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("myobj.txt"));
        MyObj obj2 = (MyObj) ois.readObject();
        ois.close();
        System.out.println(obj2.getName());
    }
}

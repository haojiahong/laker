package com.hao.laker.study.nio.nio;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * Created by haojiahong on 2017/10/9.
 */
public class NIOTest {
    public static void main(String[] args) {
        //分配Buffer缓冲区大小   其本质是指定byte[]大小
        ByteBuffer buffer = ByteBuffer.allocate(10);
        printBuffer(buffer);
        buffer.put((byte) 1);
        printBuffer(buffer);
        buffer.flip();
        printBuffer(buffer);
    }

    public static void printBuffer(Buffer buffer) {
        System.out.println("--------");
        System.out.println("position : " + buffer.position());
        System.out.println("limit : " + buffer.limit());
        System.out.println("capacity : " + buffer.capacity());
        System.out.println("--------");
    }
}

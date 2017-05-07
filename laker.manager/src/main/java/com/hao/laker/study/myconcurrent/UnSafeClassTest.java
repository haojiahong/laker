package com.hao.laker.study.myconcurrent;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * Created by haojiahong on 17/4/13.
 */
public class UnSafeClassTest {

    public static void main(String[] args) throws NoSuchFieldException,
            SecurityException, IllegalArgumentException, IllegalAccessException {
        // 通过反射得到theUnsafe对应的Field对象
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        // 设置该Field为可访问
        field.setAccessible(true);
        // 通过Field得到该Field对应的具体对象，传入null是因为该Field为static的
        Unsafe unsafe = (Unsafe) field.get(null);
        System.out.println(unsafe);

        byte[] data = new byte[10];
        System.out.println(Arrays.toString(data));
        int byteArrayBaseOffset = unsafe.arrayBaseOffset(byte[].class);

        System.out.println(byteArrayBaseOffset);
        unsafe.putByte(data, byteArrayBaseOffset, (byte) 1);
        unsafe.putByte(data, byteArrayBaseOffset + 5, (byte) 5);
        System.out.println(Arrays.toString(data));

    }
}


package com.hao.laker.study.nio.buffer_channel;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by haojiahong on 17/5/10.
 */
public class PathTest {
    public static void main(String[] args) {
        Path path = Paths.get(".");
        System.out.println("path里面包含的路径数量:" + path.getNameCount());
        System.out.println("path的根路径：" + path.getRoot());
        System.out.println(path.toAbsolutePath());
        System.out.println(path.toAbsolutePath().getRoot());
        System.out.println(path.toAbsolutePath().getRoot().getNameCount());

    }
}

package com.hao.laker.biz;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by haojiahong on 2017/10/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-biz-context.xml")
//@Transactional
@Slf4j
public class NIOStuTest {

    @Test
    public void aTest() throws Exception {

        ClassLoader classLoader = NIOStuTest.class.getClassLoader();
        URL resource = classLoader.getResource("a.txt");
        String path = resource.getPath();
        System.out.println(path);
        File f = new File(path);
        RandomAccessFile randomAccessFile = new RandomAccessFile(f, "rw");
        FileChannel randomChannel = randomAccessFile.getChannel();
        ByteBuffer byteBuffer = randomChannel.map(FileChannel.MapMode.READ_ONLY, 0, f.length());
        randomChannel.position(f.length());
        randomChannel.write(byteBuffer);

    }


}

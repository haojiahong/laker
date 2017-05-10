package com.hao.laker.study.nio.buffer_channel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * Created by haojiahong on 17/5/10.
 */
public class FileChannelTest {

    public static void main(String[] args) throws IOException {
        File file = new File("./laker.manager/src/main/java/com/hao/laker/study/nio/buffer_channel/FileTest.java");
        FileChannel inChannel = new FileInputStream(file).getChannel();
        FileChannel outChannel = new FileOutputStream("a.txt").getChannel();

        MappedByteBuffer mappedByteBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
        Charset charset = Charset.forName("GBK");
        outChannel.write(mappedByteBuffer);
        mappedByteBuffer.clear();

        CharsetDecoder charsetDecoder = charset.newDecoder();
        CharBuffer charBuffer = charsetDecoder.decode(mappedByteBuffer);
        System.out.println(charBuffer);


    }
}

package com.hao.laker.study.nio.buffer_channel;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * Created by haojiahong on 17/5/10.
 */
public class ReadFile {
    public static void main(String[] args) throws IOException {

        FileInputStream fileInputStream = new FileInputStream("./laker.manager/src/main/java/com/hao/laker/study/nio/buffer_channel/ReadFile.java");
        FileChannel fileChannel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(64);
        while (fileChannel.read(byteBuffer) != -1) {
            byteBuffer.flip();
            Charset charset = Charset.forName("GBK");
            CharsetDecoder charsetDecoder = charset.newDecoder();
            CharBuffer charBuffer = charsetDecoder.decode(byteBuffer);
            System.out.println(charBuffer);
            byteBuffer.clear();
        }

    }
}

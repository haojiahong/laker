package com.hao.laker.study.nio.nio;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;

/**
 * Created by haojiahong on 2017/5/10.
 */
public class NServer {
    private Selector selector = null;
    static final int PORT = 30000;
    private Charset charset = Charset.forName("UTF-8");
    public void init() throws IOException {
        selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1",PORT);
        serverSocketChannel.bind(inetSocketAddress);
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while(selector.select()>0){
            for (SelectionKey sk : selector.selectedKeys()){
                selector.selectedKeys().remove(sk);
                if (sk.isAcceptable()){
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_READ);
                    sk.interestOps(SelectionKey.OP_ACCEPT);
                }

                if (sk.isReadable()){
                    SocketChannel socketChannel = (SocketChannel) sk.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    StringBuilder content = new StringBuilder();

                    while(socketChannel.read(byteBuffer)>0){
                        byteBuffer.flip();
                        content.append(charset.decode(byteBuffer));
                    }
                    System.out.println("读取的数据:"+content.toString());
                    sk.interestOps(SelectionKey.OP_READ);
                    if (StringUtils.isBlank(content.toString())){
                        for (SelectionKey key : selector.selectedKeys()){
                            Channel channel = key.channel();
                            if (channel instanceof SocketChannel){
                                SocketChannel dest = (SocketChannel) channel;
                                dest.write(charset.encode(content.toString()));
                            }
                        }
                    }
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        new NServer().init();
    }
}

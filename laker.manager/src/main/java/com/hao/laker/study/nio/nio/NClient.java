package com.hao.laker.study.nio.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * Created by haojiahong on 2017/5/10.
 */
public class NClient {
    private Selector selector = null;
    static final int PORT = 30000;
    private Charset charset = Charset.forName("UTF-8");
    private SocketChannel socketChannel = null;

    public void init() throws IOException {
        selector = Selector.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1",PORT);
        socketChannel = SocketChannel.open(inetSocketAddress);
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);



    }

    private class ClientThread extends Thread{
        @Override
        public void run() {
            super.run();
            try {
                while(selector.select()>0){
                    for (SelectionKey sk : selector.selectedKeys()){
                        selector.selectedKeys().remove(sk);
                        if (sk.isReadable()){

                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
